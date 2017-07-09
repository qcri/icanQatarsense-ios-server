
package com.qsense.entity;

import java.util.HashMap;

/**
 * Class responsible for Training Pit
 * 
 * @author Sanjay
 */


public class TrainingPitEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2357348420533101334L;

	HashMap<Long, Long> goalToBeAchived;
	
	HashMap<Long, Long> goalAchived;

	public TrainingPitEntity(HashMap<Long, Long> goalToBeAchived,
			HashMap<Long, Long> goalAchived) {
		super();
		this.goalToBeAchived = goalToBeAchived;
		this.goalAchived = goalAchived;
	}

	public HashMap<Long, Long> getGoalToBeAchived() {
		return goalToBeAchived;
	}

	public void setGoalToBeAchived(HashMap<Long, Long> goalToBeAchived) {
		this.goalToBeAchived = goalToBeAchived;
	}

	public HashMap<Long, Long> getGoalAchived() {
		return goalAchived;
	}

	public void setGoalAchived(HashMap<Long, Long> goalAchived) {
		this.goalAchived = goalAchived;
	}

	public TrainingPitEntity() {
		super();
	}
	
	
	
	
	
}
