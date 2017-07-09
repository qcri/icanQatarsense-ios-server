package com.qsense.service;

import java.text.ParseException;
import java.util.List;

import com.qsense.exception.QSenseServiceException;
import com.qsense.transfer.UserFoodObservationBlockTO;
import com.qsense.transfer.UserFoodObservationTO;


public interface UserFoodObservationService {
	
	
	Boolean createUserFoodObservation(Long userId, String timezoneId, UserFoodObservationBlockTO userFoodObservationBlockTO) throws QSenseServiceException, ParseException;

	List<UserFoodObservationTO> getAllLatestByUserIdAndFromDate(Long userId, String fromDate, String timezoneId) throws QSenseServiceException, ParseException;;

}
