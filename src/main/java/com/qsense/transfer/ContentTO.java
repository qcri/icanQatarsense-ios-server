package com.qsense.transfer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Kushalkant
 *
 */
public class ContentTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 78932154976504494L;
	private List<String> registration_ids;
	private Map<String,Long> data;
	
	public List<String> getRegistration_ids() {
		return registration_ids;
	}

	public void setRegistration_ids(List<String> registration_ids) {
		this.registration_ids = registration_ids;
	}

	public Map<String, Long> getData() {
		return data;
	}

	public void setData(Map<String, Long> data) {
		this.data = data;
	}
}
