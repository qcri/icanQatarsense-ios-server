
package com.qsense.transfer;

import java.util.Date;

import com.qsense.entity.SubCategoryEntity;

public class LeaderboardForWebTO {
	public Double getMinForControl() {
		return minForControl;
	}

	public void setMinForControl(Double minForControl) {
		this.minForControl = minForControl;
	}

	public Double getMaxForControl() {
		return maxForControl;
	}

	public void setMaxForControl(Double maxForControl) {
		this.maxForControl = maxForControl;
	}

	public Double getAverageForControl() {
		return averageForControl;
	}

	public void setAverageForControl(Double averageForControl) {
		this.averageForControl = averageForControl;
	}

	public Double getMinForTrial() {
		return minForTrial;
	}

	public void setMinForTrial(Double minForTrial) {
		this.minForTrial = minForTrial;
	}

	public Double getMaxForTrial() {
		return maxForTrial;
	}

	public void setMaxForTrial(Double maxForTrial) {
		this.maxForTrial = maxForTrial;
	}

	public Double getAverageForTrial() {
		return averageForTrial;
	}

	public void setAverageForTrial(Double averageForTrial) {
		this.averageForTrial = averageForTrial;
	}

	private Date date;
	private String subCategoryName ;
	private String subCategoryDisplayName;
	private Double minForControl;
	private Double maxForControl;
	private Double averageForControl;
	private String minStringForControl;
	private String maxStringForControl;
	private String averageStringForControl;
	private Double minForTrial;
	private Double maxForTrial;
	private Double averageForTrial;
	private String minStringForTrial;
	private String maxStringForTrial;
	private String averageStringForTrial;
	
	public LeaderboardForWebTO(SubCategoryEntity subCategoryEntity){
		this.minStringForControl = "0s";
		this.maxStringForControl = "0s";
		this.averageStringForControl = "0s";
		this.minStringForTrial = "0s";
		this.maxStringForTrial = "0s";
		this.averageStringForTrial = "0s";
		this.subCategoryName = subCategoryEntity.getName();
		this.subCategoryDisplayName = subCategoryEntity.getDisplayName();
		this.minForControl =0D;
		this.maxForControl =0D;
		this.averageForControl =0D;
		this.minForTrial =0D;
		this.maxForTrial =0D;
		this.averageForTrial =0D;
	}
	
	public LeaderboardForWebTO(){
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public String getSubCategoryDisplayName() {
		return subCategoryDisplayName;
	}

	public void setSubCategoryDisplayName(String subCategoryDisplayName) {
		this.subCategoryDisplayName = subCategoryDisplayName;
	}

	public String getMinStringForControl() {
		return minStringForControl;
	}

	public void setMinStringForControl(String minStringForControl) {
		this.minStringForControl = minStringForControl;
	}

	public String getMaxStringForControl() {
		return maxStringForControl;
	}

	public void setMaxStringForControl(String maxStringForControl) {
		this.maxStringForControl = maxStringForControl;
	}

	public String getAverageStringForControl() {
		return averageStringForControl;
	}

	public void setAverageStringForControl(String averageStringForControl) {
		this.averageStringForControl = averageStringForControl;
	}

	public String getMinStringForTrial() {
		return minStringForTrial;
	}

	public void setMinStringForTrial(String minStringForTrial) {
		this.minStringForTrial = minStringForTrial;
	}

	public String getMaxStringForTrial() {
		return maxStringForTrial;
	}

	public void setMaxStringForTrial(String maxStringForTrial) {
		this.maxStringForTrial = maxStringForTrial;
	}

	public String getAverageStringForTrial() {
		return averageStringForTrial;
	}

	public void setAverageStringForTrial(String averageStringForTrial) {
		this.averageStringForTrial = averageStringForTrial;
	}

}
