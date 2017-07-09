package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.OryxDAO;
import com.qsense.entity.OryxEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * Oryx Access Object Implementation
 * 
 * @author Kushal
 */
@Repository("OryxDAO")
public class OryxDAOImpl extends CommonDAOImpl<OryxEntity> implements OryxDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<OryxEntity> getModelClass() {
		return OryxEntity.class;
	}
	
	
	
}
