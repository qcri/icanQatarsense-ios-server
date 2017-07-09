package com.qsense.dao.impl;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.qsense.dao.GoalAttributesDAO;
import com.qsense.entity.GoalAttributesEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * User Food Observation Access Object Implementation
 * 
 * @author Sanjay
 */
@Repository("GoalAttributesDAO")
public class GoalAttributesDAOImpl extends CommonDAOImpl<GoalAttributesEntity> implements GoalAttributesDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<GoalAttributesEntity> getModelClass() {
		return GoalAttributesEntity.class;
	}

	@Override
	public List<GoalAttributesEntity> getByGoalId(Long id) {
		logger.info("Begin : GoalAttributesDAOImpl.getByGoalId");
		initCommonAttributes();
		Predicate predicate1 = builder.equal(root.get("goal"),id);
		criteria.where(predicate1);
		List<GoalAttributesEntity> goalAttributesEntities = getResults();
		logger.info("End : GoalAttributesDAOImpl.getByGoalId");
		return goalAttributesEntities;
	}

	@Override
	public List<GoalAttributesEntity> getByGoalIdAndStepCountSubCategory(Long id) {
		logger.info("Begin : GoalAttributesDAOImpl.getByGoalIdAndStepCountSubCategory");
		initCommonAttributes();
		Predicate predicate1 = builder.equal(root.get("goal"),id);
		Predicate predicate2 = builder.equal(root.get("subCategory"),2);
		criteria.where(predicate1,predicate2);
		List<GoalAttributesEntity> goalAttributesEntities = getResults();
		logger.info("End : GoalAttributesDAOImpl.getByGoalIdAndStepCountSubCategory");
		return goalAttributesEntities;
	}

	

}
