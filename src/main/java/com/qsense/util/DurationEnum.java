package com.qsense.util;

/**
 * @author Kushal
 *
 */

public enum DurationEnum{
	DAILY(1),
	WEEKLY(7),
	FORTNIGHTLY(14),
	MONTHLY(30),
	YEARLY(365);
	
	private Integer days;
	
	DurationEnum(Integer days){
		this.setDays(days);
	}
	
	public Integer getDays() {
		return days;
	}
	
	public void setDays(Integer days) {
		this.days = days;
	}
	
}
