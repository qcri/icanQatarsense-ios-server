package com.qsense.transfer;

import java.io.Serializable;
import java.util.List;

public class UserFoodObservationBlockTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -657264742567486410L;

	private Long sessionId;	
	
	private Long blockId;
	
	private Long sessionRecordStartId;
	
	private Long sessionRecordEndId;
	
	private List<UserFoodObservationTO> foodObservations;
	
	

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public List<UserFoodObservationTO> getFoodObservations() {
		return foodObservations;
	}

	public void setFoodObservations(List<UserFoodObservationTO> foodObservations) {
		this.foodObservations = foodObservations;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public Long getSessionRecordStartId() {
		return sessionRecordStartId;
	}

	public void setSessionRecordStartId(Long sessionRecordStartId) {
		this.sessionRecordStartId = sessionRecordStartId;
	}

	public Long getSessionRecordEndId() {
		return sessionRecordEndId;
	}

	public void setSessionRecordEndId(Long sessionRecordEndId) {
		this.sessionRecordEndId = sessionRecordEndId;
	}

	

}
