package com.qsense.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.service.UserFoodObservationService;
import com.qsense.transfer.ResponseTO;
import com.qsense.transfer.UserFoodObservationBlockTO;
import com.qsense.util.logger.QSenseLogger;


/**
 * User Food Observation Controller
 * 
 * @author Sanjay
 * 
 */

@RestController
@RequestMapping(value = "/userFoodObservation")

public class UserFoodObservationController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private UserFoodObservationService userFoodObservationService;
	
	@RequestMapping(value = "/logFood", method = RequestMethod.POST)
	public Object createUserFoodObservation(HttpServletRequest request,
			HttpServletResponse response, @RequestBody UserFoodObservationBlockTO foodObservationBlockTO) throws Exception{
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		try{
			Long userId = Long.parseLong(request.getHeader("userId"));
			String timezoneId = request.getHeader("timezoneId");	
			boolean returnResult = userFoodObservationService.createUserFoodObservation(userId, timezoneId, foodObservationBlockTO);
			if(returnResult){
				responseTO.setSuccess(true);
			}
		}
		catch(Exception e)
		{
			if(e.getMessage().toLowerCase().contains("ConstraintViolationException in observationBlock Table".toLowerCase())){
				logger.info("Unable to create food Observations because this block had already been synced", e.getMessage());
				responseTO.setSuccess(true);
			}
			else{
				logger.error("Error occured while creating food observation ");
			}
		}
		return responseTO;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Object fetchAllByUserId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		Long userId = Long.parseLong(request.getHeader("userId"));
		String fromDate = request.getHeader("fromDate");
		String timezoneId = request.getHeader("timezoneId");	
		return userFoodObservationService.getAllLatestByUserIdAndFromDate(userId, fromDate, timezoneId);
	}
}
