package com.qsense.transfer;

import java.io.Serializable;
import java.util.List;

public class ObservationBlockTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2796695343834413083L;

	private Long sessionId;	
	
	private Long blockId;
	
	private Long sessionRecordStartId;
			
	private Long sessionRecordEndId;
	
	private List<UserObservationTO> userObservations;

	
	/**
	 * @return the blockId
	 */
	public Long getBlockId() {
		return blockId;
	}

	/**
	 * @param blockId the blockId to set
	 */
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	/**
	 * @return the userObservations
	 */
	public List<UserObservationTO> getUserObservations() {
		return userObservations;
	}

	/**
	 * @param userObservations the userObservations to set
	 */
	public void setUserObservations(List<UserObservationTO> userObservations) {
		this.userObservations = userObservations;
	}

	/**
	 * @return the sessionRecordStartId
	 */
	public Long getSessionRecordStartId() {
		return sessionRecordStartId;
	}

	/**
	 * @param sessionRecordStartId the sessionRecordStartId to set
	 */
	public void setSessionRecordStartId(Long sessionRecordStartId) {
		this.sessionRecordStartId = sessionRecordStartId;
	}

	/**
	 * @return the sessionRecordEndId
	 */
	public Long getSessionRecordEndId() {
		return sessionRecordEndId;
	}

	/**
	 * @param sessionRecordEndId the sessionRecordEndId to set
	 */
	public void setSessionRecordEndId(Long sessionRecordEndId) {
		this.sessionRecordEndId = sessionRecordEndId;
	}

	/**
	 * @return the sessionId
	 */
	public Long getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

}
