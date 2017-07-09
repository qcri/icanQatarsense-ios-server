package com.qsense.transfer;

import java.io.Serializable;

public class MessageResponseTO implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3570970903212758702L;

	private boolean success;
	
	private Long unreadMessageCount;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Long getUnreadMessageCount() {
		return unreadMessageCount;
	}

	public void setUnreadMessageCount(Long unreadMessageCount) {
		this.unreadMessageCount = unreadMessageCount;
	}
	
		
}
