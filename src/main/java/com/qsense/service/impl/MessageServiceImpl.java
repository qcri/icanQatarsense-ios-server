package com.qsense.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsense.adapter.MessageEntityTOMapper;
import com.qsense.common.ApplicationProperties;
import com.qsense.dao.MessageDAO;
import com.qsense.dao.MessageStatusDAO;
import com.qsense.dao.UserDAO;
import com.qsense.dao.UserMessageDAO;
import com.qsense.entity.MessageEntity;
import com.qsense.entity.MessageStatusEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserMessageEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.exception.QSenseServiceException;
import com.qsense.exception.QSenseWebException;
import com.qsense.service.MessageService;
import com.qsense.service.UserService;
import com.qsense.transfer.ContentTO;
import com.qsense.transfer.MessagePaginationTO;
import com.qsense.transfer.MessageSimpleTO;
import com.qsense.transfer.MessageTO;
import com.qsense.transfer.UserMessageTO;
import com.qsense.util.Constants;
import com.qsense.util.CurrentContext;
import com.qsense.util.MessageStatusEnum;
import com.qsense.util.Utils;
import com.qsense.util.logger.QSenseLogger;


@Transactional
@Service("MessageService")
public class MessageServiceImpl implements MessageService{
	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private MessageDAO messageDAO;
	
	@Autowired
	private MessageStatusDAO messageStatusDAO;
	
	@Autowired
	private UserMessageDAO userMessageDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private MessageEntityTOMapper messageEntityTOMapper;
		
	@Autowired
	private UserService userService;
	
	@Override
	public MessageTO create(MessageTO messageTO) {
		if(messageTO.getContent().length()>Constants.MAX_STRING_LENGTH1000){
			throw new QSenseServiceException("Message Content length is greater than " +Constants.MAX_STRING_LENGTH1000, null);
		}
		if(messageTO.getTitle().length()>Constants.MAX_STRING_LENGTH200){
			throw new QSenseServiceException("Message Title length is greater than " +Constants.MAX_STRING_LENGTH200, null);
		}
		MessageEntity messageEntity = messageEntityTOMapper.toEntity(messageTO);
		messageEntity.setPostedAt(Utils.convertDateToGMTDate(new Date()));
		messageEntity.setPostedBy(CurrentContext.getUserId());
		messageEntity = messageDAO.create(messageEntity);
		messageTO = messageEntityTOMapper.toDTO(messageEntity);
		List<UserEntity> users = userDAO.getAllUsersByGroupAndRole(messageEntity.getGroup(),messageEntity.getRole());
		
		List<String> registrationIds = new LinkedList<String>();
		
		UserMessageEntity userMessageEntity;
		for (UserEntity userEntity : users) {
			if(!StringUtils.isBlank(userEntity.getDeviceId())){
				registrationIds.add(userEntity.getDeviceId());
			}
			userMessageEntity = new UserMessageEntity();
			userMessageEntity.setIsRead(false);
			userMessageEntity.setIsDeleted(false);
			userMessageEntity.setMessage(messageEntity);
			userMessageEntity.setUser(userEntity);
			userMessageDAO.create(userMessageEntity);
		}

		if(!registrationIds.isEmpty()){
			ContentTO content = createContent(registrationIds, messageTO);
			// fetch apiKey from Application Property file
			String apiKey = ApplicationProperties.getProperty("apiKey");
			int responsecode =  post(apiKey,content);
			if(responsecode!=200){
				throw new QSenseWebException("Message sending failure to GCM");
			}
		}
		return messageTO;
	}
	
	private ContentTO createContent(List<String> registrationIds, MessageTO messageTO){
		Map<String, Long> messageIdMap = new HashMap<String, Long>(); 
		messageIdMap.put("MessageId",messageTO.getId());
		ContentTO content = new ContentTO();
		content.setRegistration_ids(registrationIds);
		content.setData(messageIdMap);
		return content;
	}

