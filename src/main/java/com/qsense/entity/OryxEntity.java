
package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qsense.common.ApplicationProperties;


/**
 * Oryx Entity
 * 
 * @author Sanjay
 */

@Entity
@Table(name = "oryx")
public class OryxEntity extends BaseEntity{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1139384191546095435L;

	@Column(name= "name")
	private String name;
	
	@Column(name= "display_name")
	private String displayName;
	
	@Column(name= "icon_source")
	private String iconSource;
	
	@Column(name= "price")
	private Long price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getIconSource() {
		String oryxIconUrl = ApplicationProperties.getProperty("oryx.icons.URL");
		return oryxIconUrl+iconSource;
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


}
