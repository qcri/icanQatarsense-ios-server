package com.qsense.service;

import java.util.List;

import com.qsense.transfer.LoginResponseTO;
import com.qsense.transfer.OryxTO;
import com.qsense.transfer.UserSimpleTO;
import com.qsense.transfer.UserTO;
import com.qsense.util.DeviceTypeEnum;

public interface UserService {
	
	UserTO create(UserTO userTO);
	
	Boolean delete(Long id);
	
	UserTO update(UserTO userTO);
	
	UserTO get(Long id);

	List<UserSimpleTO> getAll();

	UserTO findUserByUserName(String arg0);

	UserTO getUserById(long id);

	Boolean verifyAuthToken(String authToken, String userId);

	LoginResponseTO verifyLoginAndGenerateToken(String userName, String password, String permanentDeviceId, String timezone);
	
	LoginResponseTO generateNewAuthToken(Long userId, String oldAuthToken, String permanentDeviceId, String timezone);

	boolean removeAuthToken(Long userId, Long sessionId);

	List<UserTO> getAllUnAssociatedParticipant();

	void registerDevice(String userId, String deviceId, Boolean isStepCountSensorAvailable, Long sessionId, DeviceTypeEnum deviceType);

	Object getCoins(Long id);

	boolean updateCoins(Long userId, Long coins);

	boolean updateOryx(Long userId, Long oryxId);

	OryxTO getOryx(Long userId);

	List<OryxTO> getAllOryx();

	Object getCoinsForGroup(Long userId);

	Boolean verifyLogin(String userName, String password);
}
