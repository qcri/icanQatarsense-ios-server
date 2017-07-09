package com.qsense.service;

import java.text.ParseException;

import com.qsense.exception.QSenseServiceException;
import com.qsense.transfer.UserGoalTO;



public interface AssignGoalService {

	boolean createUserGoal(UserGoalTO userGoalTO) throws QSenseServiceException, ParseException;

}
