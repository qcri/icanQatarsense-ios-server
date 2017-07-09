
package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.qsense.util.DurationEnum;

/**
 * Class responsible for managing Duration of goals
 * 
 * @author Sanjay
 */

@Entity
@Table(name = "duration")
public class DurationEntity extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7779372197485006140L;

	@Column(name= "name")
	@Enumerated (value = EnumType.STRING)
	private DurationEnum name ;
	
	@Column(name= "description")
	private String description;
	
	@Column(name = "display_name")
	private String displayName ;	
	
	@Column(name= "number_of_days")
	private Integer numberOfDays;

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

	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public DurationEntity(DurationEnum name, String description, String displayName,
			Integer numberOfDays) {
		super();
		this.setName(name);
		this.description = description;
		this.displayName = displayName;
		this.numberOfDays = numberOfDays;
	}

	public DurationEntity() {
		super();
	}

	public DurationEnum getName() {
		return name;
	}

	public void setName(DurationEnum name) {
		this.name = name;
	}
}
