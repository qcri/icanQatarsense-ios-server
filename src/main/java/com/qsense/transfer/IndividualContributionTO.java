
package com.qsense.transfer;



public class IndividualContributionTO {

	private Long userId;
	
	private Double greenFoodContribution;
	
	private Double stepCountContribution;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getGreenFoodContribution() {
		return greenFoodContribution;
	}

	public void setGreenFoodContribution(Double greenFoodContribution) {
		this.greenFoodContribution = greenFoodContribution;
	}

	public Double getStepCountContribution() {
		return stepCountContribution;
	}

	public void setStepCountContribution(Double stepCountContribution) {
		this.stepCountContribution = stepCountContribution;
	}
}
