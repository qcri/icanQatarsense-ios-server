package com.qsense.service;

import java.util.List;

import com.qsense.exception.QSenseServiceException;
import com.qsense.transfer.MessagePaginationTO;
import com.qsense.transfer.MessageSimpleTO;
import com.qsense.transfer.MessageTO;
import com.qsense.transfer.UserMessageTO;
import com.qsense.util.MessageStatusEnum;

public interface MessageService {

	MessageTO create(MessageTO messageTO);
				
	MessageTO get(Long id);

	List<MessageSimpleTO> getAll();

	boolean read(Long userId, List<Long> messageIds) throws QSenseServiceException;

	List<UserMessageTO> retrieve(Long userId, List<Long> messageIds) throws QSenseServiceException;

	MessagePaginationTO retrieveAll(Long userId, int pageNumber, int itemsPerPage) throws QSenseServiceException;
	
	public Long getUnreadMessageCount(Long userId);

	boolean delete(Long userId, Long messageId);

	boolean updateMessageStatus(Long userId, Long messageId,
			MessageStatusEnum status);

}
