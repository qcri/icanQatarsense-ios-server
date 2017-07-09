package com.qsense.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.qsense.dao.UserGoalDAO;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserGoalEntity;
import com.qsense.util.logger.QSenseLogger;


/**
 * Group Goal Access Object Implementation
 * 
 * @author Sanjay
 */
@Repository("UserGoalDAO")
public class UserGoalDAOImpl extends CommonDAOImpl<UserGoalEntity> implements UserGoalDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<UserGoalEntity> getModelClass() {
		return UserGoalEntity.class;
	}

	@Override
	public List<UserGoalEntity> getByUserId(Long userId) {
		initCommonAttributes();
		logger.info("Begin : UserGoalDAOImpl.getByUserId");
		Predicate predicate1 = builder.equal(root.get("user"),userId);
		criteria.where(predicate1);
		List<UserGoalEntity> results = getResults();
		logger.info("End : UserGoalDAOImpl.getByUserId");
		return results;
	}

	@Override
	public UserGoalEntity getLatestGroupGoalByUserId(UserEntity user,	Date fromDate) {
		initCommonAttributes();
		logger.info("Begin : UserGoalDAOImpl.getLatestGroupGoalByUserId");
		Predicate predicate1 = builder.equal(root.get("user"),user);
		Predicate predicate2 = builder.lessThanOrEqualTo(root.<Date>get("startTime"),fromDate);
		Order order = builder.desc(root.<Date>get("startTime"));
		criteria.where(predicate1,predicate2);
		criteria.orderBy(order);
		UserGoalEntity results = getSingleResult();
		logger.info("End : UserGoalDAOImpl.getLatestGroupGoalByUserId");
		return results;
	}

	

}
