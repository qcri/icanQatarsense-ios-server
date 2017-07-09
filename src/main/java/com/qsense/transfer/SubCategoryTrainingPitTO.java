package com.qsense.transfer;



public class SubCategoryTrainingPitTO extends CommonTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1679401716876041548L;
	
	private String subCategoryName;
	
	private Long subCategoryValue;
	
	private Long subCategoryAchieved;
	
	private String SubCategoryAchivedString;

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Long getSubCategoryValue() {
		return subCategoryValue;
	}

	public void setSubCategoryValue(Long subCategoryValue) {
		this.subCategoryValue = subCategoryValue;
	}

	public Long getSubCategoryAchieved() {
		return subCategoryAchieved;
	}

	public void setSubCategoryAchieved(Long subCategoryAchieved) {
		this.subCategoryAchieved = subCategoryAchieved;
	}

	public String getSubCategoryAchivedString() {
		return SubCategoryAchivedString;
	}

	public void setSubCategoryAchivedString(String subCategoryAchivedString) {
		SubCategoryAchivedString = subCategoryAchivedString;
	}
	
	
	
}
