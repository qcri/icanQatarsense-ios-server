
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
 * Class responsible for managing User Food Observation
 * 
 * @author Sanjay
 */

@Entity
@Table(name = "user_food_observation")
public class UserFoodObservationEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6150099143990129831L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;	
	
	@ManyToOne
	@JoinColumn(name = "food_id")
	private FoodSubTypeEntity foodSubType ;	

	@Column(name= "time")
	private Date time ;
	
	@Column(name= "food_serving_size")
	private Double foodServingSize ;
	
	@ManyToOne
	@JoinColumn(name = "session_id")
	private UserSessionEntity userSession ;	
	
	@Column(name= "session_record_id")
	private Long sessionRecordId;
	
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
	/**
	 * @return the user
	 */
	public UserEntity getUser() {
		return user;
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public void setUser(Long id) {
		UserEntity userEntity =  new UserEntity();
		userEntity.setId(id);
		this.user = userEntity;
	}

	public void setFoodSubType(Long id) {
		FoodSubTypeEntity foodSubTypeEntity = new FoodSubTypeEntity();
		foodSubTypeEntity.setId(id);
		this.foodSubType = foodSubTypeEntity;
	}

	public UserFoodObservationEntity() {
		super();
	}

	public FoodSubTypeEntity getFoodSubType() {
		return foodSubType;
	}

	public void setFoodSubType(FoodSubTypeEntity foodSubType) {
		this.foodSubType = foodSubType;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getFoodServingSize() {
		return foodServingSize;
	}

	public void setFoodServingSize(Double foodServingSize) {
		this.foodServingSize = foodServingSize;
	}

	public UserSessionEntity getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSessionEntity userSession) {
		this.userSession = userSession;
	}

	public Long getSessionRecordId() {
		return sessionRecordId;
	}

	public void setSessionRecordId(Long sessionRecordId) {
		this.sessionRecordId = sessionRecordId;
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

	public UserFoodObservationEntity(UserEntity user,
			FoodSubTypeEntity foodSubType, Date time, Double foodServingSize,
			UserSessionEntity userSession, Long sessionRecordId,
			Date createdTime, Date updatedTime) {
		super();
		this.user = user;
		this.foodSubType = foodSubType;
		this.time = time;
		this.foodServingSize = foodServingSize;
		this.userSession = userSession;
		this.sessionRecordId = sessionRecordId;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}
	
	public void setUserSession(Long id) {
		UserSessionEntity userSessionEntity = new UserSessionEntity();
		userSessionEntity.setId(id);
		this.userSession = userSessionEntity;
	}

}
