package com.qsense.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.dao.GoalAttributesDAO;
import com.qsense.dao.GoalDAO;
import com.qsense.dao.UserDAO;
import com.qsense.dao.UserFoodObservationDAO;
import com.qsense.dao.UserGoalDAO;
import com.qsense.dao.UserObservationDAO;
import com.qsense.entity.DurationEntity;
import com.qsense.entity.FoodSubTypeEntity;
import com.qsense.entity.GoalAttributesEntity;
import com.qsense.entity.GoalEntity;
import com.qsense.entity.SubCategoryEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserGoalEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.exception.QSenseServiceException;
import com.qsense.service.GoalService;
import com.qsense.transfer.GoalAttributesTO;
import com.qsense.transfer.GoalTO;
import com.qsense.transfer.JournalTO;
import com.qsense.transfer.TeamboardAttributesTO;
import com.qsense.transfer.TeamboardTO;
import com.qsense.util.Constants;
import com.qsense.util.DurationEnum;
import com.qsense.util.FoodTypeEnum;
import com.qsense.util.ObservationTypeEnum;
import com.qsense.util.RoleEnum;
import com.qsense.util.TimeUtils;
import com.qsense.util.Utils;
import com.qsense.util.logger.QSenseLogger;

/**
 * @author Sanjay
 * 
 */

@Service("GoalService")
@Transactional
public class GoalServiceImpl implements GoalService {
	
	@Autowired
	private GoalDAO goalDAO;
	
	@Autowired
	private UserGoalDAO userGoalDAO;
	
	@Autowired
	private UserObservationDAO userObservationDAO;
	
	@Autowired
	private UserFoodObservationDAO userFoodObservationDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private GoalAttributesDAO goalAttributesDAO;
	
	QSenseLogger logger = QSenseLogger.getLogger(getClass());

	@Override
	public boolean createGoal(GoalTO goalTO) throws QSenseServiceException, ParseException {
		
		boolean result = false;
		
		GoalEntity goalEntity = new GoalEntity();
		goalEntity.setTitle(goalTO.getTitle());
		goalEntity.setDescription(goalTO.getDescription());
		
		DurationEntity durationEntity = new DurationEntity();
		durationEntity.setId(goalTO.getDurationId());
		goalEntity.setDuration(durationEntity);
		
		try{
			goalDAO.create(goalEntity);
			result =  true;
		}
		catch(QSenseDAOException e){
			logger.error("Error while creating goals : GoalServiceImpl",e.getMessage());
			result =  false;
			throw new QSenseServiceException(
					"Unable to create goals", e);
			
		}
		return result;
	}
	
	@Override
	public boolean createGoalAttributes(GoalAttributesTO goalAttributesTO) throws QSenseServiceException, ParseException {
		
		boolean result = false;
		
		GoalAttributesEntity goalAttributesEntity = new GoalAttributesEntity();
		
		if(goalAttributesTO.getFoodSubType().getId()!=null && goalAttributesTO.getSubCategory().getId()!=null){
			return result;
		}
		
		if(goalAttributesTO.getFoodSubType().getId()==null && goalAttributesTO.getSubCategory().getId()==null){
			return result;
		}
		
		if(goalAttributesTO.getFoodSubType().getId()!=null){
			FoodSubTypeEntity foodSubTypeEntity = new FoodSubTypeEntity();
			foodSubTypeEntity.setId(goalAttributesTO.getFoodSubType().getId());
			
			goalAttributesEntity.setFoodSubType(foodSubTypeEntity);
		}
		
		if(goalAttributesTO.getSubCategory().getId()!=null){
			SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
			subCategoryEntity.setId(goalAttributesTO.getSubCategory().getId());
			
			goalAttributesEntity.setSubCategory(subCategoryEntity);
		}
		
		
		
		GoalEntity goalEntity = new GoalEntity();
		goalEntity.setId(goalAttributesTO.getGoal().getId());
		goalAttributesEntity.setGoal(goalEntity);
		goalAttributesEntity.setValue(goalAttributesTO.getValue());
		
		try{
			goalAttributesDAO.create(goalAttributesEntity);
			result =  true;
		}
		catch(QSenseDAOException e){
			logger.error("Error while creating goal Attributes : GoalServiceImpl", e.getMessage());
			result =  false;
			throw new QSenseServiceException(
					"Unable to create goal Attributes", e); 
		}
		
		return result;
	}

