
package com.qsense.transfer;

import java.io.Serializable;


public class ResponseTO implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4478951024919510243L;

	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
		
}
