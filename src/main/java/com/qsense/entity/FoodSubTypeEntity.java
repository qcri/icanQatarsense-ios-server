package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qsense.common.ApplicationProperties;

/**
 * Food Sub Type Entity
 * 
 * @author Kushal
 */

@Entity
@Table(name = "food_sub_type")
public class FoodSubTypeEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 613698511825718287L;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "food_type_id")
	private FoodTypeEntity foodType;

	@Column(name = "description")
	private String description;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "calories_in_unit")
	private Integer caloriesInUnit;
	
	@ManyToOne
	@JoinColumn(name = "observation_unit_id")
	private ObservationUnitEntity observationUnit;

	@Column(name = "icon_source")
	private String iconSource;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FoodTypeEntity getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodTypeEntity foodType) {
		this.foodType = foodType;
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

	public String getIconSource() {
		String foodIconURL = ApplicationProperties.getProperty("food.icons.URL");
		return foodIconURL+iconSource;
	}

	public void setIconSource(String iconSource) {
		this.iconSource = iconSource;
	}

	public Integer getCaloriesInUnit() {
		return caloriesInUnit;
	}

	public void setCaloriesInUnit(Integer caloriesInUnit) {
		this.caloriesInUnit = caloriesInUnit;
	}

	public ObservationUnitEntity getObservationUnit() {
		return observationUnit;
	}

	public void setObservationUnit(ObservationUnitEntity observationUnit) {
		this.observationUnit = observationUnit;
	}
}
