package com.qsense.dao;

import com.qsense.entity.MessageStatusEntity;
import com.qsense.util.MessageStatusEnum;

/**
 * Message Status DAO
 * 
 * @author Kushal
 */
public interface MessageStatusDAO extends CommonDAO<MessageStatusEntity> {	
	MessageStatusEntity getByName(MessageStatusEnum messageStatusName);
}
