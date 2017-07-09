package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.MessageStatusDAO;
import com.qsense.entity.MessageStatusEntity;
import com.qsense.util.MessageStatusEnum;
import com.qsense.util.logger.QSenseLogger;


/**
 * Message Status Access Object Implementation
 * 
 * @author Kushal
 */
@Repository("MessageStatusDAO")
public class MessageStatusDAOImpl extends CommonDAOImpl<MessageStatusEntity> implements MessageStatusDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<MessageStatusEntity> getModelClass() {
		return MessageStatusEntity.class;
	}
	
	@Override
	public MessageStatusEntity getByName(MessageStatusEnum messageStatusName) {
		logger.info("Begin : MessageStatusDAOImpl.getByName");
		initCommonAttributes();
		criteria.where(builder.equal(root.get("name"), messageStatusName));
		MessageStatusEntity messageStatusEntity = getSingleResult();		
		logger.info("End : MessageStatusDAOImpl.getByName");
		return messageStatusEntity;
	}
}
