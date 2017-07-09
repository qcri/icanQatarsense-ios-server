
package com.qsense.transfer;

import com.qsense.entity.SubCategoryEntity;

public class LeaderboardTO {
	private String subCategoryName ;
	private String subCategoryDisplayName;
	private Long min;
	private Long max;
	private Long average;
	private Long userDuration;
	private String minString;
	private String maxString;
	private String averageString;
	private String userDurationString;
	private Long userDurationPercentage;
	private Long sortOrder;
	private Long distinctUsers;
	
	public LeaderboardTO(SubCategoryEntity subCategoryEntity){
		this.minString = "0s";
		this.maxString = "0s";
		this.averageString = "0s";
		this.userDurationString = "0s";
		this.subCategoryName = subCategoryEntity.getName();
		this.subCategoryDisplayName = subCategoryEntity.getDisplayName();
		this.sortOrder = subCategoryEntity.getSortOrder();
		this.min =0L;
		this.max =0L;
		this.average =0L;
		this.userDuration =0L;
		this.setUserDurationPercentage(0L);
		this.distinctUsers =0L;
	}
	
	public LeaderboardTO(){
		
	}

	/**
	 * @return the subCategoryName
	 */
	public String getSubCategoryName() {
		return subCategoryName;
	}

	/**
	 * @param subCategoryName the subCategoryName to set
	 */
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getSubCategoryDisplayName() {
		return subCategoryDisplayName;
	}

	public void setSubCategoryDisplayName(String subCategoryDisplayName) {
		this.subCategoryDisplayName = subCategoryDisplayName;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}


	/**
	 * @return the minString
	 */
	public String getMinString() {
		return minString;
	}

	/**
	 * @param minString the minString to set
	 */
	public void setMinString(String minString) {
		this.minString = minString;
	}

	/**
	 * @return the maxString
	 */
	public String getMaxString() {
		return maxString;
	}

	/**
	 * @param maxString the maxString to set
	 */
	public void setMaxString(String maxString) {
		this.maxString = maxString;
	}

	/**
	 * @return the averageString
	 */
	public String getAverageString() {
		return averageString;
	}

	/**
	 * @param averageString the averageString to set
	 */
	public void setAverageString(String averageString) {
		this.averageString = averageString;
	}

	/**
	 * @return the userDurationString
	 */
	public String getUserDurationString() {
		return userDurationString;
	}

	/**
	 * @param userDurationString the userDurationString to set
	 */
	public void setUserDurationString(String userDurationString) {
		this.userDurationString = userDurationString;
	}

	/**
	 * @return the min
	 */
	public Long getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public void setMin(Long min) {
		this.min = min;
	}

	/**
	 * @return the max
	 */
	public Long getMax() {
		return max;
	}

	/**
	 * @param max the max to set
	 */
	public void setMax(Long max) {
		this.max = max;
	}

	/**
	 * @return the average
	 */
	public Long getAverage() {
		return average;
	}

	/**
	 * @param average the average to set
	 */
	public void setAverage(Long average) {
		this.average = average;
	}

	/**
	 * @return the userDuration
	 */
	public Long getUserDuration() {
		return userDuration;
	}

	/**
	 * @param userDuration the userDuration to set
	 */
	public void setUserDuration(Long userDuration) {
		this.userDuration = userDuration;
	}

	public Long getUserDurationPercentage() {
		return userDurationPercentage;
	}

	public void setUserDurationPercentage(Long userDurationPercentage) {
		this.userDurationPercentage = userDurationPercentage;
	}

	/**
	 * @return the distinctUsers
	 */
	public Long getDistinctUsers() {
		return distinctUsers;
	}

	/**
	 * @param distinctUsers the distinctUsers to set
	 */
	public void setDistinctUsers(Long distinctUsers) {
		this.distinctUsers = distinctUsers;
	}

	
}
