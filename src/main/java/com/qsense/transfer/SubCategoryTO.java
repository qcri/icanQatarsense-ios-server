package com.qsense.transfer;


public class SubCategoryTO extends CommonTO {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5682963584513717007L;

	private String name; 
	
	private String displayName;
	
	private ObservationUnitTO observationUnitTO;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ObservationUnitTO getObservationUnitTO() {
		return observationUnitTO;
	}
	public void setObservationUnitTO(ObservationUnitTO observationUnitTO) {
		this.observationUnitTO = observationUnitTO;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
