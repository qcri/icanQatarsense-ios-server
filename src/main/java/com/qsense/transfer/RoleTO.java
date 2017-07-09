
package com.qsense.transfer;

import com.qsense.util.RoleEnum;


public class RoleTO extends CommonTO {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4478951024919510243L;

	private RoleEnum name;
	
	private String description;

	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public RoleEnum getName() {
		return name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}
		
}
