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
@RequestMapping(value = "/leaderboard")

public class LeaderboardController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	UserObservationService userObservationService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object getAllActivities(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		logger.info("Begin : LeaderboardController.activityStatisticsforLeaderBoard");	
		try{
			Long userId = Long.parseLong(request.getHeader("userId"));
			String date =request.getHeader("inputDate");
			String timezoneId = request.getHeader("timezoneId");
			int noOfDays = Integer.parseInt(request.getHeader("noOfDays"));
			return userObservationService.getObservationsforLeaderBoard(userId, date, timezoneId, noOfDays );
		}
		catch(QSenseServiceException e)
		{
			logger.error("Error occured while fetching activity duration for Leader Board");
			throw new QSenseWebException("Error occured while fetching activity duration for Leader Board", e);
		}
		
	}
	
	@RequestMapping(value = "/observationForWeb", method = RequestMethod.GET)
	public Object getAllLeaderboardObservationsForWebByDay(Long startDate, Long endDate, Long subCategoryId) throws Exception{
		logger.info("Begin : LeaderboardController.getAllLeaderboardObservationsForWebByDay");	
		try{
			return userObservationService.getLeaderboardObservationByActivityForWeb(startDate, endDate, subCategoryId);
		}
		catch(QSenseServiceException e)
		{
			logger.error("Error occured while fetching user activities observation");
			throw new QSenseWebException("Error occured while fetching user activities observation", e);
		}
	}
}
