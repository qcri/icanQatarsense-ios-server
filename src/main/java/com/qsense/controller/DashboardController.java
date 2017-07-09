package com.qsense.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.exception.QSenseServiceException;
import com.qsense.exception.QSenseWebException;
import com.qsense.service.UserObservationService;
import com.qsense.util.logger.QSenseLogger;


/**
 * User Controller
 * 
 * @author Kushal
 * 
 */

@RestController
@RequestMapping(value = "/dashboard")

public class DashboardController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	UserObservationService userObservationService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object getAllActivities(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try{
			Long userId = Long.parseLong(request.getHeader("userId"));
			String date =request.getHeader("inputDate");
			String timezoneId = request.getHeader("timezoneId");
			return userObservationService.getObservationsforDashboard(userId, date, timezoneId);
		}
		catch(QSenseServiceException e)
		{
			logger.error("Error occured while fetching observations for Dashboard ");
			throw new QSenseWebException("Error occured while fetching observations for Dashboard ", e);
		}
	}
	
	@RequestMapping(value = "/mainMenu", method = RequestMethod.GET)
	public Object getMainMenuActivities(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		try{
			Long userId = Long.parseLong(request.getHeader("userId"));
			String timezoneId = request.getHeader("timezoneId");
			String fromDate = request.getHeader("fromDate");
			return userObservationService.getObservationForMainMenu(userId, timezoneId, fromDate);
		}
		catch(QSenseServiceException e)
		{
			logger.error("Error occured while fetching observations for Dashboard ");
			throw new QSenseWebException("Error occured while fetching observations for Dashboard ", e);
		}
	}
}
