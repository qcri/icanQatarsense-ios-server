package com.qsense.service;

import java.text.ParseException;

import com.qsense.exception.QSenseServiceException;
import com.qsense.transfer.GoalAttributesTO;
import com.qsense.transfer.GoalTO;
import com.qsense.transfer.TeamboardTO;



public interface GoalService {

	boolean createGoal(GoalTO goalTO) throws QSenseServiceException, ParseException;

	boolean createGoalAttributes(GoalAttributesTO goalAttributesTO) throws QSenseServiceException, ParseException;

	TeamboardTO getTeamObjective(Long userId, String toDate, String timezoneId) throws ParseException;


}
