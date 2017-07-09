package com.qsense.dao.impl;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.qsense.dao.UserSessionDAO;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserSessionEntity;
import com.qsense.util.logger.QSenseLogger;

/**
 * User Session Data Access Object Implementation
 * 
 * @author Kushal
 */

@Repository("UserSessionDAO")
public class UserSessionDAOImpl extends CommonDAOImpl<UserSessionEntity> implements UserSessionDAO {
	private QSenseLogger logger = QSenseLogger.getLogger(getClass());

	@Override
	public Class<UserSessionEntity> getModelClass() {
		return UserSessionEntity.class;
	}

	@Override
	public UserSessionEntity getExclusiveSession(UserEntity userEntity, Long sessionId) {
		logger.info("Begin : UserSessionDAOImpl.getExclusiveSession");
		UserSessionEntity userSessionEntity = null;
		initCommonAttributes();
		Predicate predicate1 = builder.equal(root.get("user"),userEntity);
		Predicate predicate2 = builder.greaterThan(root.<Long>get("id"),sessionId);
		criteria.where(predicate1, predicate2);
		List<UserSessionEntity> results = getResults(1);
		if(results!=null && results.size()>0){
			userSessionEntity = results.get(0);
		}
		logger.info("End : UserSessionDAOImpl.getExclusiveSession");
		return userSessionEntity;
	}
	
}
