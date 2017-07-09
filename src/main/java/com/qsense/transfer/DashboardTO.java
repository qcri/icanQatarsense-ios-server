package com.qsense.transfer;

public class DashboardTO {
	private String subCategoryName ;
	private Long value1;
	private String value1String;
	private String units;
	private String color;
	private String subCategoryDisplayName;
	private String value1Percentage;
	
	
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
		
	public String getValue1String() {
		return value1String;
	}
	public void setValue1String(String value1String) {
		this.value1String = value1String;
	}
	public Long getValue1() {
		return value1;
	}
	public void setValue1(Long value1) {
		this.value1 = value1;
	}
	public String getSubCategoryDisplayName() {
		return subCategoryDisplayName;
	}
	public void setSubCategoryDisplayName(String subCategoryDisplayName) {
		this.subCategoryDisplayName = subCategoryDisplayName;
	}
	public String getValue1Percentage() {
		return value1Percentage;
	}
	public void setValue1Percentage(String value1Percentage) {
		this.value1Percentage = value1Percentage;
	}
}
