package com.qsense.dao.impl;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.qsense.dao.FoodSubTypeDAO;
import com.qsense.entity.FoodSubTypeEntity;
import com.qsense.entity.FoodTypeEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * Food Sub-Type Access Object Implementation
 * 
 * @author Kushal
 */
@Repository("FoodSubTypeDAO")
public class FoodSubTypeDAOImpl extends CommonDAOImpl<FoodSubTypeEntity> implements FoodSubTypeDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<FoodSubTypeEntity> getModelClass() {
		return FoodSubTypeEntity.class;
	}

	@Override
	public List<FoodSubTypeEntity> getAllByFoodType(FoodTypeEntity foodType) {
		initCommonAttributes();
		logger.info("Begin : FoodSubTypeDAOImpl.getAllByFoodType");
		Predicate predicate1 = builder.equal(root.get("foodType"),foodType);
		criteria.where(predicate1);
		List<FoodSubTypeEntity> results = getResults();
		logger.info("End : FoodSubTypeDAOImpl.getAllByFoodType");
		return results;
	}
	
}
