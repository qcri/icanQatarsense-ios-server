
package com.qsense.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.qsense.util.Utils;

/**
 * Class responsible for managing user activity
 * 
 * @author Neeraj
 */

@Entity
@Table(name = "user_observation")
public class UserObservationEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3518788494161818703L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;	
	
	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategoryEntity subCategory ;	

	@Column(name= "start_timestamp")
	private Date startTimestamp ;
	
	@Column(name= "end_timestamp")
	private Date endTimestamp ;
	
	@Column(name= "field1")
	private Double field1;
	
	@Column(name= "field2")
	private Double field2;
	
	@Column(name= "field3")
	private Double field3;
	
	@ManyToOne
	@JoinColumn(name = "session_id")
	private UserSessionEntity userSession ;	
	
	@Column(name= "session_record_id")
	private Long sessionRecordId;
	
	@Column(name= "created_time")
	private Date createdTime;
	
	@Column(name = "is_manual")
	private Boolean isManual;
	
	@PrePersist
	private void prePersist(){
		createdTime = Utils.convertDateToGMTDate(new Date());
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

	public void setSubCategory(Long id) {
		SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
		subCategoryEntity.setId(id);
		this.subCategory = subCategoryEntity;
	}

	/**
	 * @return the subCategory
	 */
	public SubCategoryEntity getSubCategory() {
		return subCategory;
	}

	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(SubCategoryEntity subCategory) {
		this.subCategory = subCategory;
	}

	
	/**
	 * @return the startTimestamp
	 */
	public Date getStartTimestamp() {
		return startTimestamp;
	}

	/**
	 * @param startTimestamp the startTimestamp to set
	 */
	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	/**
	 * @return the endTimestamp
	 */
	public Date getEndTimestamp() {
		return endTimestamp;
	}

	/**
	 * @param endTimestamp the endTimestamp to set
	 */
	public void setEndTimestamp(Date endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	/**
	 * @return the field3
	 */
	public Double getField3() {
		return field3;
	}

	/**
	 * @param field3 the field3 to set
	 */
	public void setField3(Double field3) {
		this.field3 = field3;
	}


	/**
	 * @return the field1
	 */
	public Double getField1() {
		return field1;
	}

	/**
	 * @param field1 the field1 to set
	 */
	public void setField1(Double field1) {
		this.field1 = field1;
	}

	/**
	 * @return the field2
	 */
	public Double getField2() {
		return field2;
	}

	/**
	 * @param field2 the field2 to set
	 */
	public void setField2(Double field2) {
		this.field2 = field2;
	}

	public UserObservationEntity() {
			// TODO Auto-generated constructor stub
	}
	public UserObservationEntity(UserObservationEntity userObservationEntity, Date startTimestamp, Date endTimestamp){
		this.user = userObservationEntity.getUser();
		this.subCategory = userObservationEntity.getSubCategory();
		this.startTimestamp = startTimestamp;
		this.endTimestamp = endTimestamp;
		this.field1 = userObservationEntity.getField1();
		this.field2 = userObservationEntity.getField2();
		this.field3 = userObservationEntity.getField3();
		this.userSession = userObservationEntity.getUserSession();
		this.sessionRecordId = userObservationEntity.getSessionRecordId();
		this.isManual = userObservationEntity.getIsManual();
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public UserSessionEntity getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSessionEntity userSession) {
		this.userSession = userSession;
	}
	
	public void setUserSession(Long id) {
		UserSessionEntity userSessionEntity = new UserSessionEntity();
		userSessionEntity.setId(id);
		this.userSession = userSessionEntity;
	}

	/**
	 * @return the sessionRecordId
	 */
	public Long getSessionRecordId() {
		return sessionRecordId;
	}

	/**
	 * @param sessionRecordId the sessionRecordId to set
	 */
	public void setSessionRecordId(Long sessionRecordId) {
		this.sessionRecordId = sessionRecordId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserObservationEntity [user=" + user + ", subCategory="
				+ subCategory + ", startTimestamp=" + startTimestamp
				+ ", endTimestamp=" + endTimestamp + ", field1=" + field1
				+ ", field2=" + field2 + ", field3=" + field3
				+ ", userSession=" + userSession + ", sessionRecordId="
				+ sessionRecordId + ", createdTime=" + createdTime + "]";
	}

	public Boolean getIsManual() {
		return isManual;
	}

	public void setIsManual(Boolean isManual) {
		this.isManual = isManual;
	}
}
