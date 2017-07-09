
package com.qsense.transfer;

import com.qsense.util.ObservationTypeEnum;

public class TeamboardAttributesTO {

	private String name;
	
	private Long objective;
	
	private Long contribution;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getObjective() {
		return objective;
	}

	public void setObjective(Long objective) {
		this.objective = objective;
	}

	public Long getContribution() {
		return contribution;
	}

	public void setContribution(Long contribution) {
		this.contribution = contribution;
	}
}
