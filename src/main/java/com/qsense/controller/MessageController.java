package com.qsense.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.qsense.exception.QSenseServiceException;
import com.qsense.exception.QSenseWebException;
import com.qsense.service.MessageService;
import com.qsense.transfer.MessagePaginationTO;
import com.qsense.transfer.MessageResponseTO;
import com.qsense.transfer.MessageTO;
import com.qsense.transfer.ResponseTO;
import com.qsense.transfer.UserMessageTO;
import com.qsense.util.MessageStatusEnum;
import com.qsense.util.logger.QSenseLogger;


/**
 * Message Controller
 * 
 * @author Neeraj
 * 
 */

@RestController
@RequestMapping(value = "/message")

public class MessageController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired(required = true)
	private MessageService messageService ;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Object create(@RequestBody String jsonString) throws Exception {
		MessageTO messageTO = null;
	    try {
	    	messageTO = new Gson().fromJson(jsonString, MessageTO.class);
		} catch (Exception e) {
			logger.error("Error occured while creating  with data [%s]: [%s]", jsonString, e.getMessage());
			throw e;
		}
		return messageService.create(messageTO);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object fetchAll() throws Exception {		
		return messageService.getAll();
	}
		
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object get(@PathVariable(value = "id")final Long id) throws Exception {
		logger.info("Begin : MessageController.get");	
		return messageService.get(id);
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public Object readMessage(HttpServletRequest request,
			HttpServletResponse response, @RequestBody List<Long> messageIds) throws Exception {
		MessageResponseTO messageResponseTO = new MessageResponseTO();
		messageResponseTO.setSuccess(false);
	    try {
	    	Long userId = Long.parseLong(request.getHeader("userId"));
	    	boolean result = messageService.read(userId, messageIds);
	    	Long unreadMessageCount = messageService.getUnreadMessageCount(userId);
	    	messageResponseTO.setSuccess(result);
	    	messageResponseTO.setUnreadMessageCount(unreadMessageCount);
		} catch (QSenseServiceException e) {
			logger.error("Error occured while changing the status of message read : [%s]",e.getMessage());
			throw new QSenseWebException("Error occured while changing the status of message read ", e);
		}
		return messageResponseTO;
	}
	
	@RequestMapping(value = "/delete/{messageId}", method = RequestMethod.DELETE)
	public Object deleteMessage(HttpServletRequest request,
			HttpServletResponse response, @PathVariable(value = "messageId")final Long messageId) throws Exception {
		ResponseTO messageResponseTO = new ResponseTO();
		messageResponseTO.setSuccess(false);
	    try {
	    	Long userId = Long.parseLong(request.getHeader("userId"));
	    	boolean result = messageService.delete(userId, messageId);
	    	messageResponseTO.setSuccess(result);
		} catch (QSenseServiceException e) {
			logger.error("Error occured while deleting the message : [%s]",e.getMessage());
			throw new QSenseWebException("Error occured while deleting the message ", e);
		}
		return messageResponseTO;
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.PUT)
	public Object updateMessageStatus(HttpServletRequest request,
			HttpServletResponse response, @RequestBody String jsonString) throws Exception {
		ResponseTO messageResponseTO = new ResponseTO();
		messageResponseTO.setSuccess(false);
	    try {
	    	Long userId = Long.parseLong(request.getHeader("userId"));
	    	
	    	JsonObject jobj = new Gson().fromJson(jsonString, JsonObject.class);
	    	Long messageId = jobj.get("messageId").getAsLong();
	    	MessageStatusEnum status = getMessageStatus(jobj.get("status").getAsString());
	    	boolean result = messageService.updateMessageStatus(userId, messageId, status);
	    	messageResponseTO.setSuccess(result);
		} catch (QSenseServiceException e) {
			logger.error("Error occured while deleting the message : [%s]",e.getMessage());
			throw new QSenseWebException("Error occured while deleting the message ", e);
		}
		return messageResponseTO;
	}
		
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET)
	public Object retrieveMessage(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("ids") List<Long> messageIds) throws Exception {
		List<UserMessageTO> userMessageTOs = null;
	    try {
	    	Long userId = Long.parseLong(request.getHeader("userId"));
	    	userMessageTOs = messageService.retrieve(userId, messageIds);
	    	return userMessageTOs;
		} catch (QSenseServiceException e) {
			logger.error("Error occured while retrieving message contents : [%s]",e.getMessage());
			ResponseTO responseTO = new ResponseTO();
			return responseTO;
		}
	}
	
	@RequestMapping(value = "/retrieveAll", method = RequestMethod.GET)
	public Object retrieveAllMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MessagePaginationTO messagePaginationTO =null;
	    try {
	    	Long userId = Long.parseLong(request.getHeader("userId"));
	    	int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	    	int itemsPerPage = Integer.parseInt(request.getParameter("itemsPerPage"));
	    	messagePaginationTO = messageService.retrieveAll(userId,pageNumber,itemsPerPage);
	    	return messagePaginationTO;
		} catch (QSenseServiceException e) {
			logger.error("Error occured while retrieving message contents : [%s]",e.getMessage());
			ResponseTO responseTO = new ResponseTO();
			return responseTO;
		}
	}
	
	@RequestMapping(value = "/unreadMessageCount", method = RequestMethod.GET)
	public Object getTotalUnreadMessages(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MessageResponseTO messageResponseTO = new MessageResponseTO();
		messageResponseTO.setSuccess(false);
	    try {
	    	Long userId = Long.parseLong(request.getHeader("userId"));
	    	Long unreadMessageCount = messageService.getUnreadMessageCount(userId);
	    	messageResponseTO.setSuccess(true);
	    	messageResponseTO.setUnreadMessageCount(unreadMessageCount);
		} catch (QSenseServiceException e) {
			logger.error("Error occured while retrieving unread message count : [%s]",e.getMessage());
			throw new QSenseWebException("Error occured while retrieving unread message count ", e);
		}
	    return messageResponseTO;
	}
	
	public MessageStatusEnum getMessageStatus(String messageStatus){
		if(!StringUtils.isEmpty(messageStatus)){
			switch(messageStatus.toUpperCase()){
			case "LIKED" : return MessageStatusEnum.LIKED;
			case "UNLIKED" : 
			default : return MessageStatusEnum.UNLIKED;
		}
		}
		else{
			return MessageStatusEnum.UNLIKED;
		}
		
	}
		
}
