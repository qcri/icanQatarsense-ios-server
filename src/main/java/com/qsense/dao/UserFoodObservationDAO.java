package com.qsense.dao;

import java.util.Date;
import java.util.List;

import com.qsense.entity.UserEntity;
import com.qsense.entity.UserFoodObservationEntity;
import com.qsense.util.FoodTypeEnum;

/**
 * User Food Observation DAO
 * 
 * @author Sanjay
 */
public interface UserFoodObservationDAO extends CommonDAO<UserFoodObservationEntity> {

	public Double getFoodCountByUserAndFoodType(UserEntity user, String dateInGMT, String nextDateInGMT, FoodTypeEnum foodType);
	public List<UserFoodObservationEntity> getAllByUserIdAndFromDate(Long userId, Date fromDate);
	public List getFoodCountByUser(UserEntity user, String dateInGMT, String nextDateInGMT);
}
