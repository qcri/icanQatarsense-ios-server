package com.qsense.transfer;

import java.io.Serializable;

public class UserGoalTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8757283155924320725L;

	private Long userId;	
	
	private Long goalId;
	
	private String startTime;

	

	public long getGoalId() {
		return goalId;
	}

	public void setGoalId(Long goalId) {
		this.goalId = goalId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	
	
}
