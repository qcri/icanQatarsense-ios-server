package com.qsense.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.dao.AppGroupDAO;
import com.qsense.dao.CategoryDAO;
import com.qsense.dao.GoalAttributesDAO;
import com.qsense.dao.ObservationBlockDAO;
import com.qsense.dao.SubCategoryDAO;
import com.qsense.dao.UserDAO;
import com.qsense.dao.UserFoodObservationDAO;
import com.qsense.dao.UserGoalDAO;
import com.qsense.dao.UserObservationDAO;
import com.qsense.dao.UserSessionDAO;
import com.qsense.entity.AppGroupEntity;
import com.qsense.entity.GoalAttributesEntity;
import com.qsense.entity.ObservationBlockEntity;
import com.qsense.entity.SubCategoryEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserGoalEntity;
import com.qsense.entity.UserObservationEntity;
import com.qsense.entity.UserSessionEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.exception.QSenseServiceException;
import com.qsense.service.UserObservationService;
import com.qsense.transfer.ActiveUsersTO;
import com.qsense.transfer.DashboardTO;
import com.qsense.transfer.LeaderboardForWebTO;
import com.qsense.transfer.LeaderboardTO;
import com.qsense.transfer.MainMenuTO;
import com.qsense.transfer.ObservationBlockTO;
import com.qsense.transfer.UserObservationTO;
import com.qsense.util.CategoryTypeEnum;
import com.qsense.util.Constants;
import com.qsense.util.DeviceTypeEnum;
import com.qsense.util.FoodTypeEnum;
import com.qsense.util.LeaderboardTOComparator;
import com.qsense.util.RoleEnum;
import com.qsense.util.SubCategoryEnum;
import com.qsense.util.TimeUtils;
import com.qsense.util.UserObservationComparator;
import com.qsense.util.Utils;
import com.qsense.util.logger.QSenseLogger;

/**
 * @author Kushalkant
 * 
 */

@Service("UserObservationService")
@Transactional
public class UserObservationServiceImpl implements UserObservationService {

	@Autowired
	private UserObservationDAO userObservationDAO;
	
	@Autowired
	private UserFoodObservationDAO userFoodObservationDAO;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private GoalAttributesDAO goalAttributesDAO;

	@Autowired
	private UserGoalDAO userGoalDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private SubCategoryDAO subCategoryDAO;

	@Autowired
	private UserSessionDAO userSessionDAO;

	@Autowired
	private ObservationBlockDAO observationBlockDAO;
	
	@Autowired
	private AppGroupDAO appGroupDAO;

	QSenseLogger logger = QSenseLogger.getLogger(getClass());