	private int  post(String apiKey, ContentTO content) {
		int responseCode =0;
		try{
			// 1. URL
			URL url = new URL("https://android.googleapis.com/gcm/send");
						
			// 2. Open connection
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						
			// 3. Specify POST method
			conn.setRequestMethod("POST");
			
			// 4. Set the headers
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key="+apiKey);
			
			conn.setDoOutput(true);

			// 5. Add JSON data into POST request body 
		
			//`5.1 Use Jackson object mapper to convert Contnet object into JSON
	    	ObjectMapper mapper = new ObjectMapper();
	    	
	    	// 5.2 Get connection output stream
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			
			// 5.3 Copy Content "JSON" into 
			mapper.writeValue(wr, content);

			// 5.4 Send the request
			wr.flush();
			
			// 5.5 close
			wr.close();
			 
			// 6. Get the response
			responseCode = conn.getResponseCode();
			logger.info("\nSending 'POST' request to URL : " + url);
			logger.info("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			// 7. Print result
			logger.info(response.toString());
			
		} 
		catch (MalformedURLException e) {
			logger.error("URL is not correct", e.getMessage());
		} 
		catch (IOException e) {
			logger.error("Error in reading response", e.getMessage());
		}
		return responseCode;
    }

	@Override
	public MessageTO get(Long id) {
		MessageEntity messageEntity = messageDAO.getById(id);
		MessageTO messageTO = null;
		if(messageEntity != null){
			messageTO = messageEntityTOMapper.toDTO(messageEntity);
		}		
		return messageTO;
	}

	@Override
	public List<MessageSimpleTO> getAll() {
		List<MessageEntity> all = messageDAO.getAll();
		return messageEntityTOMapper.toSimpleDTOs(all);		
	}

	@Override
	public boolean read(Long userId, List<Long> messageIds) throws QSenseServiceException{
		boolean result = false;
		try{	
			UserEntity userEntity = new UserEntity();
			userEntity.setId(userId);
			MessageEntity messageEntity = null;
			List<MessageEntity> messageEntities = new ArrayList<MessageEntity>();
			for (Long messageId : messageIds) {
				messageEntity = new MessageEntity();
				messageEntity.setId(messageId);
				messageEntities.add(messageEntity);
			}
			List<UserMessageEntity> userMessageEntities = userMessageDAO.getByUserIdAndMessageIds(userEntity, messageEntities);
			for (UserMessageEntity userMessageEntity : userMessageEntities) {
				userMessageEntity.setIsRead(true);
				userMessageEntity.setReadTime(Utils.convertDateToGMTDate(new Date()));
				userMessageDAO.update(userMessageEntity);
			}
			result = true;
		}
		catch(QSenseDAOException e){
			logger.error("Error while changing the reading status of messages", e.getMessage());
			throw new QSenseServiceException("Error while changing the reading status of messages", e);
		}
		return result;
	}

	@Override
	public List<UserMessageTO> retrieve(Long userId, List<Long> messageIds) throws QSenseServiceException{
		List<UserMessageTO> userMessageTOs = new ArrayList<UserMessageTO>();
		UserMessageTO userMessageTO;
		MessageEntity messageEntity;
		UserMessageEntity userMessageEntity;
		
		UserEntity user = null;
		try{
			for (Long messageId : messageIds) {
				userMessageTO = new UserMessageTO();
				messageEntity = new MessageEntity();
				messageEntity.setId(messageId);
				user = new UserEntity();
				user.setId(userId);
				userMessageEntity = userMessageDAO.getByUserIdAndMessageId(user, messageEntity);
				if(userMessageEntity != null){
					userMessageTO = new UserMessageTO();
					userMessageTO.setId(userMessageEntity.getMessage().getId());
					userMessageTO.setTitle(userMessageEntity.getMessage().getTitle());
					userMessageTO.setContent(userMessageEntity.getMessage().getContent());
					userMessageTO.setPostedAt(Utils.convertGMTDateToLocalDate(userMessageEntity.getMessage().getPostedAt()));
					userMessageTO.setMessageStatus(userMessageEntity.getMessageStatus() !=null ? userMessageEntity.getMessageStatus().getName() : null);
					if(userMessageEntity.getMessage().getPostedBy() != null){
						userMessageTO.setPostedBy(userService.getUserById(userMessageEntity.getMessage().getPostedBy()).getUserName());
					}
					userMessageTO.setRead(userMessageEntity.getIsRead());
					userMessageTO.setReadTime(null);
					if(userMessageEntity.getIsRead()){
						userMessageTO.setReadTime(Utils.convertGMTDateToLocalDate(userMessageEntity.getReadTime()));
					}
					userMessageTOs.add(userMessageTO);
				}
			}
		}
		catch(QSenseDAOException e){
			logger.error("Error occured while retrieving message content", e.getMessage());
			throw new QSenseServiceException("Error occured while retrieving message content", e);
		}
		return userMessageTOs;
	}
	
	@Override
	public MessagePaginationTO retrieveAll(Long userId, int pageNumber, int itemsPerPage) throws QSenseServiceException{
		MessagePaginationTO messagePaginationTO = new MessagePaginationTO();
		List<UserMessageTO> userMessageTOs = new ArrayList<UserMessageTO>();
		UserMessageTO userMessageTO;
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userId);
		try{
			List<UserMessageEntity> UserMessageEntities = userMessageDAO.getByUserId(userEntity, pageNumber, itemsPerPage, false);
			for (UserMessageEntity userMessageEntity : UserMessageEntities) {
				userMessageTO = new UserMessageTO();
				userMessageTO.setId(userMessageEntity.getMessage().getId());
				userMessageTO.setTitle(userMessageEntity.getMessage().getTitle());
				userMessageTO.setContent(userMessageEntity.getMessage().getContent());
				userMessageTO.setPostedAt(Utils.convertGMTDateToLocalDate(userMessageEntity.getMessage().getPostedAt()));
				userMessageTO.setMessageStatus(userMessageEntity.getMessageStatus() !=null ? userMessageEntity.getMessageStatus().getName() : null);
				if(userMessageEntity.getMessage().getPostedBy() != null){
					userMessageTO.setPostedBy(userService.getUserById(userMessageEntity.getMessage().getPostedBy()).getUserName());
				}
				userMessageTO.setRead(userMessageEntity.getIsRead());
				userMessageTO.setReadTime(null);
				if(userMessageEntity.getIsRead()){
					userMessageTO.setReadTime(Utils.convertGMTDateToLocalDate(userMessageEntity.getReadTime()));
				}
				userMessageTOs.add(userMessageTO);
			}
		}
		catch(QSenseDAOException e){
			logger.error("Error occured while retrieving messages ", e.getMessage());
			throw new QSenseServiceException("Error occured while retrieving messages ", e);
		}
		messagePaginationTO.setMessages(userMessageTOs);
		messagePaginationTO.setCurrentPageNumber(pageNumber);
		messagePaginationTO.setItemsPerPage(itemsPerPage);
		Long totalRecords = userMessageDAO.getMessageCountByUserId(userEntity);
		int totalPages = (int)(totalRecords/itemsPerPage);
		if((totalRecords%itemsPerPage)>0){
			totalPages++;
		}	
		messagePaginationTO.setTotalNoOfPages(totalPages);
		
		return messagePaginationTO;
	}
	
