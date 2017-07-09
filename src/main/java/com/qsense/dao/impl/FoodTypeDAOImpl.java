package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.FoodTypeDAO;
import com.qsense.entity.FoodTypeEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * Food-Type Access Object Implementation
 * 
 * @author Kushal
 */
@Repository("FoodTypeDAO")
public class FoodTypeDAOImpl extends CommonDAOImpl<FoodTypeEntity> implements FoodTypeDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<FoodTypeEntity> getModelClass() {
		return FoodTypeEntity.class;
	}
	
}
