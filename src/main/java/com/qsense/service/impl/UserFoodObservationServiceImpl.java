package com.qsense.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.dao.ObservationBlockDAO;
import com.qsense.dao.UserDAO;
import com.qsense.dao.UserFoodObservationDAO;
import com.qsense.dao.UserSessionDAO;
import com.qsense.entity.FoodSubTypeEntity;
import com.qsense.entity.FoodTypeEntity;
import com.qsense.entity.ObservationBlockEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserFoodObservationEntity;
import com.qsense.entity.UserSessionEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.exception.QSenseServiceException;
import com.qsense.service.UserFoodObservationService;
import com.qsense.transfer.FoodSubTypeTO;
import com.qsense.transfer.FoodTypeTO;
import com.qsense.transfer.ObservationUnitTO;
import com.qsense.transfer.UserFoodObservationBlockTO;
import com.qsense.transfer.UserFoodObservationTO;
import com.qsense.util.Constants;
import com.qsense.util.TimeUtils;
import com.qsense.util.UserFoodObservationComparator;
import com.qsense.util.Utils;
import com.qsense.util.logger.QSenseLogger;

/**
 * @author Sanjay
 * 
 */

@Service("UserFoodObservationService")
@Transactional
public class UserFoodObservationServiceImpl implements UserFoodObservationService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	private UserFoodObservationDAO userFoodObservationDAO;
	
	@Autowired
	private ObservationBlockDAO observationBlockDAO;
	
	QSenseLogger logger = QSenseLogger.getLogger(getClass());

	
	@Override
	public Boolean createUserFoodObservation(Long userId, String fromTimezoneId,
			UserFoodObservationBlockTO userFoodObservationBlockTO)
			throws QSenseServiceException, ParseException {
		boolean result = false;
		

		try {
			UserSessionEntity userSessionEntity = userSessionDAO
					.getById(userFoodObservationBlockTO.getSessionId());
			
			if (userSessionEntity == null) {
				result = true;
				return result;
			}
			
			UserEntity userEntity = new UserEntity();
			userEntity.setId(userId);
			
			List<UserFoodObservationTO> userFoodObservationList = userFoodObservationBlockTO.getFoodObservations();
			
			List<UserFoodObservationEntity> userFoodObservationEntities = new ArrayList<UserFoodObservationEntity>();
			
			for (UserFoodObservationTO userFoodObservationTO : userFoodObservationList) {
				
				FoodSubTypeEntity foodSubTypeEntity = new FoodSubTypeEntity();
				foodSubTypeEntity.setId(userFoodObservationTO.getFoodSubType().getId());
				
				Date time = TimeUtils.parseFormattedDate(userFoodObservationTO.getTime(),Constants.TIMESTAMP_FORMAT, fromTimezoneId);
				time = Utils.convertDateToGMTDate(time);
				
				UserFoodObservationEntity userFoodObservationEntity = new UserFoodObservationEntity();
				userFoodObservationEntity.setUser(userEntity);
				userFoodObservationEntity.setFoodSubType(foodSubTypeEntity);
				userFoodObservationEntity.setFoodServingSize(userFoodObservationTO.getFoodServingSize());
				userFoodObservationEntity.setSessionRecordId(userFoodObservationTO.getSessionRecordId());
				userFoodObservationEntity.setUserSession(userFoodObservationBlockTO.getSessionId());
				userFoodObservationEntity.setTime(time);
				
				userFoodObservationEntities.add(userFoodObservationEntity);
				}
			
				Collections.sort(userFoodObservationEntities,
					new UserFoodObservationComparator());
				
				for(UserFoodObservationEntity userFoodObservationEntity : userFoodObservationEntities){
					userFoodObservationDAO.create(userFoodObservationEntity);
				}
				result = true;
			
				// Added entries for the observation block entity
				ObservationBlockEntity observationBlockEntity = new ObservationBlockEntity();
				observationBlockEntity.setSessionId(userFoodObservationBlockTO.getSessionId());
				observationBlockEntity.setBlockId(userFoodObservationBlockTO.getBlockId());
				observationBlockEntity.setRecordStartId(userFoodObservationBlockTO.getSessionRecordStartId());
				observationBlockEntity.setRecordEndId(userFoodObservationBlockTO.getSessionRecordEndId());
				
				try {
					observationBlockDAO.create(observationBlockEntity);
				} catch (QSenseDAOException e) {
					if (e.getMessage().toLowerCase()
							.contains("ConstraintViolationException".toLowerCase())) {
						logger.info(
								"Unable to create user Observations because this block had already been synced",
								e.getMessage());
						result = true;
						throw new QSenseServiceException(
								"ConstraintViolationException in observationBlock Table",
								e);
					}
				}
				result = true;
		} catch (QSenseDAOException e) {
			logger.error("Unable to create user food observations", e.getMessage());
			result = false;
			throw new QSenseServiceException(
					"Unable to create user food observations", e);

		}
		return result;
	}


	@Override
	public List<UserFoodObservationTO> getAllLatestByUserIdAndFromDate(Long userId, String fromDate, String timezoneId) throws QSenseServiceException, ParseException {
		
		Date fromDateByUser;
		if(StringUtils.isNotEmpty(fromDate)){
			fromDateByUser = TimeUtils.parseFormattedDate(fromDate, Constants.DATE_FORMAT, timezoneId);
		}
		else{
			fromDateByUser = TimeUtils.dateStartTime(new Date());
		}

		Date fromDateInGMT = Utils.convertDateToGMTDate(fromDateByUser);

		List<UserFoodObservationEntity> userFoodObservationList = userFoodObservationDAO.getAllByUserIdAndFromDate(userId,fromDateInGMT);
		
		List<UserFoodObservationTO> userFoodObservationTOs = new ArrayList<UserFoodObservationTO>();
		
		for (UserFoodObservationEntity userFoodObservationEntity : userFoodObservationList) {
			userFoodObservationTOs.add(toDTO(userFoodObservationEntity, timezoneId));
		}
		
		return userFoodObservationTOs;
	}
	
	public UserFoodObservationTO toDTO(UserFoodObservationEntity entity, String timezone) {
		UserFoodObservationTO userFoodObservationTO = new UserFoodObservationTO();
		
		FoodSubTypeEntity foodSubTypeEntity = entity.getFoodSubType();
		FoodSubTypeTO foodSubType = new FoodSubTypeTO();
		foodSubType.setId(foodSubTypeEntity.getId());
		foodSubType.setName(foodSubTypeEntity.getName());
		foodSubType.setIconSource(foodSubTypeEntity.getIconSource());
		foodSubType.setDisplayName(foodSubTypeEntity.getDisplayName());
		
		ObservationUnitTO observationUnit = new ObservationUnitTO();
		observationUnit.setName(foodSubTypeEntity.getObservationUnit().getName());
		
		FoodTypeEntity foodType = foodSubTypeEntity.getFoodType();
		FoodTypeTO foodTypeTO = new FoodTypeTO();
		foodTypeTO.setId(foodType.getId());
		foodTypeTO.setName(foodType.getName());
		foodTypeTO.setPrice(foodType.getPrice());
		foodSubType.setFoodTypeTO(foodTypeTO);
		
		foodSubType.setObservationUnit(observationUnit);
		userFoodObservationTO.setFoodSubType(foodSubType);
		
		Date GMTTimeToLocalTime = Utils.convertGMTDateToLocalDate(entity.getTime());
		String dateInUserTimeZone = Utils.convertDate(GMTTimeToLocalTime, Constants.TIMESTAMP_FORMAT, timezone);
		
		userFoodObservationTO.setTime(dateInUserTimeZone);
		userFoodObservationTO.setFoodServingSize(entity.getFoodServingSize());
		userFoodObservationTO.setSessionId(entity.getUserSession().getId());
		userFoodObservationTO.setSessionRecordId(entity.getSessionRecordId());
		
		return userFoodObservationTO;
	}
}
