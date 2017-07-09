package com.qsense.service.impl;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qsense.dao.UserSessionDAO;
import com.qsense.entity.DeviceTypeEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserSessionEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.exception.QSenseServiceException;
import com.qsense.service.UserSessionService;
import com.qsense.util.DeviceTypeEnum;
import com.qsense.util.Utils;

@Service("UserSessionService")
@Transactional
public class UserSessionServiceImpl implements UserSessionService {
	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Override
	public UserSessionEntity create(UserEntity user, String timezone) {
		logger.info("Begin : UserSessionServiceImpl.create");
		UserSessionEntity userSessionEntity = new UserSessionEntity();
		userSessionEntity.setUser(user);
		userSessionEntity.setAuthToken(user.getAuthToken());
		userSessionEntity.setPermanentDeviceId(user.getPermanentDeviceId());
		userSessionEntity.setDeviceId(user.getDeviceId());
		userSessionEntity.setLoginTime(Utils.convertDateToGMTDate(new Date()));
		userSessionEntity.setTimezone(timezone);
		userSessionEntity.setDeviceType(user.getDeviceType());
		try {
			userSessionEntity = (UserSessionEntity) userSessionDAO
					.create(userSessionEntity);
			logger.info("End : UserSessionServiceImpl.create");
			return userSessionEntity;
		}
		 catch (QSenseDAOException e) {
			logger.error("Error while creating user session ");
			throw new QSenseServiceException("Error while creating user session ", e);
		}
	}

	@Override
	public boolean updateDeviceId(Long sessionId, String deviceId, Boolean isStepCountSensorAvailable, DeviceTypeEntity deviceType) {
		logger.info("Begin : UserSessionServiceImpl.updateDeviceId");
		try {
			UserSessionEntity userSessionEntity = userSessionDAO.getById(sessionId);
			if (userSessionEntity == null) {
				logger.info("Error while fetching user session entity");
				return false;
			}
			userSessionEntity.setDeviceId(deviceId);
			userSessionEntity.setDeviceType(deviceType);
			userSessionEntity.setIsStepCountSensorAvailable(isStepCountSensorAvailable);
			userSessionEntity = userSessionDAO.update(userSessionEntity);
			if (userSessionEntity != null) {
				logger.info("End : UserSessionServiceImpl.updateDeviceId");
				return true;
			} 
		} catch (QSenseDAOException e) {
			logger.error("Error while fetching last processed record of user session ");
			throw new QSenseServiceException(
					"Error while fetching last processed record of user session ",e);
		}
		return false;
	}

	@Override
	public boolean updateLogoutTime(Long sessionId) {
		logger.info("Begin : UserSessionServiceImpl.updateLogoutTime");
		try {
			UserSessionEntity userSessionEntity = userSessionDAO.getById(sessionId);
			if (userSessionEntity == null) {
				logger.info("Error while fetching user session entity ");
				return false;
			}
			try {
				userSessionEntity.setLogoutTime(Utils.convertDateToGMTDate(new Date()));
				userSessionEntity = (UserSessionEntity) userSessionDAO.update(userSessionEntity);
				if (userSessionEntity != null) {
					logger.info("End : UserSessionServiceImpl.updateLogoutTime");
					return true;
				}
			} catch (QSenseDAOException e1) {
				logger.error("Error while updating logout time of the session ");
				throw new QSenseServiceException(
						"Error while updating logout time of the session ", e1);
			}
		} catch (QSenseDAOException e) {
			logger.error("Error while fetching last processed record of user session ");
			throw new QSenseServiceException(
					"Error while fetching last processed record of user session ",
					e);
		}
		return false;
	}

}
