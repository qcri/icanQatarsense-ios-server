package com.qsense.service.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.adapter.SubCategoryEntityTOMapper;
import com.qsense.dao.GoalAttributesDAO;
import com.qsense.dao.GoalDAO;
import com.qsense.dao.ObservationBlockDAO;
import com.qsense.dao.SubCategoryDAO;
import com.qsense.dao.UserDAO;
import com.qsense.dao.UserGoalDAO;
import com.qsense.dao.UserObservationDAO;
import com.qsense.entity.GoalAttributesEntity;
import com.qsense.entity.SubCategoryEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserGoalEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.exception.QSenseServiceException;
import com.qsense.service.TrainingPitService;
import com.qsense.transfer.TrainingPitTO;
import com.qsense.util.Constants;
import com.qsense.util.DurationEnum;
import com.qsense.util.RoleEnum;
import com.qsense.util.SubCategoryEnum;
import com.qsense.util.TimeUtils;
import com.qsense.util.Utils;
import com.qsense.util.logger.QSenseLogger;

/**
 * @author Sanjay
 * 
 */

@Service("TrainingPitService")
@Transactional
public class TrainingPitServiceImpl implements TrainingPitService {

	@Autowired
	private UserObservationDAO userObservationDAO;

	@Autowired
	private SubCategoryDAO subCategoryDAO;

	@Autowired
	private ObservationBlockDAO observationBlockDAO;
	
	@Autowired
	private UserGoalDAO userGoalDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private GoalAttributesDAO goalAttributesDAO;
	
	@Autowired
	private SubCategoryEntityTOMapper subCategoryEntityTOMapper;
	
	@Autowired
	private GoalDAO goalDAO;

	QSenseLogger logger = QSenseLogger.getLogger(getClass());

	@Override
	public TrainingPitTO getTrainingPitObservation(Long userId, String timezoneId, String toDate) throws ParseException {

		Date fromDate = TimeUtils.getLastWeekDayDateByCurrentDate(toDate, Constants.DATE_FORMAT, Calendar.SUNDAY);
		fromDate = TimeUtils.parseFormattedDate(TimeUtils.parseFormattedDate(fromDate, Constants.TIMESTAMP_FORMAT), Constants.TIMESTAMP_FORMAT, timezoneId);
		Date fromDateInGMT = Utils.convertDateToGMTDate(fromDate);
		
		Date toDateByUser = TimeUtils.parseFormattedDate(toDate, Constants.DATE_FORMAT, timezoneId);
		toDateByUser = TimeUtils.getNextDate(toDateByUser);
		Date toDateInGMT = Utils.convertDateToGMTDate(toDateByUser);
		
		try{
			UserEntity currentUser = userDAO.getById(userId);

			if(currentUser.getRole().getName()==RoleEnum.PARENT){
				currentUser = currentUser.getAssociatedParticipant();
			}

			TrainingPitTO trainingPitTO  = new TrainingPitTO();
			UserGoalEntity userGoalEntity = userGoalDAO.getLatestGroupGoalByUserId(currentUser, toDateInGMT);
			if(userGoalEntity!=null){
				List<GoalAttributesEntity> goalAttributesEntities = goalAttributesDAO.getByGoalId(userGoalEntity.getGoal().getId());
				if(goalAttributesEntities.size()>0){
					for (GoalAttributesEntity goalAttributesEntity : goalAttributesEntities) {
						if(goalAttributesEntity.getSubCategory() != null && goalAttributesEntity.getSubCategory().getName().equals(SubCategoryEnum.STEP_COUNT.toString())){
							trainingPitTO.setGoalToBeAchieved((goalAttributesEntity.getValue() == null ? 0 : goalAttributesEntity.getValue()*DurationEnum.WEEKLY.getDays()));
						}
					}
				}
			}
			SubCategoryEntity stepCountSubCategory = subCategoryDAO.getByName(SubCategoryEnum.STEP_COUNT.toString());
			Double stepCountOfCurrentUser = userObservationDAO.getStepCountForDashboardStatus(currentUser, fromDateInGMT, toDateInGMT, stepCountSubCategory);
			trainingPitTO.setCurrentUserStepCount(stepCountOfCurrentUser == null ? 0 : Math.round(stepCountOfCurrentUser));

			return trainingPitTO;
		}catch(QSenseDAOException e) {
			logger.error("Error while fetching records from DB", e.getMessage());
			throw new QSenseServiceException(
					"Error while fetching records from DB", e);
		}
	}
}
