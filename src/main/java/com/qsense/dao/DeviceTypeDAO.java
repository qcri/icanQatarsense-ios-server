package com.qsense.dao;

import com.qsense.entity.DeviceTypeEntity;
import com.qsense.util.DeviceTypeEnum;

/**
 * DeviceType DAO
 * 
 * @author Kushal
 */
public interface DeviceTypeDAO extends CommonDAO<DeviceTypeEntity> {

	public DeviceTypeEntity findDeviceTypeByName(DeviceTypeEnum deviceType);
				
}
