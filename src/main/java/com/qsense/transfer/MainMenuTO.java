package com.qsense.transfer;

import java.io.Serializable;

public class MainMenuTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6546193986598058917L;
	
	public MainMenuTO() {
		super();
		this.stepsContribution = 0L;
		this.activityObjective = 0L;
		this.greenFoodContribution = 0L;
		this.yellowFoodContribution = 0L;
		this.redFoodContribution = 0L;
	}
	
	private Long stepsContribution;	
	private Long activityObjective;
	private Long greenFoodContribution;
	private Long yellowFoodContribution;
	private Long redFoodContribution;
	
	
	
	public Long getStepsContribution() {
		return stepsContribution;
	}
	public void setStepsContribution(Long stepsContribution) {
		this.stepsContribution = stepsContribution;
	}
	public Long getActivityObjective() {
		return activityObjective;
	}
	public void setActivityObjective(Long activityObjective) {
		this.activityObjective = activityObjective;
	}
	public Long getGreenFoodContribution() {
		return greenFoodContribution;
	}
	public void setGreenFoodContribution(Long greenFoodContribution) {
		this.greenFoodContribution = greenFoodContribution;
	}
	public Long getYellowFoodContribution() {
		return yellowFoodContribution;
	}
	public void setYellowFoodContribution(Long yellowFoodContribution) {
		this.yellowFoodContribution = yellowFoodContribution;
	}
	public Long getRedFoodContribution() {
		return redFoodContribution;
	}
	public void setRedFoodContribution(Long redFoodContribution) {
		this.redFoodContribution = redFoodContribution;
	}
	
}
