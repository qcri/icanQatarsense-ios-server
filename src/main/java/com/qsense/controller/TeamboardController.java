package com.qsense.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.exception.QSenseServiceException;
import com.qsense.exception.QSenseWebException;
import com.qsense.service.GoalService;
import com.qsense.util.logger.QSenseLogger;


/**
 * Teamboard Controller
 * 
 * @author Sanjay
 * 
 */

@RestController
@RequestMapping(value = "/teamboard")

public class TeamboardController extends CommonController{

 QSenseLogger logger = QSenseLogger.getLogger(getClass());
 
 @Autowired
 GoalService goalService;
 
 @RequestMapping(value = "teamObjective", method = RequestMethod.GET)
 public Object getTeamObjective(HttpServletRequest request,
   HttpServletResponse response) throws Exception{
  logger.info("Begin : teamObjective controller");
  try{
   Long userId = Long.parseLong(request.getHeader("userId"));
   String timezoneId = request.getHeader("timezoneId");
   String toDate = request.getHeader("toDate");
   return goalService.getTeamObjective(userId, toDate, timezoneId);
  }
  catch(QSenseServiceException e)
  {
   logger.error("Error occured while fetching teamObjective.");
   throw new QSenseWebException("Error occured while fetching teamObjective", e);
  }
 }
 
}