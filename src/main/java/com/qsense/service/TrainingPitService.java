package com.qsense.service;

import java.text.ParseException;

import com.qsense.transfer.TrainingPitTO;



public interface TrainingPitService {
	TrainingPitTO getTrainingPitObservation(Long userId, String timezoneId, String toDate) throws ParseException;

}
