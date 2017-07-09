package com.qsense.transfer;

import java.util.Date;




public class ActiveUsersTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7322359582259007282L;

	private Long controlGroupActiveUsers;
	
	private Long trialGroupActiveUsers;
	
	private Date date;

	
	public Long getControlGroupActiveUsers() {
		return controlGroupActiveUsers;
	}

	public void setControlGroupActiveUsers(Long controlGroupActiveUsers) {
		this.controlGroupActiveUsers = controlGroupActiveUsers;
	}

	public Long getTrialGroupActiveUsers() {
		return trialGroupActiveUsers;
	}

	public void setTrialGroupActiveUsers(Long trialGroupActiveUsers) {
		this.trialGroupActiveUsers = trialGroupActiveUsers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
