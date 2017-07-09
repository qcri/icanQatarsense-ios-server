package com.qsense.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.qsense.dao.UserDAO;
import com.qsense.entity.AppGroupEntity;
import com.qsense.entity.DeviceTypeEntity;
import com.qsense.entity.RoleEntity;
import com.qsense.entity.SubCategoryEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserObservationEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.util.logger.QSenseLogger;



/**
 * User Data Access Object Implementation
 * 
 * @author Neeraj
 */
@Repository("UserDAO")
public class UserDAOImpl extends CommonDAOImpl<UserEntity> implements UserDAO {
	private QSenseLogger logger = QSenseLogger.getLogger(getClass());

	@Override
	public Class<UserEntity> getModelClass() {
		return UserEntity.class;
	}

	public UserEntity create(UserEntity entity) throws QSenseDAOException
    {
    	try{
    		if(entity.getDeviceType()==null){
    			DeviceTypeEntity deviceType = new DeviceTypeEntity();
    			deviceType.setId(1L);
    			entity.setDeviceType(deviceType);
    		}
    		
    		entityManager.persist(entity);
    	}catch(PersistenceException pe){
    		logger.error("Unable to persist entity :"+entity+" in DB", pe);
    		throw new QSenseDAOException(pe);
    	}
        
        return entity;
    }

	@Override
	public UserEntity findUserByUserName(String userName) {
		logger.info("Begin : UserDAOImpl.findUserByUserName");
		initCommonAttributes();
		criteria.where(builder.equal(root.get("userName"), userName));
		UserEntity user = getSingleResult();		
		logger.info("End : UserDAOImpl.findUserByUserName");
		return user;
	}



	@Override
	public UserEntity findUserByUserId(Long userId) {
		logger.info("Begin : UserDAOImpl.findUserByUserId");		
		initCommonAttributes();
		criteria.where(builder.equal(root.get("id"), userId));
		UserEntity user = getSingleResult();		
		logger.info("End : UserDAOImpl.findUserByUserId");
		return user;
	}



	@Override
	public boolean verifyAuthToken(String authToken, String userid) {
		logger.info("Begin : UserDAOImpl.verifyAuthToken");		
		initCommonAttributes();
		Predicate p1 = builder.equal(root.get("authToken"), authToken);
		Predicate p2 = builder.equal(root.get("id"), userid);
		criteria.where(p1,p2);
		UserEntity user = getSingleResult();
		boolean result = false;
		if(user != null){
			result = true;
		}else{
			result = false;
		}
		logger.info("End : UserDAOImpl.verifyAuthToken");
		return result;
	}



	@Override
	public Long verifyLogin(String userName, String password) {
		logger.info("Begin : UserDAOImpl.verifyLogin");		
		initCommonAttributes();
		Predicate p1 = builder.equal(root.get("userName"), userName);
		Predicate p2 = builder.equal(root.get("password"), password);
		criteria.where(p1,p2);
		UserEntity user = getSingleResult();
		Long userId = null;
		if(user != null){
			userId = user.getId();
		}else{
			userId = null;
		}
		logger.info("End : UserDAOImpl.verifyLogin");
		return userId;
	}



	@Override
	public void saveAuthToken(String authToken, String userName) {
		logger.info("Begin : UserDAOImpl.saveAuthToken");		
		initCommonAttributes();
		criteria.where(builder.equal(root.get("userName"), userName));
		UserEntity user = getSingleResult();		
		if(user != null){
			user.setAuthToken(authToken);
		}
		update(user);
		logger.info("End : UserDAOImpl.saveAuthToken");				
	}

	@Override
	public void saveAuthToken(String authToken, String userName, String permanentDeviceId) {
		logger.info("Begin : UserDAOImpl.saveAuthToken");		
		initCommonAttributes();
		criteria.where(builder.equal(root.get("userName"), userName));
		UserEntity user = getSingleResult();		
		if(user != null){
			user.setAuthToken(authToken);
			user.setPermanentDeviceId(permanentDeviceId);
		}
		update(user);
		logger.info("End : UserDAOImpl.saveAuthToken");				
	}


	@Override
	public List<UserEntity> getAllUsersByGroupAndRole(AppGroupEntity group,
			RoleEntity role) {
		logger.info("Begin : UserDAOImpl.getAllUsersByGroupAndRole");		
		initCommonAttributes();
		Predicate p1 = builder.equal(root.get("group"), group);
		Predicate p2 = builder.equal(root.get("role"), role);
		criteria.where(p1,p2);
		List<UserEntity> results = getResults();
		logger.info("Begin : UserDAOImpl.getAllUsersByGroupAndRole");	
		return results;
	}



	@Override
	public boolean getByUserName(String userName) {
		logger.info("Begin : UserDAOImpl.getByUserName");		
		initCommonAttributes();
		Predicate p1 = builder.equal(root.get("userName"), userName);		
		criteria.where(p1);
		UserEntity user = getSingleResult();
		boolean result = false;
		if(user != null){
			result = true;
		}else{
			result = false;
		}
		logger.info("End : UserDAOImpl.getByUserName");
		return result;
	}



	@Override
	public boolean removeAuthToken(Long userId) {
		logger.info("Begin : UserDAOImpl.removeAuthToken");	
		boolean result = false;
		initCommonAttributes();
		Predicate p1 = builder.equal(root.get("id"), userId);	
		criteria.where(p1);
		UserEntity user = getSingleResult();
		if(user != null){			
			user.setAuthToken(null);
			user.setDeviceId(null);
			update(user);
			result = true;
		}else{
			result = false;
		}
		logger.info("End : UserDAOImpl.removeAuthToken");	
		return result;
		
	}



	@Override
	public List<UserEntity> getAllUnAssociatedParticipant() {
		logger.info("Begin : UserDAOImpl.getAllUnAssociatedParticipant");		
		initCommonAttributes();
		Path<Object> path = root.get("id");				
		Subquery<Integer> subquery = criteria.subquery(Integer.class);
		Root<UserEntity> from = subquery.from(UserEntity.class);		
		subquery.select(from.<Integer>get("associatedParticipant"));
		subquery.where(builder.isNotNull(from.<Integer>get("associatedParticipant")));		
		RoleEntity role = new RoleEntity(3L);		
		Predicate p1 = builder.equal(root.get("role"), role);
		criteria.where(p1,builder.not(builder.in(path).value(subquery)));
		List<UserEntity> results = getResults();
		logger.info("Begin : UserDAOImpl.getAllUnAssociatedParticipant");	
		return results;
	}
	
	@Override
	public Long getCoinsForGroup(AppGroupEntity group){
		logger.info("Begin : UserDAOImpl.getCoinsForGroup");
		initCommonAttributes();
		CriteriaQuery<Long> sumCriteria = builder.createQuery( Long.class );
		Root<UserEntity> root1 = sumCriteria.from( UserEntity.class );
		Path<Long> path = root1.get("coins");		
		sumCriteria.select(builder.sum(path));
		Predicate predicate1 = builder.equal(root1.get("group"),group);
		sumCriteria.where( predicate1);
		
		Long totalCoins = entityManager.createQuery( sumCriteria ).getSingleResult();
		logger.info("End : UserDAOImpl.getCoinsForGroup");
		return totalCoins;
	}

}
