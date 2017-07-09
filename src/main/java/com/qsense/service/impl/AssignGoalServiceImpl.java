package com.qsense.service.impl;

import java.text.ParseException;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsense.dao.GoalDAO;
import com.qsense.dao.UserGoalDAO;
import com.qsense.entity.GoalEntity;
import com.qsense.entity.UserEntity;
import com.qsense.entity.UserGoalEntity;
import com.qsense.exception.QSenseDAOException;
import com.qsense.exception.QSenseServiceException;
import com.qsense.service.AssignGoalService;
import com.qsense.transfer.UserGoalTO;
import com.qsense.util.Constants;
import com.qsense.util.TimeUtils;
import com.qsense.util.Utils;
import com.qsense.util.logger.QSenseLogger;

/**
 * @author Sanjay
 * 
 */

@Service("AssignGoalService")
@Transactional
public class AssignGoalServiceImpl implements AssignGoalService  {
	
	@Autowired
	private GoalDAO goalDAO;
	
	@Autowired
	private UserGoalDAO userGoalDAO;
	
	QSenseLogger logger = QSenseLogger.getLogger(getClass());

	@Override
	public boolean createUserGoal(UserGoalTO userGoalTO) throws QSenseServiceException, ParseException {
		
		boolean result = false;
		
		UserGoalEntity userGoalEntity = new UserGoalEntity();
		
		GoalEntity goalEntity = new GoalEntity();
		goalEntity.setId(userGoalTO.getGoalId());
		
		userGoalEntity.setGoal(goalEntity);
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userGoalTO.getUserId());
		userGoalEntity.setUser(userEntity);
		
		Date startTime = TimeUtils.parseFormattedDate(userGoalTO.getStartTime(), Constants.TIMESTAMP_FORMAT);
		Date StartTimeInGMT = Utils.convertDateToGMTDate(startTime);
		userGoalEntity.setStartTime(StartTimeInGMT);
		
		try {
			userGoalDAO.create(userGoalEntity);
			result= true;
		}
		catch(QSenseDAOException e) {
			logger.error("Error while creating user goals : GoalServiceImpl", e.getMessage());
			result =  false;
			throw new QSenseServiceException(
					"Unable to create user goals", e); 
		}
		return result;
	}

	
}
