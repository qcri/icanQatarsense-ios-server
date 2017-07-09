
package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.qsense.util.RoleEnum;


/**
 * Role Entity
 * 
 * @author Neeraj
 */

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -734295159034197875L;

	@Column(name= "name")
	@Enumerated (value = EnumType.STRING)
	private RoleEnum name;
	
	@Column(name= "description")
	private String description;

	public RoleEntity(Long id) {
		this.setId(id);
	}		
	
	public RoleEntity() {
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoleEnum getName() {
		return name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleEntity [name=" + name + ", description=" + description
				+ "]";
	}
}
