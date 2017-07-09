package com.qsense.dao.impl;

import org.springframework.stereotype.Repository;

import com.qsense.dao.DeviceTypeDAO;
import com.qsense.entity.DeviceTypeEntity;
import com.qsense.util.DeviceTypeEnum;
import com.qsense.util.logger.QSenseLogger;


/**
 * DeviceType Access Object Implementation
 * 
 * @author Kushal
 */
@Repository("DeviceTypeDAO")
public class DeviceTypeDAOImpl extends CommonDAOImpl<DeviceTypeEntity> implements DeviceTypeDAO{
	QSenseLogger logger  = QSenseLogger.getLogger(getClass());

	@Override
	public Class<DeviceTypeEntity> getModelClass() {
		return DeviceTypeEntity.class;
	}
	
	@Override
	public DeviceTypeEntity findDeviceTypeByName(DeviceTypeEnum deviceType) {
		logger.info("Begin : DeviceTypeDAOImpl.findDeviceTypeByName");		
		initCommonAttributes();
		criteria.where(builder.equal(root.get("name"), deviceType));
		DeviceTypeEntity deviceTypeEntity = getSingleResult();		
		logger.info("End : DeviceTypeDAOImpl.findDeviceTypeByName");
		return deviceTypeEntity;
	}
	
	
}
