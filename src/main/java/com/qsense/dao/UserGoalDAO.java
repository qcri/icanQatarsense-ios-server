package com.qsense.dao;

import java.util.Date;
import java.util.List;

import com.qsense.entity.UserEntity;
import com.qsense.entity.UserGoalEntity;

/**
 * User Food Observation DAO
 * 
 * @author Sanjay
 */
public interface UserGoalDAO extends CommonDAO<UserGoalEntity> {
	List<UserGoalEntity> getByUserId(Long userId);
	UserGoalEntity getLatestGroupGoalByUserId(UserEntity userId, Date fromDate);
}
