package com.qsense.dao;

import java.util.List;

import com.qsense.entity.GoalAttributesEntity;

/**
 * User Food Observation DAO
 * 
 * @author Sanjay
 */
public interface GoalAttributesDAO extends CommonDAO<GoalAttributesEntity> {

	List<GoalAttributesEntity> getByGoalId(Long id);

	List<GoalAttributesEntity> getByGoalIdAndStepCountSubCategory(Long id);

	

}