	@Override
	public TeamboardTO getTeamObjective(Long userId, String toDate, String timezoneId) throws ParseException {
		try{
			Date fromDate = TimeUtils.getLastWeekDayDateByCurrentDate(toDate, Constants.DATE_FORMAT, Calendar.SUNDAY);
			fromDate = TimeUtils.parseFormattedDate(TimeUtils.parseFormattedDate(fromDate, Constants.TIMESTAMP_FORMAT), Constants.TIMESTAMP_FORMAT, timezoneId);
			String fromDateAsStringInGMT = Utils.convertDate(fromDate, Constants.TIMESTAMP_FORMAT, "GMT");
			Date fromDateInGMT = Utils.convertDateToGMTDate(fromDate);
			
			Date toDateByUser = TimeUtils.parseFormattedDate(toDate, Constants.DATE_FORMAT, timezoneId);
			toDateByUser = TimeUtils.getNextDate(toDateByUser);
			String toDateAsStringInGMT = Utils.convertDate(toDateByUser, Constants.TIMESTAMP_FORMAT, "GMT");
			Date toDateInGMT = Utils.convertDateToGMTDate(toDateByUser);


			UserEntity currentUser = userDAO.getById(userId);

			if(currentUser.getRole().getName()==RoleEnum.PARENT){
				currentUser = currentUser.getAssociatedParticipant();
			}
			List<UserEntity> userEntities = userDAO.getAllUsersByGroupAndRole(currentUser.getGroup(), currentUser.getRole());

			Map<String, TeamboardAttributesTO> subcategoryToTeamBoardAttributeMap = new HashMap<String, TeamboardAttributesTO>();
			TeamboardTO teamboardTO = new TeamboardTO();
			teamboardTO.setJournal(new ArrayList<JournalTO>());
			List<TeamboardAttributesTO>	currentUserTeamboardAttributes = new ArrayList<TeamboardAttributesTO>();

			for (UserEntity userEntity : userEntities) {
				UserGoalEntity userGoalEntity = userGoalDAO.getLatestGroupGoalByUserId(userEntity, toDateInGMT);
				JournalTO journal = new JournalTO();
				journal.setUserName(userEntity.getFirstName());
				journal.setStepCount(0D);
				journal.setGreenFoodCount(0D);
				if(userGoalEntity != null){
					List<GoalAttributesEntity> goalAttributes = goalAttributesDAO.getByGoalId(userGoalEntity.getGoal().getId());

					TeamboardAttributesTO teamboardAttributesTO = null;

					for (GoalAttributesEntity goalAttributesEntity : goalAttributes) {

						if(goalAttributesEntity.getSubCategory()!=null){
							if(subcategoryToTeamBoardAttributeMap.containsKey(goalAttributesEntity.getSubCategory().getName())){
								teamboardAttributesTO = subcategoryToTeamBoardAttributeMap.get(goalAttributesEntity.getSubCategory().getName());
							}else{
								teamboardAttributesTO = new TeamboardAttributesTO();
								teamboardAttributesTO.setName(goalAttributesEntity.getSubCategory().getName());
							}

							Double stepCountContribution = userObservationDAO.getStepCountForDashboardStatus(userEntity, fromDateInGMT, toDateInGMT, goalAttributesEntity.getSubCategory());
							teamboardAttributesTO.setObjective( (teamboardAttributesTO.getObjective() == null ? 0 : teamboardAttributesTO.getObjective()) + goalAttributesEntity.getValue() * DurationEnum.WEEKLY.getDays());
							teamboardAttributesTO.setContribution( (teamboardAttributesTO.getContribution() == null ? 0 : teamboardAttributesTO.getContribution()) + (stepCountContribution == null ? 0 : Math.round(stepCountContribution)));
							subcategoryToTeamBoardAttributeMap.put(goalAttributesEntity.getSubCategory().getName(), teamboardAttributesTO);
							journal.setStepCount(stepCountContribution == null ? 0 : stepCountContribution);

							if(userEntity.getId() == currentUser.getId()){
								TeamboardAttributesTO currenUserTeamboardAttributesTO = new TeamboardAttributesTO();
								currenUserTeamboardAttributesTO.setName(goalAttributesEntity.getSubCategory().getName());
								currenUserTeamboardAttributesTO.setObjective( goalAttributesEntity.getValue() * DurationEnum.WEEKLY.getDays());
								currenUserTeamboardAttributesTO.setContribution( stepCountContribution == null ? 0 : Math.round(stepCountContribution));
								currentUserTeamboardAttributes.add(currenUserTeamboardAttributesTO);
							}
						}

						if(goalAttributesEntity.getFoodSubType()!=null){
							if(goalAttributesEntity.getFoodSubType().getFoodType().getName() == FoodTypeEnum.GREEN){
								Double foodContribution = userFoodObservationDAO.getFoodCountByUserAndFoodType(userEntity, fromDateAsStringInGMT, toDateAsStringInGMT, FoodTypeEnum.GREEN);
								journal.setGreenFoodCount(foodContribution);
							}
						}
					}
				}
				teamboardTO.getJournal().add(journal);
			}
			List<TeamboardAttributesTO> subCategoriesTeamboardAttributesList = new ArrayList<TeamboardAttributesTO>(subcategoryToTeamBoardAttributeMap.values());

			if(subCategoriesTeamboardAttributesList != null){
				teamboardTO.setGroupTeamboardAttributes(subCategoriesTeamboardAttributesList);
			}
			teamboardTO.setCurrentUserTeamboardAttributes(currentUserTeamboardAttributes);
			teamboardTO.setTopContributors(getTopContributors(teamboardTO.getJournal(), 4));
			return teamboardTO;
		}catch(QSenseDAOException e) {
			logger.error("Error while fetching records from DB", e.getMessage());
			throw new QSenseServiceException(
					"Error while fetching records from DB", e);
		}
	}
	
	private List<JournalTO> getTopContributors(List<JournalTO> journal, int noOfTopContributors){
		Collections.sort(journal, new Comparator<JournalTO>() {
            @Override
            public int compare(JournalTO e1, JournalTO e2) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
            	Double lhs = e1.getGreenFoodCount() + e1.getStepCount();
            	Double rhs = e2.getGreenFoodCount() + e2.getStepCount();
                return lhs > rhs ? -1 : (lhs < rhs ) ? 1 : 0;
            }
        });
		
		List<JournalTO> topContributorsTOs = new ArrayList<JournalTO>();
		for (JournalTO entry : journal) {
			if(noOfTopContributors-- > 0 ){
				topContributorsTOs.add(entry);
			}else{
				break;
			}
				
		}
		return topContributorsTOs;
	}
}
