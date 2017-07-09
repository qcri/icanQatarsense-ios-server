package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.GoalDAO;
import com.qsense.entity.GoalEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * Goal Access Object Implementation
 * 
 * @author Sanjay
 */
@Repository("GoalDAO")
public class GoalDAOImpl extends CommonDAOImpl<GoalEntity> implements GoalDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<GoalEntity> getModelClass() {
		return GoalEntity.class;
	}

	

}
