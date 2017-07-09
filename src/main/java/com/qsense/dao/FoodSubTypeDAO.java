package com.qsense.dao;

import java.util.List;

import com.qsense.entity.FoodSubTypeEntity;
import com.qsense.entity.FoodTypeEntity;

/**
 * Food Sub-Type DAO
 * 
 * @author Kushal
 */
public interface FoodSubTypeDAO extends CommonDAO<FoodSubTypeEntity> {

	List<FoodSubTypeEntity> getAllByFoodType(FoodTypeEntity foodType);
	
}