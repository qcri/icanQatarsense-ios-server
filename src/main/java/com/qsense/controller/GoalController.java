package com.qsense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.service.GoalService;
import com.qsense.transfer.GoalAttributesTO;
import com.qsense.transfer.GoalTO;
import com.qsense.transfer.ResponseTO;
import com.qsense.util.logger.QSenseLogger;


/**
 * Goal Controller
 * 
 * @author Sanjay
 * 
 */

@RestController
@RequestMapping(value = "/goal")

public class GoalController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private GoalService goalService;
	
	@RequestMapping(value = "/createGoal", method = RequestMethod.POST)
	public Object createUserFoodObservation(@RequestBody GoalTO goalTO) throws Exception{
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		try{
			boolean returnResult = goalService.createGoal(goalTO);
			if(returnResult){
				responseTO.setSuccess(true);
			}
		}
		catch(Exception e)
		{
			logger.error("Error occured while creating goal : GoalController", e.getMessage());
			responseTO.setSuccess(false);
		}
		return responseTO;
	}
	
	@RequestMapping(value = "/addGoalAttributes", method = RequestMethod.POST)
	public Object createGoalAttributes(@RequestBody GoalAttributesTO goalAttributesTO) throws Exception{
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		try{
			boolean returnResult = goalService.createGoalAttributes(goalAttributesTO);
			if(returnResult){
				responseTO.setSuccess(true);
			}
		}
		catch(Exception e)
		{
			logger.error("Error occured while creating goal attributes : GoalController", e.getMessage());
			responseTO.setSuccess(false);
		}
		return responseTO;
	}
}
