
package com.qsense.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.qsense.util.DeviceTypeEnum;


/**
 * Device Type Entity
 * 
 * @author Kushal
 */

@Entity
@Table(name = "device_type")
public class DeviceTypeEntity extends BaseEntity{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5712507461672725263L;

	@Column(name= "name")
	@Enumerated(EnumType.STRING)
	private DeviceTypeEnum name;

	public DeviceTypeEnum getName() {
		return name;
	}

	public void setName(DeviceTypeEnum name) {
		this.name = name;
	}

}
