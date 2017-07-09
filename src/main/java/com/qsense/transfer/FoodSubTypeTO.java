package com.qsense.transfer;



public class FoodSubTypeTO extends CommonTO {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8491863053870908301L;
	
	private String name;
	
	private String displayName;
	
	private FoodTypeTO foodTypeTO;
	
	private Long id;
	
	private ObservationUnitTO observationUnit;
	
	private String iconSource;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconSource() {
		return iconSource;
	}
	public void setIconSource(String iconSource) {
		this.iconSource = iconSource;
	}
	public ObservationUnitTO getObservationUnit() {
		return observationUnit;
	}
	public void setObservationUnit(ObservationUnitTO observationUnit) {
		this.observationUnit = observationUnit;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public FoodTypeTO getFoodTypeTO() {
		return foodTypeTO;
	}
	public void setFoodTypeTO(FoodTypeTO foodTypeTO) {
		this.foodTypeTO = foodTypeTO;
	}
	
}
