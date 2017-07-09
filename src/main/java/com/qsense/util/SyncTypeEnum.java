package com.qsense.util;

/**
 * @author Sanjay
 *
 */

public enum SyncTypeEnum{
	AUTOMATIC("Automatic"),
	MANUAL("Manual");
	
	private String syncType;
	
	SyncTypeEnum(String syncType){
		this.setDays(syncType);
	}
	
	public String getDays() {
		return syncType;
	}
	
	public void setDays(String syncType) {
		this.syncType = syncType;
	}
}
