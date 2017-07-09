package com.qsense.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.exception.QSenseServiceException;
import com.qsense.exception.QSenseWebException;
import com.qsense.service.TrainingPitService;
import com.qsense.transfer.ResponseTO;
import com.qsense.util.logger.QSenseLogger;


/**
 * Training Pit Controller
 * 
 * @author Sanjay
 * 
 */

@RestController
@RequestMapping(value = "/trainingPit")

public class TrainingPitController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired
	TrainingPitService trainingPitService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object getObservationsByUserId(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ResponseTO responseTO = new ResponseTO();
		logger.info("Begin : training pit controller");
		responseTO.setSuccess(false);
		try{
			Long userId = Long.parseLong(request.getHeader("userId"));
			String timezoneId = request.getHeader("timezoneId");
			String toDate = request.getHeader("toDate");
			return trainingPitService.getTrainingPitObservation(userId, timezoneId, toDate);
		}
		catch(QSenseServiceException e)
		{
			logger.error("Error occured while fetching training pit");
			throw new QSenseWebException("Error occured while fetching training pit", e);
		}
	}
	
}
