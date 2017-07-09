
package com.qsense.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.qsense.util.Utils;

/**
 * Class responsible for managing user goals attributes
 * 
 * @author Sanjay
 */

@Entity
@Table(name = "goal_attributes")
public class GoalAttributesEntity extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4716849617555936060L;

	@ManyToOne
	@JoinColumn(name= "goal_id")
	private GoalEntity goal ;
	
	@ManyToOne
	@JoinColumn(name= "sub_category_id")
	private SubCategoryEntity subCategory;
	
	@ManyToOne
	@JoinColumn(name = "food_sub_type_id")
	private FoodSubTypeEntity foodSubType ;	
	
	@Column(name= "value")
	private Long value;
	
	@Column(name= "created_time")
	private Date createdTime;
	
	@Column(name= "updated_time")
	private Date updatedTime;
	
	@PrePersist
	private void prePersist(){
		createdTime = Utils.convertDateToGMTDate(new Date());
		updatedTime = Utils.convertDateToGMTDate(new Date()); 
	}

	@PreUpdate
	private void preUpdate(){
		updatedTime = Utils.convertDateToGMTDate(new Date());
	}

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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public GoalAttributesEntity(GoalEntity goad, SubCategoryEntity subCategory,
			FoodSubTypeEntity foodSubType, Long value, Date createdTime,
			Date updatedTime) {
		super();
		this.goal = goad;
		this.subCategory = subCategory;
		this.foodSubType = foodSubType;
		this.setValue(value);
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public GoalAttributesEntity() {
		super();
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