	// Method to get dashboard observations
	@Override
	public List<DashboardTO> getObservationsforDashboard(Long userId,
			String dateInUserTimezoneAsString, String userTimezoneId)
			throws QSenseServiceException, ParseException {
		Date dateInUserTimezone = TimeUtils.parseFormattedDate(
				dateInUserTimezoneAsString, Constants.DATE_FORMAT,
				userTimezoneId);
		String dateAsStringInGMT = Utils.convertDate(dateInUserTimezone,
				Constants.TIMESTAMP_FORMAT, "GMT");
		Date nextDate = TimeUtils.getNextDate(dateInUserTimezone);
		String nextDateAsStringInGMT = Utils.convertDate(nextDate,
				Constants.TIMESTAMP_FORMAT, "GMT");

		Date dateInGMT = TimeUtils.parseFormattedDate(dateAsStringInGMT,
				Constants.TIMESTAMP_FORMAT);
		Date nextDateInGMT = TimeUtils.parseFormattedDate(
				nextDateAsStringInGMT, Constants.TIMESTAMP_FORMAT);
		List<DashboardTO> dashboardTOs = new ArrayList<DashboardTO>();
		UserEntity user = userDAO.getById(userId);
		UserEntity childUser = null;
		if (user == null) {
			return dashboardTOs;
		} else {
			if (user.getRole().getName() == RoleEnum.PARENT) {
				childUser = user.getAssociatedParticipant();
			} else {
				childUser = user;
			}
		}
		if (childUser == null) {
			return dashboardTOs;
		}
		try {
			List activityObservations = userObservationDAO
					.getAllObservationsForDashboard(childUser.getId(),
							dateAsStringInGMT, nextDateAsStringInGMT);

			DashboardTO dashboardTO;
			Long totalDuration = 0L;

			for (Object observation : activityObservations) {
				Object[] obj = (Object[]) observation;
				dashboardTO = new DashboardTO();
				dashboardTO.setSubCategoryName(obj[0].toString());
				dashboardTO.setSubCategoryDisplayName(obj[4].toString());
				dashboardTO.setColor(obj[1].toString());
				dashboardTO.setUnits(obj[2].toString());
				if (obj[3] != null) {
					Long totalSeconds = Math.round(Double.parseDouble(obj[3]
							.toString()));
					dashboardTO.setValue1(totalSeconds);
					dashboardTO.setValue1String(TimeUtils
							.dateTimeFormatterForQsense(totalSeconds, 3));
					totalDuration += totalSeconds;
				} else {
					dashboardTO.setValue1(0L);
					dashboardTO.setValue1String("0s");
				}
				dashboardTOs.add(dashboardTO);
			}

			// Add percentage value of every activity in dashboardTO
			for (DashboardTO activityDashboardTO : dashboardTOs) {
				double value1Percent = 0.0;
				if(totalDuration != 0L){
					value1Percent = (((double) activityDashboardTO
							.getValue1()) / totalDuration) * 100;
					value1Percent = Utils.round(value1Percent, 2);
				}
				activityDashboardTO.setValue1Percentage(value1Percent + "%");
			}

			// Total Active Time
			dashboardTO = new DashboardTO();
			dashboardTO.setSubCategoryName("TOTAL_ACTIVE_TIME");
			dashboardTO.setSubCategoryDisplayName("Total Active Time");
			dashboardTO.setColor("#FFFFFF");
			dashboardTO.setValue1(totalDuration);
			dashboardTO.setValue1String(TimeUtils.dateTimeFormatterForQsense(
					totalDuration, 3));
			dashboardTOs.add(dashboardTO);

			// For Step Count
			dashboardTO = new DashboardTO();

			SubCategoryEntity subCategoryEntity = subCategoryDAO
					.getByName("STEP_COUNT");
			if (subCategoryEntity.isDashboardVisible()) {
				Double totalStepCounts = userObservationDAO
						.getStepCountForDashboardStatus(childUser, dateInGMT,
								nextDateInGMT, subCategoryEntity);
				dashboardTO.setSubCategoryName(subCategoryEntity.getName());
				dashboardTO.setSubCategoryDisplayName(subCategoryEntity
						.getDisplayName());
				dashboardTO.setColor(subCategoryEntity.getColor());
				dashboardTO.setUnits(subCategoryEntity.getUnits());
				if (totalStepCounts != null) {
					dashboardTO.setValue1(Math.round(totalStepCounts));
				} else {
					dashboardTO.setValue1(0L);
				}

				dashboardTOs.add(dashboardTO);
			}

			// For Luminescence
			dashboardTO = new DashboardTO();
			subCategoryEntity = subCategoryDAO.getByName("LUMINESCENCE");
			if (subCategoryEntity.isDashboardVisible()) {
				Double averageLuminescence = userObservationDAO
						.getLuminescenceForDashboardStatus(childUser,
								dateInGMT, nextDateInGMT, subCategoryEntity);
				dashboardTO.setSubCategoryName(subCategoryEntity.getName());
				dashboardTO.setSubCategoryDisplayName(subCategoryEntity
						.getDisplayName());
				dashboardTO.setColor(subCategoryEntity.getColor());
				dashboardTO.setUnits(subCategoryEntity.getUnits());
				if (averageLuminescence != null) {
					dashboardTO.setValue1(Math.round(averageLuminescence));
				} else {
					dashboardTO.setValue1(0L);
				}
				dashboardTOs.add(dashboardTO);
			}

			return dashboardTOs;
		} catch (QSenseDAOException e) {
			logger.error("Error while fetching records from DB", e.getMessage());
			throw new QSenseServiceException(
					"Error while fetching records from DB", e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qsense.service.UserObservationService#createUserObservation(java.
	 * lang.Long, java.lang.String, com.qsense.transfer.ObservationBlockTO)
	 */
	@Override
	public boolean createUserObservation(Long userId, String fromTimezoneId,
			ObservationBlockTO observationBlockTO)
			throws QSenseServiceException, ParseException {
		boolean result = false;
		try {
			List<UserObservationTO> userObservationTOsList = observationBlockTO
					.getUserObservations();
			// userObservationEntitiesListforStepCount
			List<UserObservationEntity> userObservationEntitiesListforStepCount = new ArrayList<UserObservationEntity>();
			// Getting session id of the activities
			UserSessionEntity userSessionEntity = userSessionDAO
					.getById(observationBlockTO.getSessionId());

			/* If sesssion Id of a user does not exist and some data came from user then just return the true to the user 
			 * but its data won't be persist as session Id didn't exist
			 * 
			 * */
			/* TODO to think on this later that what happens in the case when user's current session just got deleted from the 
			 * user session table and the user won't be able to know about it
			 */
			if (userSessionEntity == null) {
				result = true;
				return result;
			}
			DeviceTypeEnum deviceType = userSessionEntity.getDeviceType().getName();
			
			// Fetching all subcategories list to create a map of subcategories
			// with their respective category
			List<SubCategoryEntity> subCategoryEntities = subCategoryDAO.getAll();
			List<SubCategoryEntity> subCategoryEntitiesOfCategoryActivity = new ArrayList<SubCategoryEntity>();
			HashMap<String, SubCategoryEntity> subCategoryNameMapWithSubCategoryEntity = new HashMap<String, SubCategoryEntity>();
			for (SubCategoryEntity subCategoryEntity : subCategoryEntities) {
				subCategoryNameMapWithSubCategoryEntity.put(
						subCategoryEntity.getName(), subCategoryEntity);
				
				if(subCategoryEntity.getCategory().getName().equals(CategoryTypeEnum.ACTIVITY)){
					subCategoryEntitiesOfCategoryActivity.add(subCategoryEntity);
				}
				
			}
			// Creating two seperate list for Observations of type Activity and
			// Others( like status or sensor readings...since they are not
			// having end timestamp)
			List<UserObservationEntity> userObservationEntitiesForActivity = new ArrayList<UserObservationEntity>();
			List<UserObservationEntity> userObservationEntitiesForOthers = new ArrayList<UserObservationEntity>();

			// Fetching sessionId of the activities;
			Long sessionId = userSessionEntity.getId();
			UserEntity userEntity = new UserEntity();
			userEntity.setId(userId);
			// Fetching xclusive sessionEntity of the current session
			UserSessionEntity exclusiveSessionEntity = userSessionDAO
					.getExclusiveSession(userEntity, sessionId);

			// To set endTimestamp of last activity of previous session only if it is having activities lies 
			// after another session start time
			boolean observationsExistPostExclusiveSession = false; 
			//Get Last record start Timestamp of the current session to check whether any record 
			//exist in current block that is before that start timestamp  
			UserObservationEntity lastProcessedRecordBySession = null;
			if(deviceType.equals(DeviceTypeEnum.IPHONE)){
				lastProcessedRecordBySession = userObservationDAO.getLastProcessedRecordBySession(userEntity, subCategoryEntitiesOfCategoryActivity, userSessionEntity);
			}
			else{
				lastProcessedRecordBySession = userObservationDAO.getLastProcessedRecordBySession(userEntity, userSessionEntity);
			}
			Date lastProcessedStartTimestamp =null;
			if(lastProcessedRecordBySession!=null){
				lastProcessedStartTimestamp = lastProcessedRecordBySession.getStartTimestamp();
			}
			// Parsing observationTos and seperate out by category type
			for (UserObservationTO userObservationTO : userObservationTOsList) {
				UserObservationEntity userObservationEntity = new UserObservationEntity();
				userObservationEntity.setUser(userEntity);
				SubCategoryEntity subCategoryEntity = subCategoryNameMapWithSubCategoryEntity
						.get(userObservationTO.getSubCategoryName());
				userObservationEntity.setSubCategory(subCategoryEntity);
				Date startTimeStamp1 = TimeUtils.parseFormattedDate(
						userObservationTO.getStartTimestamp(),
						Constants.TIMESTAMP_FORMAT, fromTimezoneId);
				Date startTimeStamp = Utils
						.convertDateToGMTDate(startTimeStamp1);
				userObservationEntity.setStartTimestamp(startTimeStamp);
	//////////////////////////
				if(userObservationTO.getEndTimestamp() !=null){
					Date endTimeStamp1 = TimeUtils.parseFormattedDate(
							userObservationTO.getEndTimestamp(),
							Constants.TIMESTAMP_FORMAT, fromTimezoneId);
					Date endTimeStamp = Utils
							.convertDateToGMTDate(endTimeStamp1);
					userObservationEntity.setEndTimestamp(endTimeStamp);
				}
				
	/////////////////////////
				
				if(userObservationTO.getIsManual() != null){
					userObservationEntity.setIsManual(userObservationTO.getIsManual());
				}else{
					userObservationEntity.setIsManual(false);
				}
				userObservationEntity.setField1(userObservationTO.getField1());
				userObservationEntity.setField2(userObservationTO.getField2());
				userObservationEntity.setField3(userObservationTO.getField3());
				userObservationEntity.setSessionRecordId(userObservationTO
						.getSessionRecordId());
				userObservationEntity.setUserSession(observationBlockTO
						.getSessionId());

				// Only add observations in the list when current activity
				// timestamp is before the next session login time
				// or its start timestamp is after the last record start timestamp recorded in the database for that session

				if(lastProcessedStartTimestamp!=null){
					if(!deviceType.equals(DeviceTypeEnum.IPHONE)){
						if(userObservationEntity.getStartTimestamp().before(lastProcessedStartTimestamp)){
							continue;
						}
					}
					/*else{
						if (subCategoryEntity.getCategory().getName().equals(CategoryTypeEnum.ACTIVITY)){
							if(userObservationEntity.getStartTimestamp().before(lastProcessedStartTimestamp)){
								continue;
							}
						}
					}*/
				}
				if (exclusiveSessionEntity != null && !userObservationEntity.getIsManual()) {
					if (userObservationEntity.getStartTimestamp().before(
							exclusiveSessionEntity.getLoginTime())) {
						if (subCategoryEntity.getCategory().getName()
								.equals(CategoryTypeEnum.ACTIVITY)) {
							userObservationEntitiesForActivity
									.add(userObservationEntity);
						} else {
							userObservationEntitiesForOthers
									.add(userObservationEntity);
						}
					} else {
						observationsExistPostExclusiveSession = true;
					}
				}

				else {
					if (subCategoryEntity.getCategory().getName()
							.equals(CategoryTypeEnum.ACTIVITY)) {
						userObservationEntitiesForActivity
								.add(userObservationEntity);
					} else {
						userObservationEntitiesForOthers
								.add(userObservationEntity);
					}
				}
			}
			
			// Sorting observations on the basis of their Start Timestamp
			Collections.sort(userObservationEntitiesForActivity,
					new UserObservationComparator());
			Collections.sort(userObservationEntitiesForOthers,
					new UserObservationComparator());
			
			// Persisting observations of category type other than Activity
			for (UserObservationEntity entity : userObservationEntitiesForOthers) {
				userObservationDAO.create(entity);
			}
			// Fetching the list of all sub categories of type ACTIVITY
//			CategoryEntity categoryEntityOfTypeActivity = categoryDAO
//					.getByName(CategoryTypeEnum.ACTIVITY);
//			List<SubCategoryEntity> subCategoryEntitiesOfCategoryActivity = subCategoryDAO
//					.getAllSubCategoriesByCategory(categoryEntityOfTypeActivity);
			if (userObservationEntitiesForActivity.size() > 0) {
				// Fetching last processed userActivity to set its end timestamp
				// with the current list startTimestamp
				UserObservationEntity lastProcessedUserObservationEntity = userObservationDAO
						.getLastProcessedRecordBySession(userEntity,
								subCategoryEntitiesOfCategoryActivity,
								userSessionEntity);
				if (lastProcessedUserObservationEntity != null || !deviceType.equals(DeviceTypeEnum.IPHONE)) {
					Date endTimestamp = userObservationEntitiesForActivity.get(
							0).getStartTimestamp();
					lastProcessedUserObservationEntity.setEndTimestamp(endTimestamp);
					Date startTimestamp = lastProcessedUserObservationEntity
							.getStartTimestamp();

					UserObservationEntity entity = null;
					if (TimeUtils.dateEndTime(startTimestamp).before(
							TimeUtils.dateEndTime(endTimestamp))) {

						Date newEndTimestamp = TimeUtils
								.dateEndTime(startTimestamp);
						lastProcessedUserObservationEntity.setEndTimestamp(newEndTimestamp);
						userObservationDAO.update(lastProcessedUserObservationEntity);
						userObservationEntitiesListforStepCount
								.add(lastProcessedUserObservationEntity);
						Date newStartTimestamp = TimeUtils
								.nextDateStartTime(startTimestamp);
						while (TimeUtils.dateEndTime(newStartTimestamp).before(
								TimeUtils.dateEndTime(endTimestamp))) {
							newEndTimestamp = TimeUtils
									.dateEndTime(newStartTimestamp);
							entity = new UserObservationEntity(
									lastProcessedUserObservationEntity, newStartTimestamp,
									newEndTimestamp);
							userObservationDAO.create(entity);
							userObservationEntitiesListforStepCount.add(entity);
							newStartTimestamp = TimeUtils
									.nextDateStartTime(newStartTimestamp);
						}
						entity = new UserObservationEntity(
								lastProcessedUserObservationEntity, newStartTimestamp,
								endTimestamp);
						userObservationDAO.create(entity);
						userObservationEntitiesListforStepCount.add(entity);
					} else {
						userObservationDAO.update(lastProcessedUserObservationEntity);
						userObservationEntitiesListforStepCount
								.add(lastProcessedUserObservationEntity);
					}
				}
				
				
				//Persisting for Android
				if(!deviceType.equals(DeviceTypeEnum.IPHONE)){
					UserObservationEntity userObservationEntity2 = userObservationEntitiesForActivity
							.get(0);
	
					if (userObservationEntitiesForActivity.size() > 1) {
						// Set the end timestamp of first activity with the next
						// activity start timestamp
						UserObservationEntity userObservationEntity1 = null;
						userObservationEntity2 = null;
						for (int userObservationEntityIndex = 0; userObservationEntityIndex < userObservationEntitiesForActivity
								.size() - 1; userObservationEntityIndex++) {
							userObservationEntity1 = userObservationEntitiesForActivity
									.get(userObservationEntityIndex);
							userObservationEntity2 = userObservationEntitiesForActivity
									.get(userObservationEntityIndex + 1);
							Date endTimestamp = userObservationEntity2
									.getStartTimestamp();
							Date startTimestamp = userObservationEntity1
									.getStartTimestamp();
	
							userObservationEntity1 = persistUserObservationEntity(
									startTimestamp, endTimestamp,
									userObservationEntity1,
									userObservationEntitiesListforStepCount);
							if (userObservationEntity1 == null) {
								return false;
							}
						}
					}
					
					// This entity will not have endTimestamp since it is the last
					// activity and we are fetching end timestamp
					// from the next activity's start_timestamp
				
					if (exclusiveSessionEntity != null
							&& observationsExistPostExclusiveSession) {
						userObservationEntity2 = persistUserObservationEntity(
								userObservationEntity2.getStartTimestamp(),
								exclusiveSessionEntity.getLoginTime(),
								userObservationEntity2,
								userObservationEntitiesListforStepCount);
					} else {
						userObservationDAO.create(userObservationEntity2);
						userObservationEntitiesListforStepCount
								.add(userObservationEntity2);
					}
				}else{
					//Persisting for iphone
					for (UserObservationEntity userObservationEntity : userObservationEntitiesForActivity) {
						
						UserObservationEntity iphoneUserObservationEntity = persistUserObservationEntity(
								userObservationEntity.getStartTimestamp(), userObservationEntity.getEndTimestamp(),
								userObservationEntity, userObservationEntitiesListforStepCount);
						if (iphoneUserObservationEntity == null) {
							return false;
						}
					}
				}
			}
			
			
			if (userSessionEntity.getIsStepCountSensorAvailable() == Boolean.FALSE
					&& userObservationEntitiesListforStepCount.size() > 1) {
				List<UserObservationEntity> stepCountEntries = getStepCountEntries(
						userObservationEntitiesListforStepCount,
						subCategoryNameMapWithSubCategoryEntity,
						observationsExistPostExclusiveSession);
				for (UserObservationEntity stepCountEntity : stepCountEntries) {
					userObservationDAO.create(stepCountEntity);
				}
			}

			// Added entries for the observation block entity
			ObservationBlockEntity observationBlockEntity = new ObservationBlockEntity();
			observationBlockEntity.setSessionId(observationBlockTO
					.getSessionId());
			observationBlockEntity.setBlockId(observationBlockTO.getBlockId());
			observationBlockEntity.setRecordStartId(observationBlockTO
					.getSessionRecordStartId());
			observationBlockEntity.setRecordEndId(observationBlockTO
					.getSessionRecordEndId());
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
			logger.error("Unable to create user Observations", e.getMessage());
			result = false;
			throw new QSenseServiceException(
					"Unable to create user Observations", e);

		}
		return result;
	}

	// Method to check wheather any activity lies in two different dates or not
	// and if lies in two different dates
	// then it breaks the activity upto day end and start another activity from
	// next day
	private UserObservationEntity persistUserObservationEntity(
			Date startTimestamp, Date endTimestamp,
			UserObservationEntity userObservationEntity,
			List<UserObservationEntity> userObservationEntitiesListforStepCount)
			throws QSenseServiceException {
		try {
			UserObservationEntity observationEntity;
			if (TimeUtils.dateEndTime(startTimestamp).before(
					TimeUtils.dateEndTime(endTimestamp))) {
				while (TimeUtils.dateEndTime(startTimestamp).before(
						TimeUtils.dateEndTime(endTimestamp))) {
					Date newEndTimestamp = TimeUtils
							.dateEndTime(startTimestamp);
					observationEntity = new UserObservationEntity(
							userObservationEntity, startTimestamp,
							newEndTimestamp);
					userObservationDAO.create(observationEntity);
					userObservationEntitiesListforStepCount
							.add(observationEntity);
					startTimestamp = TimeUtils
							.nextDateStartTime(startTimestamp);
				}

				observationEntity = new UserObservationEntity(
						userObservationEntity, startTimestamp, endTimestamp);
				userObservationDAO.create(observationEntity);
				userObservationEntitiesListforStepCount.add(observationEntity);
			} else {
				observationEntity = new UserObservationEntity(
						userObservationEntity, startTimestamp, endTimestamp);
				userObservationDAO.create(observationEntity);
				userObservationEntitiesListforStepCount.add(observationEntity);
			}

		} catch (QSenseDAOException e) {
			logger.error("Unable to create user Observations", e.getMessage());
			throw new QSenseServiceException(
					"Unable to create user Observations", e);
		}
		return userObservationEntity;
	}

	// Method to get leaderboard observations
	@Override
	public List<LeaderboardTO> getObservationsforLeaderBoard(Long userId,
			String dateInIncomingTimezoneAsString,
			String fromTimezoneId, int noOfDays)
			throws QSenseServiceException, ParseException {

		Date dateInIncomingTimezone = TimeUtils.parseFormattedDate(
				dateInIncomingTimezoneAsString, Constants.DATE_FORMAT,
				fromTimezoneId);
		Date toDate = TimeUtils.getNextDate(dateInIncomingTimezone);
		Date fromDate = TimeUtils.subtractDate(toDate, noOfDays);

		String fromDateInGMT = Utils.convertDate(fromDate,
				Constants.TIMESTAMP_FORMAT, "GMT");

		String toDateInGMT = Utils.convertDate(toDate,
				Constants.TIMESTAMP_FORMAT, "GMT");

		// Return list of leaderboardTOs
		List<LeaderboardTO> returnLeaderboardTOs = new ArrayList<LeaderboardTO>();

		try{
	
		List<SubCategoryEntity> subCategoryEntities = subCategoryDAO.getAll();
		SubCategoryEntity stepCountEntity = null;
		HashMap<Long, LeaderboardTO> subCategoryNameToLeaderBoardTOMap = new HashMap<Long, LeaderboardTO>();
		String leaderboardVisibleActivityIdsString ="";
		LeaderboardTO leaderboardTO = null;
		for (SubCategoryEntity subCategoryEntity : subCategoryEntities) {
			if (subCategoryEntity.getName().equals("STEP_COUNT")) {
				stepCountEntity = subCategoryEntity;
			}
			// Not adding any activity to subCategoryNameToLeaderBoardTOMap , if
			// that activity's visibility is false in database
			if ((subCategoryEntity.getCategory().getName() == CategoryTypeEnum.ACTIVITY)
					&& (subCategoryEntity.isLeaderboardVisible() == true)) {
				leaderboardTO = new LeaderboardTO(subCategoryEntity);
				subCategoryNameToLeaderBoardTOMap.put(
						subCategoryEntity.getId(), leaderboardTO);
				if(leaderboardVisibleActivityIdsString.length()!=0){
					leaderboardVisibleActivityIdsString += ",";
				}
				leaderboardVisibleActivityIdsString += subCategoryEntity.getId();
			}
		}

		// fetch GroupId of user
			Long groupId = null;
			UserEntity userEntity = userDAO.getById(userId);

			if (userEntity == null) {
				return returnLeaderboardTOs;
			} else {
				if (userEntity.getRole().getName() == RoleEnum.PARENT) {
					userEntity = userEntity.getAssociatedParticipant();
				}
			}

			if (userEntity != null) {
				userId = userEntity.getId();
				groupId = userEntity.getGroup().getId();
			} else {
				return returnLeaderboardTOs;
			}

			List observationdurations = userObservationDAO
					.getActivitiesForLeaderboard(userId, groupId,
							fromDateInGMT, toDateInGMT, leaderboardVisibleActivityIdsString);
			// Adding group's duration in map
			for (Object observationduration : observationdurations) {

				Object[] obj = (Object[]) observationduration;

				if (!(subCategoryNameToLeaderBoardTOMap.containsKey(Long.parseLong(obj[0]
						.toString())))) {
					continue;
				}
				if (Double.parseDouble(obj[1].toString()) != 0.0) {
					continue;
				}

				leaderboardTO = subCategoryNameToLeaderBoardTOMap.get(Long.parseLong(obj[0]
						.toString()));
				if (obj[2] != null) {
					leaderboardTO.setMin(Math.round(Double.parseDouble(obj[2]
							.toString())));
					leaderboardTO.setMinString(TimeUtils
							.dateTimeFormatterForQsense(leaderboardTO.getMin(),
									2));
				} else {
					leaderboardTO.setMin(0L);
					leaderboardTO.setMinString("0s");
				}
				if (obj[3] != null) {
					leaderboardTO.setMax(Math.round(Double.parseDouble(obj[3]
							.toString())));
					leaderboardTO.setMaxString(TimeUtils
							.dateTimeFormatterForQsense(leaderboardTO.getMax(),
									2));
				} else {
					leaderboardTO.setMax(0L);
					leaderboardTO.setMaxString("0s");
				}
				if (obj[4] != null) {
					leaderboardTO.setAverage(Math.round(Double
							.parseDouble(obj[4].toString())));
					leaderboardTO.setAverageString(TimeUtils
							.dateTimeFormatterForQsense(
									leaderboardTO.getAverage(), 2));
				} else {
					leaderboardTO.setAverage(0L);
					leaderboardTO.setAverageString("0s");
				}
				if (obj[5] != null) {
					leaderboardTO.setDistinctUsers(Math.round(Double
							.parseDouble(obj[5].toString())));
				} else {
					leaderboardTO.setDistinctUsers(0L);
				}
				subCategoryNameToLeaderBoardTOMap.put(Long.parseLong(obj[0].toString()),
						leaderboardTO);
			}

			// Adding user's duration in map
			for (Object observationduration : observationdurations) {

				Object[] obj = (Object[]) observationduration;

				if (!(subCategoryNameToLeaderBoardTOMap.containsKey(Long.parseLong(obj[0]
						.toString())))) {
					continue;
				}
				if (Double.parseDouble(obj[1].toString()) != 1.0) {
					continue;
				}

				leaderboardTO = subCategoryNameToLeaderBoardTOMap.get(Long.parseLong(obj[0]
						.toString()));
				long userDuration = Math.round(Double.parseDouble(obj[3]
						.toString()));
				Long distinctUsersOfGroup = leaderboardTO.getDistinctUsers();

				if (distinctUsersOfGroup > 0) {

					if (userDuration < leaderboardTO.getMin()) {
						leaderboardTO.setMin(userDuration);
						leaderboardTO.setMinString(TimeUtils
								.dateTimeFormatterForQsense(userDuration, 2));
					}

					if (userDuration > leaderboardTO.getMax()) {
						leaderboardTO.setMax(userDuration);
						leaderboardTO.setMaxString(TimeUtils
								.dateTimeFormatterForQsense(userDuration, 2));
					}

					leaderboardTO.setUserDuration(userDuration);
					leaderboardTO.setUserDurationString(TimeUtils
							.dateTimeFormatterForQsense(userDuration, 2));

					// Computing the average of the group

					Long averageDurationOfGroup = leaderboardTO.getAverage();

					Long averageDuration = (((averageDurationOfGroup * distinctUsersOfGroup) + (userDuration)) / (distinctUsersOfGroup + 1));

					leaderboardTO.setAverage(averageDuration);
					leaderboardTO.setAverageString(TimeUtils
							.dateTimeFormatterForQsense(averageDuration, 2));

					Double userDurationPercent = ((double) leaderboardTO
							.getUserDuration() / averageDuration) * 100;
					leaderboardTO.setUserDurationPercentage(Math
							.round(userDurationPercent));
				} else {
					leaderboardTO.setMin(userDuration);
					leaderboardTO.setMax(userDuration);
					leaderboardTO.setAverage(userDuration);
					leaderboardTO.setUserDuration(userDuration);
					leaderboardTO.setUserDurationString(TimeUtils
							.dateTimeFormatterForQsense(userDuration, 2));
					leaderboardTO.setMinString(TimeUtils
							.dateTimeFormatterForQsense(userDuration, 2));
					leaderboardTO.setMaxString(TimeUtils
							.dateTimeFormatterForQsense(userDuration, 2));
					leaderboardTO.setAverageString(TimeUtils
							.dateTimeFormatterForQsense(userDuration, 2));
					leaderboardTO.setUserDurationPercentage(100L);

				}

				subCategoryNameToLeaderBoardTOMap.put(Long.parseLong(obj[0].toString()),
						leaderboardTO);
			}

			// for stepcount
			if (stepCountEntity.isLeaderboardVisible() == true) {

				Object stepCountObservation = userObservationDAO
						.getStepCountForLeaderboard(groupId, fromDateInGMT,
								toDateInGMT, stepCountEntity.getId());

				if (stepCountObservation != null) {
					Object[] obj = (Object[]) stepCountObservation;
					leaderboardTO = new LeaderboardTO();
					leaderboardTO.setSubCategoryName(stepCountEntity.getName());
					leaderboardTO.setSubCategoryDisplayName(stepCountEntity
							.getDisplayName());
					leaderboardTO.setSortOrder(stepCountEntity.getSortOrder());
					if (obj[0] != null) {

						leaderboardTO.setMin(Math.round(Double
								.parseDouble(obj[0].toString())));
						leaderboardTO.setMinString(leaderboardTO.getMin()
								.toString());

					} else {
						leaderboardTO.setMin(0L);
						leaderboardTO.setMinString("0");
					}
					if (obj[1] != null) {
						leaderboardTO.setMax(Math.round(Double
								.parseDouble(obj[1].toString())));
						leaderboardTO.setMaxString(leaderboardTO.getMax()
								.toString());
					} else {
						leaderboardTO.setMax(0L);
						leaderboardTO.setMaxString("0");
					}
					if (obj[2] != null) {
						leaderboardTO.setAverage(Math.round(Double
								.parseDouble(obj[2].toString())));
						leaderboardTO.setAverageString(leaderboardTO
								.getAverage().toString());
					} else {
						leaderboardTO.setAverage(0L);
						leaderboardTO.setAverageString("0");
					}

					Date dateInGMT = TimeUtils.parseFormattedDate(
							fromDateInGMT, Constants.TIMESTAMP_FORMAT);
					Date nextDateInGMT = TimeUtils.parseFormattedDate(
							toDateInGMT, Constants.TIMESTAMP_FORMAT);
					// User step count observtion
					Double userStepCountObservation = userObservationDAO
							.getStepCountForDashboardStatus(userEntity,
									dateInGMT, nextDateInGMT, stepCountEntity);

					if (userStepCountObservation != null) {
						leaderboardTO.setUserDuration(Math
								.round(userStepCountObservation));
						leaderboardTO.setUserDurationString(leaderboardTO
								.getUserDuration().toString());
						Double userDurationPercent = ((double) leaderboardTO
								.getUserDuration() / leaderboardTO.getAverage()) * 100;
						leaderboardTO.setUserDurationPercentage(Math
								.round(userDurationPercent));
					} else {
						leaderboardTO.setUserDuration(0L);
						leaderboardTO.setUserDurationString("0");
						leaderboardTO.setUserDurationPercentage(0L);
					}

					subCategoryNameToLeaderBoardTOMap.put(
							stepCountEntity.getId(), leaderboardTO);

				} else {
					leaderboardTO = new LeaderboardTO();
					leaderboardTO.setSubCategoryName(stepCountEntity.getName());
					leaderboardTO.setSubCategoryDisplayName(stepCountEntity
							.getDisplayName());
					leaderboardTO.setMin(0L);
					leaderboardTO.setMinString("0");
					leaderboardTO.setMax(0L);
					leaderboardTO.setMaxString("0");
					leaderboardTO.setAverage(0L);
					leaderboardTO.setAverageString("0");
					leaderboardTO.setUserDuration(0L);
					leaderboardTO.setUserDurationString("0");
					leaderboardTO.setUserDurationPercentage(0L);
					subCategoryNameToLeaderBoardTOMap.put(
							stepCountEntity.getId(), leaderboardTO);
				}
			}

			Collection<LeaderboardTO> leaderboardTOs = subCategoryNameToLeaderBoardTOMap
					.values();
			// Parse map values into a list
			for (LeaderboardTO leaderboardTO2 : leaderboardTOs) {
				returnLeaderboardTOs.add(leaderboardTO2);
			}
			// Sort leaderboardTos on the basis of sort order defined in
			// database
			Collections.sort(returnLeaderboardTOs,
					new LeaderboardTOComparator());

			return returnLeaderboardTOs;

		} catch (QSenseDAOException e) {
			logger.error("Error while fetching records for leaderboard",
					e.getMessage());
			throw new QSenseServiceException(
					"Error while fetching records for leaderboard", e);
		}
	}

	// Method to get total active users within a period of both groups trial and
	// control
	@Override
	public List<ActiveUsersTO> getActiveUsers(Long startDate, Long endDate)
			throws ParseException {

		Date startDateInIncomingTimezone = new Date(startDate);
		Date endDateInIncomingTimezone = new Date(endDate);

		Date toDate = TimeUtils.getNextDate(startDateInIncomingTimezone);
		Date fromDate = TimeUtils.subtractDate(toDate, 1);
		List<ActiveUsersTO> activeUsersByDay = new ArrayList<ActiveUsersTO>();
		List<AppGroupEntity> appGroupEntities=appGroupDAO.getAll();
		Long controlGroupId=0L;
		Long trialGroupId=0L;
		for (AppGroupEntity appGroupEntity : appGroupEntities) {
			if(appGroupEntity.getName().equals("control")){
				controlGroupId = appGroupEntity.getId();
			}
			else if(appGroupEntity.getName().equals("trial")){
				trialGroupId = appGroupEntity.getId();
			}
		}
		
		ActiveUsersTO activeUsersTO;
		while (fromDate
				.before(TimeUtils.getNextDate(endDateInIncomingTimezone))) {

			activeUsersTO = new ActiveUsersTO();
			
			String fromDateInGMT = Utils.convertDate(fromDate,
					Constants.TIMESTAMP_FORMAT, "GMT");
			String toDateInGMT = Utils.convertDate(toDate,
					Constants.TIMESTAMP_FORMAT, "GMT");
			
			Long controlGroupActiveUsers = userObservationDAO.getActiveUsers(
					fromDateInGMT, toDateInGMT,controlGroupId);
			activeUsersTO.setControlGroupActiveUsers(controlGroupActiveUsers);
			
			Long trialGroupActiveUsers = userObservationDAO.getActiveUsers(
					fromDateInGMT, toDateInGMT,trialGroupId);
			activeUsersTO.setTrialGroupActiveUsers(trialGroupActiveUsers);
			
			activeUsersTO.setDate(fromDate);
			activeUsersByDay.add(activeUsersTO);

			toDate = TimeUtils.getNextDate(toDate);
			fromDate = TimeUtils.getNextDate(fromDate);
		}
		return activeUsersByDay;
	}

	// Method to get leaderboard observations for web-ui for a particular
	// activity for both control and trial groups
	@Override
	public List<LeaderboardForWebTO> getLeaderboardObservationByActivityForWeb(
			Long startDate, Long endDate, Long subCategoryId)
			throws ParseException {

		Date startDateInIncomingTimezone = new Date(startDate);
		Date endDateInIncomingTimezone = new Date(endDate);

		Date toDate = TimeUtils.getNextDate(startDateInIncomingTimezone);
		Date fromDate = TimeUtils.subtractDate(toDate, 1);

		SubCategoryEntity subCategoryEntity = subCategoryDAO
				.getById(subCategoryId);

		List<LeaderboardForWebTO> leaderboardObservationListByDay = new ArrayList<LeaderboardForWebTO>();

		while (fromDate
				.before(TimeUtils.getNextDate(endDateInIncomingTimezone))) {

			String fromDateInGMT = Utils.convertDate(fromDate,
					Constants.TIMESTAMP_FORMAT, "GMT");
			String toDateInGMT = Utils.convertDate(toDate,
					Constants.TIMESTAMP_FORMAT, "GMT");
			LeaderboardForWebTO leaderboardForWebTO = new LeaderboardForWebTO(
					subCategoryEntity);

			if (!subCategoryEntity.getName().equals("STEP_COUNT")) {
				List observationdurations = userObservationDAO
						.getLeaderboardObservationByActivity(subCategoryId,
								fromDateInGMT, toDateInGMT);

				for (Object observationduration : observationdurations) {

					Object[] obj = (Object[]) observationduration;

					if (obj[0].toString().equals("control")) {
						if (obj[1] != null) {
							leaderboardForWebTO
									.setMinForControl(Utils.round(
											(Double.parseDouble(obj[1]
													.toString())) / 60, 2));
							// leaderboardForWebTO.setMinStringForControl(TimeUtils.dateTimeFormatterForQsense(leaderboardForWebTO.getMinForControl(),
							// 2));
						} else {
							leaderboardForWebTO.setMinForControl(0D);
							leaderboardForWebTO.setMinStringForControl("0s");
						}
						if (obj[2] != null) {
							leaderboardForWebTO
									.setMaxForControl(Utils.round(
											(Double.parseDouble(obj[2]
													.toString())) / 60, 2));
							// leaderboardForWebTO.setMaxStringForControl(TimeUtils.dateTimeFormatterForQsense(leaderboardForWebTO.getMaxForControl(),
							// 2));
						} else {
							leaderboardForWebTO.setMaxForControl(0D);
							leaderboardForWebTO.setMaxStringForControl("0s");
						}
						if (obj[3] != null) {
							leaderboardForWebTO.setAverageForControl(Utils
									.round((Double.parseDouble(obj[3]
											.toString())) / 60, 2));
							// leaderboardForWebTO.setAverageStringForControl(TimeUtils.dateTimeFormatterForQsense(leaderboardForWebTO.getAverageForControl(),
							// 2));
						} else {
							leaderboardForWebTO.setAverageForControl(0D);
							leaderboardForWebTO
									.setAverageStringForControl("0s");
						}
					} else if (obj[0].toString().equals("trial")) {
						if (obj[1] != null) {
							leaderboardForWebTO
									.setMinForTrial(Utils.round(
											(Double.parseDouble(obj[1]
													.toString())) / 60, 2));
							// leaderboardForWebTO.setMinStringForTrial(TimeUtils.dateTimeFormatterForQsense(leaderboardForWebTO.getMinForTrial(),
							// 2));
						} else {
							leaderboardForWebTO.setMinForTrial(0D);
							leaderboardForWebTO.setMinStringForTrial("0s");
						}
						if (obj[2] != null) {
							leaderboardForWebTO
									.setMaxForTrial(Utils.round(
											(Double.parseDouble(obj[2]
													.toString())) / 60, 2));
							// leaderboardForWebTO.setMaxStringForTrial(TimeUtils.dateTimeFormatterForQsense(leaderboardForWebTO.getMaxForTrial(),
							// 2));
						} else {
							leaderboardForWebTO.setMaxForTrial(0D);
							leaderboardForWebTO.setMaxStringForTrial("0s");
						}
						if (obj[3] != null) {
							leaderboardForWebTO
									.setAverageForTrial(Utils.round(
											(Double.parseDouble(obj[3]
													.toString())) / 60, 2));
							// leaderboardForWebTO.setAverageStringForTrial(TimeUtils.dateTimeFormatterForQsense(leaderboardForWebTO.getAverageForTrial(),
							// 2));
						} else {
							leaderboardForWebTO.setAverageForTrial(0D);
							leaderboardForWebTO.setAverageStringForTrial("0s");
						}
					}

				}
			}
			// If subcategory is step_count
			else {

				List stepCountObservations = userObservationDAO
						.getStepCountForLeaderboardForEveryGroup(fromDateInGMT,
								toDateInGMT, subCategoryId);
				leaderboardForWebTO.setMinForControl(0D);
				leaderboardForWebTO.setMinStringForControl("0");
				leaderboardForWebTO.setMaxForControl(0D);
				leaderboardForWebTO.setMaxStringForControl("0");
				leaderboardForWebTO.setAverageForControl(0D);
				leaderboardForWebTO.setAverageStringForControl("0");
				leaderboardForWebTO.setMinForTrial(0D);
				leaderboardForWebTO.setMinStringForTrial("0");
				leaderboardForWebTO.setMaxForTrial(0D);
				leaderboardForWebTO.setMaxStringForTrial("0");
				leaderboardForWebTO.setAverageForTrial(0D);
				leaderboardForWebTO.setAverageStringForTrial("0");
				for (Object stepCountObservation : stepCountObservations) {

					Object[] obj = (Object[]) stepCountObservation;

					if (obj[0].toString().equals("control")) {
						if (obj[1] != null) {

							leaderboardForWebTO
									.setMinForControl((double) Math
											.round(Double.parseDouble(obj[1]
													.toString())));
							leaderboardForWebTO
									.setMinStringForControl(leaderboardForWebTO
											.getMinForControl().toString());

						} else {
							leaderboardForWebTO.setMinForControl(0D);
							leaderboardForWebTO.setMinStringForControl("0");
						}
						if (obj[2] != null) {
							leaderboardForWebTO
									.setMaxForControl((double) Math
											.round(Double.parseDouble(obj[2]
													.toString())));
							leaderboardForWebTO
									.setMaxStringForControl(leaderboardForWebTO
											.getMaxForControl().toString());
						} else {
							leaderboardForWebTO.setMaxForControl(0D);
							leaderboardForWebTO.setMaxStringForControl("0");
						}
						if (obj[3] != null) {
							leaderboardForWebTO
									.setAverageForControl((double) Math
											.round(Double.parseDouble(obj[3]
													.toString())));
							leaderboardForWebTO
									.setAverageStringForControl(leaderboardForWebTO
											.getAverageForControl().toString());
						} else {
							leaderboardForWebTO.setAverageForControl(0D);
							leaderboardForWebTO.setAverageStringForControl("0");
						}
					} else if (obj[0].toString().equals("trial")) {
						if (obj[1] != null) {
							leaderboardForWebTO
									.setMinForTrial((double) Math.round(Double
											.parseDouble(obj[1].toString())));
							leaderboardForWebTO
									.setMinStringForTrial(leaderboardForWebTO
											.getMinForTrial().toString());
						} else {
							leaderboardForWebTO.setMinForTrial(0D);
							leaderboardForWebTO.setMinStringForTrial("0");
						}
						if (obj[2] != null) {
							leaderboardForWebTO
									.setMaxForTrial((double) Math.round(Double
											.parseDouble(obj[2].toString())));
							leaderboardForWebTO
									.setMaxStringForTrial(leaderboardForWebTO
											.getMaxForTrial().toString());
						} else {
							leaderboardForWebTO.setMaxForTrial(0D);
							leaderboardForWebTO.setMaxStringForTrial("0");
						}
						if (obj[3] != null) {
							leaderboardForWebTO
									.setAverageForTrial((double) Math
											.round(Double.parseDouble(obj[3]
													.toString())));
							leaderboardForWebTO
									.setAverageStringForTrial(leaderboardForWebTO
											.getAverageForTrial().toString());
						} else {
							leaderboardForWebTO.setAverageForTrial(0D);
							leaderboardForWebTO.setAverageStringForTrial("0");
						}
					}
				}
			}
			leaderboardForWebTO.setDate(fromDate);
			leaderboardObservationListByDay.add(leaderboardForWebTO);
			toDate = TimeUtils.getNextDate(toDate);
			fromDate = TimeUtils.getNextDate(fromDate);
		}
		return leaderboardObservationListByDay;
	}

	// Method to compute step counts for devices which are incompatible to sense
	// step counts through activities RUNNING & WALKING
	private List<UserObservationEntity> getStepCountEntries(
			List<UserObservationEntity> userObservationEntities,
			HashMap<String, SubCategoryEntity> subCategoryNameMapWithSubCategoryEntity,
			Boolean observationsExistPostExclusiveSession) {

		UserObservationEntity lastActivity = userObservationEntities.get(0);
		UserEntity userEntity = lastActivity.getUser();
		UserSessionEntity userSessionEntity = lastActivity.getUserSession();
		List<UserObservationEntity> stepCountEntries = new ArrayList<UserObservationEntity>();
		String lastActivityName = lastActivity.getSubCategory().getName();
		Date lastActivityTimestamp = lastActivity.getStartTimestamp();
		Double duration = 0.0d;
		UserObservationEntity currentActivity;
		for (int i = 1; i < userObservationEntities.size(); i++) {
			currentActivity = userObservationEntities.get(i);
			String currentActivityName = currentActivity.getSubCategory()
					.getName();
			Date currentActivityTimestamp = currentActivity.getStartTimestamp();

			if (currentActivityName.equals(lastActivityName)
					&& (currentActivityName.equals(SubCategoryEnum.WALK
							.toString()) || currentActivityName
							.equals(SubCategoryEnum.RUN.toString()))) {
				duration += (currentActivityTimestamp.getTime() / Constants.MILLISECONDS_IN_MINUTE - lastActivityTimestamp
						.getTime() / Constants.MILLISECONDS_IN_MINUTE);
				if (i == userObservationEntities.size() - 1) {
					if (lastActivityName
							.equals(SubCategoryEnum.WALK.toString())) {
						UserObservationEntity stepCountEntry = new UserObservationEntity();
						stepCountEntry
								.setSubCategory(subCategoryNameMapWithSubCategoryEntity
										.get(SubCategoryEnum.STEP_COUNT
												.toString()));
						stepCountEntry
								.setStartTimestamp(currentActivityTimestamp);
						stepCountEntry.setUser(userEntity);
						stepCountEntry.setUserSession(userSessionEntity);
						stepCountEntry
								.setField1(Utils.round(Constants.AVERAGE_STEPS_IN_WALKING_PER_MINUTE
										* duration, 4));
						stepCountEntries.add(stepCountEntry);
					} else if (lastActivityName.equals(SubCategoryEnum.RUN
							.toString())) {
						UserObservationEntity stepCountEntry = new UserObservationEntity();
						stepCountEntry
								.setSubCategory(subCategoryNameMapWithSubCategoryEntity
										.get(SubCategoryEnum.STEP_COUNT
												.toString()));
						stepCountEntry
								.setStartTimestamp(currentActivityTimestamp);
						stepCountEntry.setUser(userEntity);
						stepCountEntry.setUserSession(userSessionEntity);
						stepCountEntry
								.setField1(Utils.round(Constants.AVERAGE_STEPS_IN_RUNNING_PER_MINUTE
										* duration, 4));
						stepCountEntries.add(stepCountEntry);
					}
					duration = 0.0d;
				}
			} else if (!currentActivityName.equals(lastActivityName)) {
				if (lastActivityName.equals(SubCategoryEnum.WALK.toString())) {
					UserObservationEntity stepCountEntry = new UserObservationEntity();
					stepCountEntry
							.setSubCategory(subCategoryNameMapWithSubCategoryEntity
									.get(SubCategoryEnum.STEP_COUNT.toString()));
					stepCountEntry.setStartTimestamp(currentActivityTimestamp);
					stepCountEntry.setUser(userEntity);
					stepCountEntry.setUserSession(userSessionEntity);
					duration += (currentActivityTimestamp.getTime() / Constants.MILLISECONDS_IN_MINUTE - lastActivityTimestamp
							.getTime() / Constants.MILLISECONDS_IN_MINUTE);
					stepCountEntry
							.setField1(Utils.round(Constants.AVERAGE_STEPS_IN_WALKING_PER_MINUTE
									* duration,4));
					stepCountEntries.add(stepCountEntry);
				} else if (lastActivityName.equals(SubCategoryEnum.RUN
						.toString())) {
					UserObservationEntity stepCountEntry = new UserObservationEntity();
					stepCountEntry
							.setSubCategory(subCategoryNameMapWithSubCategoryEntity
									.get(SubCategoryEnum.STEP_COUNT.toString()));
					stepCountEntry.setStartTimestamp(currentActivityTimestamp);
					stepCountEntry.setUser(userEntity);
					stepCountEntry.setUserSession(userSessionEntity);
					duration += (currentActivityTimestamp.getTime() / Constants.MILLISECONDS_IN_MINUTE - lastActivityTimestamp
							.getTime() / Constants.MILLISECONDS_IN_MINUTE);
					stepCountEntry
							.setField1(Utils.round(Constants.AVERAGE_STEPS_IN_RUNNING_PER_MINUTE
									* duration,2));
					stepCountEntries.add(stepCountEntry);
				}
				duration = 0.0d;
			}
			if ((i == userObservationEntities.size() - 1)
					&& observationsExistPostExclusiveSession) {
				if (currentActivityName.equals(SubCategoryEnum.WALK.toString())) {
					UserObservationEntity stepCountEntry = new UserObservationEntity();
					stepCountEntry
							.setSubCategory(subCategoryNameMapWithSubCategoryEntity
									.get(SubCategoryEnum.STEP_COUNT.toString()));
					stepCountEntry.setStartTimestamp(currentActivityTimestamp);
					stepCountEntry.setUser(userEntity);
					stepCountEntry.setUserSession(userSessionEntity);
					duration += (currentActivity.getEndTimestamp().getTime() / Constants.MILLISECONDS_IN_MINUTE - currentActivity
							.getStartTimestamp().getTime() / Constants.MILLISECONDS_IN_MINUTE);
					stepCountEntry
							.setField1(Utils.round(Constants.AVERAGE_STEPS_IN_WALKING_PER_MINUTE
									* duration,4));
					stepCountEntries.add(stepCountEntry);
				} else if (currentActivityName.equals(SubCategoryEnum.RUN
						.toString())) {
					UserObservationEntity stepCountEntry = new UserObservationEntity();
					stepCountEntry
							.setSubCategory(subCategoryNameMapWithSubCategoryEntity
									.get(SubCategoryEnum.STEP_COUNT.toString()));
					stepCountEntry.setStartTimestamp(currentActivityTimestamp);
					stepCountEntry.setUser(userEntity);
					stepCountEntry.setUserSession(userSessionEntity);
					duration += (currentActivity.getEndTimestamp().getTime() / Constants.MILLISECONDS_IN_MINUTE - currentActivity
							.getStartTimestamp().getTime() / Constants.MILLISECONDS_IN_MINUTE);
					stepCountEntry
							.setField1(Utils.round(Constants.AVERAGE_STEPS_IN_RUNNING_PER_MINUTE
									* duration,4));
					stepCountEntries.add(stepCountEntry);
				}
			}

			lastActivityTimestamp = currentActivityTimestamp;
			lastActivityName = currentActivityName;
		}

		return stepCountEntries;
	}

	@Override
	public List<UserObservationTO> getObservationsByUserId(Long userId, String timezoneId, Integer numberOfDays)
			throws QSenseServiceException, ParseException {
		List<UserObservationEntity> userObservationsList = new ArrayList<UserObservationEntity>();
		List<UserObservationTO> userObservationTOs = new ArrayList<UserObservationTO>();
		Date currentDate = new Date();
		Date fromDate = TimeUtils.subtractDate(currentDate, numberOfDays);
		fromDate = Utils.convertDateToGMTDate(fromDate);
		userObservationsList = userObservationDAO.getAllByUserId(userId,fromDate);
		for (UserObservationEntity userObservationEntity : userObservationsList) {
			UserObservationTO userObservationTO = new UserObservationTO();
			userObservationTO.setSubCategoryName(userObservationEntity.getSubCategory().getName());
			
			String startTimeInUserTimeZone = Utils.convertDate(userObservationEntity.getStartTimestamp(), Constants.TIMESTAMP_FORMAT, timezoneId);
			userObservationTO.setStartTimestamp(startTimeInUserTimeZone);
			if(userObservationEntity.getEndTimestamp()!=null){
				String endTimeInUserTimeZone = Utils.convertDate(userObservationEntity.getEndTimestamp(), Constants.TIMESTAMP_FORMAT, timezoneId);
				userObservationTO.setEndTimestamp(endTimeInUserTimeZone);
			}
			
			userObservationTO.setField1(userObservationEntity.getField1());
			userObservationTO.setField2(userObservationEntity.getField2());
			userObservationTO.setField3(userObservationEntity.getField3());
			userObservationTOs.add(userObservationTO);			
		}
		return userObservationTOs;
	}

	@Override
	public MainMenuTO getObservationForMainMenu(Long userId, String timezoneId,
			String fromDate) throws ParseException {

		Date fromDateByUser;
		if(StringUtils.isNotEmpty(fromDate)){
			fromDateByUser = TimeUtils.parseFormattedDate(fromDate, Constants.DATE_FORMAT, timezoneId);
		}
		else{
			fromDateByUser = TimeUtils.dateStartTime(new Date());
		}

		Date toDateByUser = TimeUtils.getNextDate(fromDateByUser);

		String toDateAsStringInGMT = Utils.convertDate(toDateByUser, Constants.TIMESTAMP_FORMAT, "GMT");
		Date toDateInGMT = Utils.convertDateToGMTDate(toDateByUser);

		String fromDateAsStringInGMT = Utils.convertDate(fromDateByUser, Constants.TIMESTAMP_FORMAT, "GMT");
		Date fromDateInGMT = Utils.convertDateToGMTDate(fromDateByUser);

		try{
			UserEntity currentUser = userDAO.getById(userId);

			if(currentUser.getRole().getName()==RoleEnum.PARENT){
				currentUser = currentUser.getAssociatedParticipant();
			}

			MainMenuTO mainMenuTO = new MainMenuTO();
			//Step count objective
			UserGoalEntity userGoalEntity = userGoalDAO.getLatestGroupGoalByUserId(currentUser, toDateInGMT);
			if(userGoalEntity != null){
				List<GoalAttributesEntity> goalAttributes = goalAttributesDAO.getByGoalId(userGoalEntity.getGoal().getId());
				for (GoalAttributesEntity goalAttributesEntity : goalAttributes) {
					if(goalAttributesEntity.getSubCategory() != null && goalAttributesEntity.getSubCategory().getName().equals(SubCategoryEnum.STEP_COUNT.toString())){
						mainMenuTO.setActivityObjective(goalAttributesEntity.getValue() == null ? 0 : goalAttributesEntity.getValue());
					}
				}
			}
			//Step count Contribution
			SubCategoryEntity stepCountSubCategory = subCategoryDAO.getByName(SubCategoryEnum.STEP_COUNT.toString());
			Double stepsContribution = userObservationDAO.getStepCountForDashboardStatus(currentUser, fromDateInGMT, toDateInGMT, stepCountSubCategory);
			mainMenuTO.setStepsContribution(stepsContribution == null ? 0 : Math.round(stepsContribution));

			List foodCountObservations = userFoodObservationDAO.getFoodCountByUser(currentUser, fromDateAsStringInGMT, toDateAsStringInGMT);

			for (Object observation : foodCountObservations) {
				Object[] obj = (Object[]) observation;
				if(obj[0].toString().equals(FoodTypeEnum.GREEN.toString())){
					mainMenuTO.setGreenFoodContribution(Math.round(Double.parseDouble(obj[1].toString())));
				}else if(obj[0].toString().equals(FoodTypeEnum.YELLOW.toString())){
					mainMenuTO.setYellowFoodContribution(Math.round(Double.parseDouble(obj[1].toString())));
				}else if(obj[0].toString().equals(FoodTypeEnum.RED.toString())){
					mainMenuTO.setRedFoodContribution(Math.round(Double.parseDouble(obj[1].toString())));
				}
			}
			return mainMenuTO;
		}catch(QSenseDAOException e) {
			logger.error("Error while fetching records from DB", e.getMessage());
			throw new QSenseServiceException(
					"Error while fetching records from DB", e);
		}
	}
	
}
