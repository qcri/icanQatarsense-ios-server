
package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * App Group Entity
 * 
 * @author Neeraj
 */

@Entity
@Table(name = "sub_category")
public class SubCategoryEntity extends BaseEntity{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5712507461672725263L;

	@Column(name= "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category ;	
	
	@Column(name= "description")
	private String description;
	
	@Column(name= "display_name")
	private String displayName;
	
	@Column(name= "color")
	private String color;
	
	@Column(name= "units")
	private String units;
	
	@Column(name= "sort_order")
	private Long sortOrder;
	
	@Column(name= "is_dashboard_visible")
	private boolean dashboardVisible;
	
	@Column(name= "is_leaderboard_visible")
	private boolean leaderboardVisible;
	
	@Column(name= "is_manually_allowed")
	private boolean isManuallyAllowed;
	
	@ManyToOne
	@JoinColumn(name = "observation_unit_id")
	private ObservationUnitEntity observationUnit;

	public ObservationUnitEntity getObservationUnit() {
		return observationUnit;
	}

	public void setObservationUnit(ObservationUnitEntity observationUnit) {
		this.observationUnit = observationUnit;
	}

	public boolean isManuallyAllowed() {
		return isManuallyAllowed;
	}

	public void setManuallyAllowed(boolean isManuallyAllowed) {
		this.isManuallyAllowed = isManuallyAllowed;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public boolean isDashboardVisible() {
		return dashboardVisible;
	}

	public void setDashboardVisible(boolean dashboardVisible) {
		this.dashboardVisible = dashboardVisible;
	}

	public boolean isLeaderboardVisible() {
		return leaderboardVisible;
	}

	public void setLeaderboardVisible(boolean leaderboardVisible) {
		this.leaderboardVisible = leaderboardVisible;
	}
}
