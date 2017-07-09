
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
 * Class responsible for managing user goals
 * 
 * @author Sanjay
 */

@Entity
@Table(name = "goal")
public class GoalEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 338424130754806017L;

	@Column(name= "title")
	private String title ;
	
	@Column(name= "description")
	private String description;

	@ManyToOne
	@JoinColumn(name= "duration_id")
	private DurationEntity duration;
	
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

	public GoalEntity() {
		super();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public DurationEntity getDuration() {
		return duration;
	}

	public void setDuration(DurationEntity duration) {
		this.duration = duration;
	}
}
