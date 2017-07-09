package com.qsense.transfer;

import java.io.Serializable;

/**
 * @author Neeraj.
 * 
 */
public class CommonTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 135042547884680771L;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
