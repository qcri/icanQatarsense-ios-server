package com.qsense.transfer;

import java.io.Serializable;


public class JournalTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3394285896778553879L;

	private String userName;
	
	private Double greenFoodCount;
	
	private Double stepCount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getGreenFoodCount() {
		return greenFoodCount;
	}

	public void setGreenFoodCount(Double greenFoodCount) {
		this.greenFoodCount = greenFoodCount;
	}

	public Double getStepCount() {
		return stepCount;
	}

	public void setStepCount(Double stepCount) {
		this.stepCount = stepCount;
	}

}
