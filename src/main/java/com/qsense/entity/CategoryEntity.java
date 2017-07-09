package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.qsense.util.CategoryTypeEnum;

/**
 * Category Entity
 * 
 * @author Kushal
 */

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1091011546976320963L;

	@Column(name= "name")
	@Enumerated (value = EnumType.STRING)
	private CategoryTypeEnum name;
	
	@Column(name= "description")
	private String description;

	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CategoryEntity [name=" + getName() + ", description=" + description
				+ "]";
	}

	public CategoryTypeEnum getName() {
		return name;
	}

	public void setName(CategoryTypeEnum name) {
		this.name = name;
	}
}
