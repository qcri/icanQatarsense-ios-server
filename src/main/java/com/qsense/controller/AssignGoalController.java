package com.qsense.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.service.AssignGoalService;
import com.qsense.transfer.ResponseTO;
import com.qsense.transfer.UserGoalTO;
import com.qsense.util.logger.QSenseLogger;


/**
 * User Food Observation Controller
 * 
 * @author Sanjay
 * 
 */

@RestController
@RequestMapping(value = "/assignGoal")

public class AssignGoalController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private AssignGoalService assignGoalService;
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public Object createUserGoal(HttpServletRequest request,
			HttpServletResponse response, @RequestBody UserGoalTO userGoalTO) throws Exception{
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		try{
//			 = new UserGoalTO();
//			userGoalTO.setGoalId(Long.parseLong(request.getHeader("goalId")));
//			userGoalTO.setUserId(Long.parseLong(request.getHeader("userId")));
//			userGoalTO.setStartTime(Utils.convertDate(request.getHeader("startTime")));
			boolean returnResult = assignGoalService.createUserGoal(userGoalTO);
			if(returnResult){
				responseTO.setSuccess(true);
			}
		}
		catch(Exception e)
		{
			logger.error("Error occured while creating user goal : AssignGoalController", e.getMessage());
			responseTO.setSuccess(false);
		}
		return responseTO;
	}
}
