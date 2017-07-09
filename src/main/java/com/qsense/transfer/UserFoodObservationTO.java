package com.qsense.transfer;

import java.io.Serializable;

public class UserFoodObservationTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4264600862718416440L;

	private FoodSubTypeTO foodSubType;
	
	private String time;
			
	private double foodServingSize;
		
	private Long sessionId;
	
	private Long sessionRecordId;
	
	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Long getSessionRecordId() {
		return sessionRecordId;
	}

	public void setSessionRecordId(Long sessionRecordId) {
		this.sessionRecordId = sessionRecordId;
	}

	public FoodSubTypeTO getFoodSubType() {
		return foodSubType;
	}

	public void setFoodSubType(FoodSubTypeTO foodSubType) {
		this.foodSubType = foodSubType;
	}

	public double getFoodServingSize() {
		return foodServingSize;
	}

	public void setFoodServingSize(double foodServingSize) {
		this.foodServingSize = foodServingSize;
	}

}
