package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.RoleDAO;
import com.qsense.entity.RoleEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * Role Access Object Implementation
 * 
 * @author Neeraj
 */
@Repository("RoleDAO")
public class RoleDAOImpl extends CommonDAOImpl<RoleEntity> implements RoleDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<RoleEntity> getModelClass() {
		return RoleEntity.class;
	}
	
	
	
}
