package com.qsense.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.qsense.util.CurrentContext;
import com.qsense.util.Utils;


/**
 * An abstract class a persistence Entity. All persistence entities need to
 * extend this class
 * 
 * @author Neeraj
 * 
 */
@MappedSuperclass
public abstract class CommonEntity extends BaseEntity {
	private static final long serialVersionUID = 8753426937063415705L;
	
	@Column(name= "created_by")
	private Long createdBy;

	
	@Column(name= "updated_by")
	private Long updatedBy;

	@Column(name= "created_time")
	private Date createdTime;

	@Column(name= "updated_time")
	private Date updatedTime;
	
	@PrePersist
	private void prePersist(){
		createdTime = Utils.convertDateToGMTDate(new Date());
		updatedTime = Utils.convertDateToGMTDate(new Date());
		createdBy = CurrentContext.getUserId();
		updatedBy = CurrentContext.getUserId();
	}
	
	@PreUpdate
	private void preUpdate(){
		updatedTime = Utils.convertDateToGMTDate(new Date());
		updatedBy = CurrentContext.getUserId();
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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

}
