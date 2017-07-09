package com.qsense.service;

import java.text.ParseException;
import java.util.List;

import com.qsense.exception.QSenseServiceException;
import com.qsense.transfer.ActiveUsersTO;
import com.qsense.transfer.DashboardTO;
import com.qsense.transfer.LeaderboardForWebTO;
import com.qsense.transfer.LeaderboardTO;
import com.qsense.transfer.MainMenuTO;
import com.qsense.transfer.ObservationBlockTO;
import com.qsense.transfer.UserObservationTO;


public interface UserObservationService {
	
	List<DashboardTO> getObservationsforDashboard(Long userId, String date,String timezoneId) throws QSenseServiceException, ParseException;
	List<LeaderboardTO> getObservationsforLeaderBoard(Long userId, String date, String timezoneId, int noOfDays) throws QSenseServiceException, ParseException;
	boolean createUserObservation(Long userId, String timezoneId, ObservationBlockTO observationBlockTO) throws QSenseServiceException, ParseException;
//	boolean updateEndTimestampOfActivityOnLogout(Long userId, Long sessionId);
	List<ActiveUsersTO> getActiveUsers(Long startDate, Long endDate) throws ParseException;
	List<LeaderboardForWebTO> getLeaderboardObservationByActivityForWeb(Long startDate, Long endDate, Long subCategoryId) throws ParseException;
	List<UserObservationTO> getObservationsByUserId(Long userId, String timezoneId, Integer numberOfDays) throws QSenseServiceException, ParseException;
	MainMenuTO getObservationForMainMenu(Long userId, String timezoneId, String fromDate) throws ParseException;
}
