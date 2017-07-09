
package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * App Group Entity
 * 
 * @author Neeraj
 */

@Entity
@Table(name = "app_group")
public class AppGroupEntity extends BaseEntity{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6732856927245851561L;

	@Column(name= "name")
	private String name;
	
	@Column(name= "description")
	private String description;

	public AppGroupEntity(Long id) {
		this.setId(id);
	}
	
	public AppGroupEntity() {		
	}

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

	@Override
	public String toString() {
		return "AppGroupEntity [name=" + name + ", description=" + description
				+ "]";
	}
}
