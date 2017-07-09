package com.qsense.transfer;

import java.io.Serializable;


public class TrainingPitTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1679401716876041548L;
	
	private Long currentUserStepCount;
	
	private Long goalToBeAchieved;

	public TrainingPitTO(){
		super();
		this.currentUserStepCount = 0L;
		this.goalToBeAchieved = 0L;
	}

	public Long getGoalToBeAchieved() {
		return goalToBeAchieved;
	}

	public void setGoalToBeAchieved(Long goalToBeAchieved) {
		this.goalToBeAchieved = goalToBeAchieved;
	}

	public Long getCurrentUserStepCount() {
		return currentUserStepCount;
	}

	public void setCurrentUserStepCount(Long currentUserStepCount) {
		this.currentUserStepCount = currentUserStepCount;
	}
	
	
}
