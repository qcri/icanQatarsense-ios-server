
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
@Table(name = "user_goal")
public class UserGoalEntity extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3068321039768882074L;

	@ManyToOne
	@JoinColumn(name= "user_id")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name= "goal_id")
	private GoalEntity goal;
	
	@Column(name = "start_time")
	private Date startTime ;	
	
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public GoalEntity getGoal() {
		return goal;
	}

	public void setGoal(GoalEntity goal) {
		this.goal = goal;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public UserGoalEntity(UserEntity user, GoalEntity goal,
			Date startTime, Date createdTime, Date updatedTime) {
		super();
		this.user = user;
		this.goal = goal;
		this.startTime = startTime;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public UserGoalEntity() {
		super();
	}
}
