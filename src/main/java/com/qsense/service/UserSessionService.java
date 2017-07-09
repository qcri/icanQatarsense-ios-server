package com.qsense.service;

import com.qsense.entity.DeviceTypeEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserSessionEntity;

public interface UserSessionService {
	
	public abstract UserSessionEntity create(UserEntity userEntity, String timezone);

    public abstract boolean updateDeviceId(Long sessionId, String deviceId, Boolean isStepCountSensorAvailable, DeviceTypeEntity deviceType);

    public abstract boolean updateLogoutTime(Long sessionId);
}
