package com.qsense.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qsense.adapter.UserEntityTOMapper;
import com.qsense.dao.DeviceTypeDAO;
import com.qsense.dao.OryxDAO;
import com.qsense.dao.RoleDAO;
import com.qsense.dao.UserDAO;
import com.qsense.entity.AppGroupEntity;
import com.qsense.entity.DeviceTypeEntity;
import com.qsense.entity.OryxEntity;
import com.qsense.entity.RoleEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserSessionEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.exception.QSenseServiceException;
import com.qsense.exception.QSenseUserExistException;
import com.qsense.exception.QSenseWebException;
import com.qsense.service.UserObservationService;
import com.qsense.service.UserService;
import com.qsense.service.UserSessionService;
import com.qsense.transfer.CoinsTO;
import com.qsense.transfer.LoginResponseTO;
import com.qsense.transfer.OryxTO;
import com.qsense.transfer.UserSimpleTO;
import com.qsense.transfer.UserTO;
import com.qsense.util.DeviceTypeEnum;
import com.qsense.util.RoleEnum;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
	private Logger logger = LogManager.getLogger(getClass());

	@Autowired(required = true)
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private OryxDAO oryxDAO;

	@Autowired
	private UserEntityTOMapper userEntityTOMapper;

	@Autowired
	private UserObservationService userObservationService;

	@Autowired
	private UserSessionService userSessionService;

	@Autowired
	private DeviceTypeDAO deviceTypeDAO;

	@Override
	public UserTO findUserByUserName(String userName) {
		logger.info("Begin : UserServiceImpl.findUserByUsername");
		UserEntity user = userDAO.findUserByUserName(userName);
		logger.info("End : UserServiceImpl.findUserByUsername");

		if (user != null) {
			return userEntityTOMapper.toDTO(user);
		}

		else {
			return null;
		}
	}

	@Override
	public UserTO getUserById(long id) {
		logger.info("Begin : UserServiceImpl.getUserById");
		UserEntity user = userDAO.findUserByUserId(id);
		logger.info("End : UserServiceImpl.getUserById");

		if (user != null) {
			return userEntityTOMapper.toDTO(user);
		}

		else {
			return null;
		}
	}

	@Override
	public Boolean verifyAuthToken(String authToken, String userId) {
		logger.info("Begin : UserServiceImpl.verifyAuthToken");
		boolean result = userDAO.verifyAuthToken(authToken, userId);
		logger.info("End : UserServiceImpl.verifyAuthToken");
		return result;
	}
	
	@Override
	public Boolean verifyLogin(String userName,String password) {
		logger.info("Begin : UserServiceImpl.verifyLogin");
		String md5Password = DigestUtils.md5Hex(password);
		boolean result = false;
		Long userId = userDAO.verifyLogin(userName, md5Password);
		if(userId != null){
			result = true;
		}
		logger.info("End : UserServiceImpl.verifyLogin");
		return result;
	}

	@Override
	public LoginResponseTO verifyLoginAndGenerateToken(String userName,
			String password, String permanentDeviceId, String timezone) {
		logger.info("Begin : UserServiceImpl.verifyLoginAndGenerateAuthToken");
		Long userId = userDAO.verifyLogin(userName, password);
		String authToken = "";
		LoginResponseTO loginResponseTO = null;
		if (userId != null) {
			UserEntity user = userDAO.getById(userId);
			if ((user.getAuthToken() != null)
					&& !StringUtils.isBlank(user.getPermanentDeviceId())
					&& (!(permanentDeviceId.equals(user.getPermanentDeviceId())))
					&& (!(user.getRole().getName() == RoleEnum.PARENT))) {
				loginResponseTO = new LoginResponseTO();
				loginResponseTO.setUserId(userId);
				loginResponseTO.setAuthToken(user.getAuthToken());
				loginResponseTO.setRoleName(user.getRole().getName());
				loginResponseTO.setGroupName(user.getGroup().getName());
				loginResponseTO.setUserName(user.getUserName());
				loginResponseTO.setFirstName(user.getFirstName());
				loginResponseTO.setLastName(user.getLastName());
				loginResponseTO.setIsDeviceChanged(true);
			} else {
				authToken = UUID.randomUUID().toString();
				userDAO.saveAuthToken(authToken, userName, permanentDeviceId);
				loginResponseTO = new LoginResponseTO();
				loginResponseTO.setAuthToken(authToken);
				loginResponseTO.setRoleName(user.getRole().getName());
				loginResponseTO.setGroupName(user.getGroup().getName());
				loginResponseTO.setUserId(userId);
				loginResponseTO.setUserName(user.getUserName());
				loginResponseTO.setFirstName(user.getFirstName());
				loginResponseTO.setLastName(user.getLastName());
				loginResponseTO.setIsDeviceChanged(false);
				try {
					user.setAuthToken(authToken);
					user.setPermanentDeviceId(permanentDeviceId);
					UserSessionEntity userSessionEntity = userSessionService
							.create(user, timezone);
					loginResponseTO.setSessionId(userSessionEntity.getId());
				} catch (QSenseServiceException e) {
					logger.error("Error while creating session for the user ");
					throw new QSenseWebException(String.format(
							"Error while creating session for the user ",
							e.getMessage()));
				}
			}

		} else {
			throw new QSenseWebException(String.format(
					"UserName %s with given password is wrong", userName));
		}
		logger.info("End : UserServiceImpl.verifyLoginAndGenerateAuthToken");
		return loginResponseTO;
	}

	@Override
	public LoginResponseTO generateNewAuthToken(Long userId,
			String oldAuthToken, String permanentDeviceId, String timezone) {
		logger.info("Begin : UserServiceImpl.generateNewAuthToken");
		String authToken = "";
		LoginResponseTO loginResponseTO = null;

		UserEntity user = userDAO.getById(userId);
		if (user != null) {
			if (oldAuthToken == null
					|| !(oldAuthToken.equals(user.getAuthToken()))) {
				throw new QSenseWebException(String.format(
						"UserName %s with given authToken is wrong",
						user.getUserName()));
			} else {
				authToken = UUID.randomUUID().toString();
				userDAO.saveAuthToken(authToken, user.getUserName(),
						permanentDeviceId);
				loginResponseTO = new LoginResponseTO();
				loginResponseTO.setAuthToken(authToken);
				loginResponseTO.setRoleName(user.getRole().getName());
				loginResponseTO.setGroupName(user.getGroup().getName());
				loginResponseTO.setUserId(userId);
				loginResponseTO.setUserName(user.getUserName());
				loginResponseTO.setFirstName(user.getFirstName());
				loginResponseTO.setLastName(user.getLastName());
				loginResponseTO.setIsDeviceChanged(false);
				try {
					user.setAuthToken(authToken);
					user.setPermanentDeviceId(permanentDeviceId);
					UserSessionEntity userSessionEntity = userSessionService
							.create(user, timezone);
					loginResponseTO.setSessionId(userSessionEntity.getId());
				} catch (QSenseServiceException e) {
					logger.error("Error while creating session for the user ");
					throw new QSenseWebException(String.format(
							"Error while creating session for the user ",
							e.getMessage()));
				}
			}
		} else {
			throw new QSenseWebException(String.format(
					"User does not exist with given UserId %l", userId));
		}

		logger.info("End : UserServiceImpl.generateNewAuthToken");
		return loginResponseTO;
	}

	@Override
	public UserTO create(UserTO userTO) {
		UserEntity userEntity = userEntityTOMapper.toEntity(userTO);
		boolean isUserExists = userDAO.getByUserName(userEntity.getUserName());

		if (isUserExists) {
			throw new QSenseUserExistException("Username :"
					+ userTO.getUserName() + " already exists");
		}

		RoleEntity userRole = roleDAO.getById(userEntity.getRole().getId());
		UserEntity associatedParticipantEntity = null;
		if (userEntity.getAssociatedParticipant() != null) {
			associatedParticipantEntity = userDAO.getById(userEntity
					.getAssociatedParticipant().getId());
		}

		if (userRole.getName() == RoleEnum.PARENT
				&& associatedParticipantEntity == null) {
			throw new QSenseServiceException(
					"Username :"
							+ userTO.getUserName()
							+ "  is a parent and it is not associated with any participant",
					null);

		} else if (userRole.getName() == RoleEnum.PARENT
				&& associatedParticipantEntity.getRole().getName() != RoleEnum.PARTICIPANT) {
			throw new QSenseServiceException(
					"Username :"
							+ userTO.getUserName()
							+ "  is a parent and it is not associated with a participant",
					null);

		} else {
			// Set the parent's group to associated participant's group
			if (userRole.getName() == RoleEnum.PARENT) {
				userEntity.setGroup(associatedParticipantEntity.getGroup());
			}
			userEntity.setPassword(DigestUtils.md5Hex(userTO.getPassword()));
			userEntity.setCoins(0L);
			userEntity = userDAO.create(userEntity);
			userTO = userEntityTOMapper.toDTO(userEntity);
		}

		return userTO;
	}

	@Override
	public Boolean delete(Long id) {
		UserEntity userEntity = userDAO.getById(id);
		return userDAO.remove(userEntity);
	}

	@Override
	public UserTO update(UserTO userTO) {
		UserEntity userEntity = userDAO.getById(userTO.getId());
		userEntity.setFirstName(userTO.getFirstName());
		userEntity.setLastName(userTO.getLastName());
		if (!userTO.getPassword().equals(userEntity.getPassword())) {
			userEntity.setPassword(DigestUtils.md5Hex(userTO.getPassword()));
		}
		userDAO.update(userEntity);
		userTO = userEntityTOMapper.toDTO(userEntity);
		return userTO;
	}

	@Override
	public UserTO get(Long id) {
		UserEntity userEntity = userDAO.getById(id);
		UserTO userTO = null;
		if (userEntity != null) {
			userTO = userEntityTOMapper.toDTO(userEntity);
		}
		return userTO;
	}

	@Override
	public List<UserSimpleTO> getAll() {
		List<UserEntity> all = userDAO.getAll();
		return userEntityTOMapper.toSimpleDTOs(all);
	}

	@Override
	public boolean removeAuthToken(Long userId, Long sessionId) {
		boolean result = false;
		try {
			result = userDAO.removeAuthToken(userId);
		} catch (QSenseDAOException e) {
			logger.error("Unable to remove authToken of the user ");
			throw new QSenseServiceException(
					"Unable to remove authToken of the user ", e);
		}
		// try{
		// if(result){
		// result =
		// userObservationService.updateEndTimestampOfActivityOnLogout(userId,
		// sessionId);
		// }
		// }
		// catch(QSenseDAOException e){
		// logger.error("Unable to update endTimestamp of the last activity of the user ");
		// throw new QSenseServiceException(
		// "Unable to update endTimestamp of the last activity of the user ",
		// e);
		// }
		try {
			if (result)
				result = userSessionService.updateLogoutTime(sessionId);
		} catch (QSenseDAOException e) {
			logger.error("Unable to update logout time of the user Session ");
			throw new QSenseServiceException(
					"Unable to update logout time of the user Session ", e);
		}
		return result;
	}

	@Override
	public List<UserTO> getAllUnAssociatedParticipant() {
		List<UserEntity> userEntities = userDAO.getAllUnAssociatedParticipant();
		return userEntityTOMapper.toDTO(userEntities);
	}

	@Override
	public void registerDevice(String userId, String deviceId,
			Boolean isStepCountSensorAvailable, Long sessionId,
			DeviceTypeEnum deviceType) {
		UserEntity user = userDAO.findUserByUserId(Long.parseLong(userId));
		DeviceTypeEntity deviceTypeEntity = deviceTypeDAO
				.findDeviceTypeByName(deviceType);
		if (user != null) {
			user.setDeviceId(deviceId);
			user.setDeviceType(deviceTypeEntity);
			userDAO.update(user);
		}
		userSessionService.updateDeviceId(sessionId, deviceId,
				isStepCountSensorAvailable, deviceTypeEntity);
	}

	@Override
	public Object getCoins(Long id) {
		UserEntity userEntity = userDAO.getById(id);
		CoinsTO coinsTO = new CoinsTO();
		coinsTO.setNumberOfCoins(userEntity.getCoins());
		
		return coinsTO;
	}

	@Override
	public Object getCoinsForGroup(Long id) {
		UserEntity userEntity = userDAO.getById(id);
		AppGroupEntity group = userEntity.getGroup();
		Long totalCoinsForGroup = userDAO.getCoinsForGroup(group);
		CoinsTO coinsTO = new CoinsTO();
		coinsTO.setNumberOfCoins(totalCoinsForGroup);
		
		return coinsTO;
	}
	
	@Override
	public boolean updateCoins(Long userId, Long coins) {
		UserEntity userEntity = userDAO.getById(userId);
		if(userEntity != null){
			userEntity.setCoins(coins);
			try{
				userDAO.update(userEntity);
				return true;
			} catch (QSenseDAOException e) {
				logger.error("Unable to update coins for the user "+userId);
				throw new QSenseServiceException(
						"Unable to update coins for the user ", e);
			}
		}else{
			return false;
		}
		
	}

	@Override
	public boolean updateOryx(Long userId, Long oryxId) {
		UserEntity userEntity = userDAO.getById(userId);
		
		if(userEntity != null){
			OryxEntity oryxEntity = new OryxEntity();
			oryxEntity.setId(oryxId);
			userEntity.setOryx(oryxEntity);
			try{
				userDAO.update(userEntity);
				return true;
			} catch (QSenseDAOException e) {
				logger.error("Unable to update oryx for the user "+userId);
				throw new QSenseServiceException(
						"Unable to update oryx for the user ", e);
			}
		}else{
			return false;
		}
	}
	
	@Override
	public OryxTO getOryx(Long id) {
		UserEntity userEntity = userDAO.getById(id);
		
		if(userEntity != null){
			OryxEntity oryxEntity = userEntity.getOryx();
			OryxTO oryxTO = new OryxTO();
			if(oryxEntity != null){
				oryxTO.setDisplayName(oryxEntity.getDisplayName());
				oryxTO.setPrice(oryxEntity.getPrice());
				oryxTO.setIconSource(oryxEntity.getIconSource());
				oryxTO.setId(oryxEntity.getId());
				oryxTO.setName(oryxEntity.getName());
			}
			return oryxTO;
		}
		return null;
	}

	@Override
	public List<OryxTO> getAllOryx() {
		List<OryxEntity> oryxEntities = oryxDAO.getAll();
		
		List<OryxTO> oryxTOs = new ArrayList<OryxTO>();
		for (OryxEntity oryxEntity : oryxEntities) {
			OryxTO oryxTO = new OryxTO();
			oryxTO.setDisplayName(oryxEntity.getDisplayName());
			oryxTO.setIconSource(oryxEntity.getIconSource());
			oryxTO.setPrice(oryxEntity.getPrice());
			oryxTO.setName(oryxEntity.getName());
			oryxTO.setId(oryxEntity.getId());
			
			oryxTOs.add(oryxTO);
		}
		return oryxTOs;
	}
}