	@Override
	public Long getUnreadMessageCount(Long userId){
		try{
			UserEntity user = new UserEntity();
			user.setId(userId);
			return userMessageDAO.getUnreadMessageCountByUserId(user);
		}
		catch(QSenseDAOException e){
			logger.error("Error occured while retrieving unread message count ", e.getMessage());
			throw new QSenseServiceException("Error occured while retrieving unread message count ", e);
		}
	}
	
	@Override
	public boolean delete(Long userId, Long messageId) throws QSenseServiceException{
		boolean result = false;
		try{	
			UserEntity userEntity = new UserEntity();
			userEntity.setId(userId);
			
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setId(messageId);
			
			UserMessageEntity userMessage = userMessageDAO.getByUserIdAndMessageId(userEntity, messageEntity);
			userMessage.setIsDeleted(true);
			userMessageDAO.update(userMessage);
			result = true;
		}
		catch(QSenseDAOException e){
			logger.error("Error while deleting the message", e.getMessage());
			throw new QSenseServiceException("Error while deleting the message", e);
		}
		return result;
	}

	@Override
	public boolean updateMessageStatus(Long userId, Long messageId,
			MessageStatusEnum status) {
		boolean result = false;
		try{	
			UserEntity userEntity = new UserEntity();
			userEntity.setId(userId);
			
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setId(messageId);
			
			MessageStatusEntity messageStatusEntity = messageStatusDAO.getByName(status);
			
			UserMessageEntity userMessage = userMessageDAO.getByUserIdAndMessageId(userEntity, messageEntity);
			userMessage.setMessageStatus(messageStatusEntity);
			
			userMessageDAO.update(userMessage);
			result = true;
		}
		catch(QSenseDAOException e){
			logger.error("Error while updating the message status", e.getMessage());
			throw new QSenseServiceException("Error while updating the message status", e);
		}
		return result;
	}
}
