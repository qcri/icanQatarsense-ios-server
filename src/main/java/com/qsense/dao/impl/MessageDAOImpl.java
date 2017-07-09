package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.MessageDAO;
import com.qsense.entity.MessageEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * Message Access Object Implementation
 * 
 * @author Neeraj
 */
@Repository("MessageDAO")
public class MessageDAOImpl extends CommonDAOImpl<MessageEntity> implements MessageDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<MessageEntity> getModelClass() {
		return MessageEntity.class;
	}
}
