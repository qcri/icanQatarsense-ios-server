package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.AppGroupDAO;
import com.qsense.entity.AppGroupEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * AppGroup Access Object Implementation
 * 
 * @author Neeraj
 */
@Repository("AppGroupDAO")
public class AppGroupDAOImpl extends CommonDAOImpl<AppGroupEntity> implements AppGroupDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<AppGroupEntity> getModelClass() {
		return AppGroupEntity.class;
	}
	
	
	
}
