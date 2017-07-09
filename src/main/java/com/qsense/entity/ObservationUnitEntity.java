package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Food Sub Type Entity
 * 
 * @author Sanjay
 */

@Entity
@Table(name = "observation_unit")
public class ObservationUnitEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7154964578743336928L;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "display_name")
	private String displayName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
