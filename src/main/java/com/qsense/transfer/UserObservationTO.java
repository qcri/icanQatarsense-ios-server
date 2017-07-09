package com.qsense.transfer;

import java.io.Serializable;

public class UserObservationTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2517178306916283802L;

	private String subCategoryName;	
	
	private String startTimestamp ;
	
	private String endTimestamp;
	
	private Double field1;
			
	private Double field2;
	
	private Double field3;
	
	private Long sessionId;
	
	private Long sessionRecordId;
		
	private Boolean isManual;

	/**
	 * @return the field1
	 */
	public Double getField1() {
		return field1;
	}

	/**
	 * @param field1 the field1 to set
	 */
	public void setField1(Double field1) {
		this.field1 = field1;
	}

	/**
	 * @return the field2
	 */
	public Double getField2() {
		return field2;
	}

	/**
	 * @param field2 the field2 to set
	 */
	public void setField2(Double field2) {
		this.field2 = field2;
	}

	/**
	 * @return the field3
	 */
	public Double getField3() {
		return field3;
	}

	/**
	 * @param field3 the field3 to set
	 */
	public void setField3(Double field3) {
		this.field3 = field3;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the sessionRecordId
	 */
	public Long getSessionRecordId() {
		return sessionRecordId;
	}

	/**
	 * @param sessionRecordId the sessionRecordId to set
	 */
	public void setSessionRecordId(Long sessionRecordId) {
		this.sessionRecordId = sessionRecordId;
	}

	public String getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Boolean getIsManual() {
		return isManual;
	}

	public void setIsManual(Boolean isManual) {
		this.isManual = isManual;
	}

	public String getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
	}


}
