package com.qsense.transfer;

import java.io.Serializable;

public class OryxTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6957737678710758958L;

	private String displayName;
	
	private Long price;
	
	private String iconSource;
	
	private String name;
	
	private Long id;
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getIconSource() {
		return iconSource;
	}

	public void setIconSource(String iconSource) {
		this.iconSource = iconSource;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
