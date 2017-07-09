package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class responsible for managing observation Block
 * 
 * @author Kushal
 */

@Entity
@Table(name = "observation_block")
public class ObservationBlockEntity extends BaseEntity {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 3479598702784004186L;

	@Column(name = "session_id")
	private Long sessionId;

	@Column(name = "block_id")
	private Long blockId;
			
	@Column(name = "record_start_id")
	private Long recordStartId;

	@Column(name = "record_end_id")
	private Long recordEndId;

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
	 * @return the recordStartId
	 */
	public Long getRecordStartId() {
		return recordStartId;
	}

	/**
	 * @param recordStartId the recordStartId to set
	 */
	public void setRecordStartId(Long recordStartId) {
		this.recordStartId = recordStartId;
	}

	/**
	 * @return the recordEndId
	 */
	public Long getRecordEndId() {
		return recordEndId;
	}

	/**
	 * @param recordEndId the recordEndId to set
	 */
	public void setRecordEndId(Long recordEndId) {
		this.recordEndId = recordEndId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ObservationBlockEntity [sessionId=" + sessionId + ", blockId="
				+ blockId + ", recordStartId=" + recordStartId
				+ ", recordEndId=" + recordEndId + "]";
	}

}
