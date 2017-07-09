package com.qsense.transfer;

import java.io.Serializable;

public class ObservationUnitTO implements Serializable{

	

	
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -7861825802032110705L;
	private String name;
	private String displayName;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
			
}
