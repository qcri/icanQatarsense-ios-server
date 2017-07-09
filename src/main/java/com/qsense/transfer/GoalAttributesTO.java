package com.qsense.transfer;

import java.io.Serializable;

import com.qsense.entity.FoodSubTypeEntity;
import com.qsense.entity.GoalEntity;
import com.qsense.entity.SubCategoryEntity;

public class GoalAttributesTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5406099281347535722L;

	private GoalEntity goal;
	
	private SubCategoryEntity subCategory;
	
	private FoodSubTypeEntity foodSubType;
	
	private Long value;

	public GoalEntity getGoal() {
		return goal;
	}

	public void setGoal(GoalEntity goal) {
		this.goal = goal;
	}

	public SubCategoryEntity getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategoryEntity subCategory) {
		this.subCategory = subCategory;
	}

	public FoodSubTypeEntity getFoodSubType() {
		return foodSubType;
	}

	public void setFoodSubType(FoodSubTypeEntity foodSubType) {
		this.foodSubType = foodSubType;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}


}
