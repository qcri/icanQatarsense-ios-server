package com.qsense.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.exception.QSenseServiceException;
import com.qsense.exception.QSenseWebException;
import com.qsense.service.UserObservationService;
import com.qsense.transfer.ObservationBlockTO;
import com.qsense.transfer.ResponseTO;
import com.qsense.util.logger.QSenseLogger;


/**
 * User Controller
 * 
 * @author Kushal
 * 
 */

@RestController
@RequestMapping(value = "/logactivity")

public class UserObservationController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	private UserObservationService userObservationService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Object createUserObservation(HttpServletRequest request,
			HttpServletResponse response, @RequestBody ObservationBlockTO observationBlockTO) throws Exception{
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		try{
			Long userId = Long.parseLong(request.getHeader("userId"));
			String timezoneId = request.getHeader("timezoneId");			
			boolean returnResult = userObservationService.createUserObservation(userId, timezoneId, observationBlockTO);
			if(returnResult){
				responseTO.setSuccess(true);
			}
		}
		catch(Exception e)
		{
			if(e.getMessage().toLowerCase().contains("ConstraintViolationException in observationBlock Table".toLowerCase())){
				logger.info("Unable to create user Observations because this block had already been synced", e.getMessage());
				responseTO.setSuccess(true);
			}
			else{
				logger.error("Error occured while creating user observation ");			
			}
		}
		return responseTO;
	}
	
	@RequestMapping(value = "/activeUsers", method = RequestMethod.GET)
	public Object getAllActiveUsersByDay(Long startDate, Long endDate) throws Exception{
		logger.info("Begin : UserObservationController.getAllActiveUsersByDay");	
		try{
			return userObservationService.getActiveUsers(startDate, endDate);
		}
		catch(QSenseServiceException e)
		{
			logger.error("Error occured while fetching active users");
			throw new QSenseWebException("Error occured while fetching active users", e);
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object getObservationsByUserId(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		try{
			Long userId = Long.parseLong(request.getHeader("userId"));
			String timezoneId = request.getHeader("timezoneId");
			Integer numberOfDays = Integer.parseInt(request.getHeader("numberOfDays"));
			return userObservationService.getObservationsByUserId(userId, timezoneId, numberOfDays);
		}
		catch(QSenseServiceException e)
		{
			logger.error("Error occured while fetching observations");
			throw new QSenseWebException("Error occured while fetching observations", e);
		}
	}
	
}
