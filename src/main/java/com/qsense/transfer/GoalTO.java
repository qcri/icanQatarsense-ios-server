package com.qsense.transfer;

import java.io.Serializable;

public class GoalTO implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 6785636890647559805L;

	private String title;	
	
	private String description;
	
	private Long durationId;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDurationId() {
		return durationId;
	}

	public void setDurationId(Long durationId) {
		this.durationId = durationId;
	}
	
	

	

	

}
