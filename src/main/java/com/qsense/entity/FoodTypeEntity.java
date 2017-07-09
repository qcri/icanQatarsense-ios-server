package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.qsense.util.FoodTypeEnum;

/**
 * Food Type Entity
 * 
 * @author Kushal
 */

@Entity
@Table(name = "food_type")
public class FoodTypeEntity extends BaseEntity{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1766222320251185103L;

	@Column(name= "name")
	@Enumerated (value = EnumType.STRING)
	private FoodTypeEnum name;
	
	@Column(name= "description")
	private String description;
	
	@Column(name = "price")
	private Long price;
	
	public FoodTypeEnum getName() {
		return name;
	}

	public void setName(FoodTypeEnum name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}
