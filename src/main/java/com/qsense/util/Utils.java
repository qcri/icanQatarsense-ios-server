package com.qsense.util;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.qsense.common.CommonReturnCodes;
import com.qsense.exception.QSenseException;
import com.qsense.security.LoggedInUser;
import com.qsense.util.logger.QSenseLogger;



public class Utils {
	public static QSenseLogger logger = QSenseLogger.getLogger(Utils.class);	

	public static final Date REGISTRATION_TIME_STAMP = new Date();

	public static Object cloneBean(Object object) {
		logger.info("Begin : Utils.cloneBean");
		Object clonedObject = null;
		try {
			clonedObject = BeanUtils.cloneBean(object);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
			throw new QSenseException(e.getMessage(), e,
					CommonReturnCodes.ERROR_EXCEPTION,
					ErrorTypeEnum.TYPE_ERROR.toString());
		} catch (InstantiationException e) {
			logger.error(e.getMessage());
			throw new QSenseException(e.getMessage(), e,
					CommonReturnCodes.ERROR_EXCEPTION,
					ErrorTypeEnum.TYPE_ERROR.toString());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage());
			throw new QSenseException(e.getMessage(), e,
					CommonReturnCodes.ERROR_EXCEPTION,
					ErrorTypeEnum.TYPE_ERROR.toString());
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage());
			throw new QSenseException(e.getMessage(), e,
					CommonReturnCodes.ERROR_EXCEPTION,
					ErrorTypeEnum.TYPE_ERROR.toString());
		}
		logger.info("End : Utils.cloneBean");
		return clonedObject;
	}

	public static LoggedInUser getCurrentLoggedInUser() {
		SecurityContext context = (SecurityContext) SecurityContextHolder
				.getContext();
		if (context != null && context.getAuthentication() != null)
			return (LoggedInUser) context.getAuthentication().getPrincipal();

		return null;
	}
	

	public static Date convertDate(String expDate) {
		DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date expirationDate = null;
		try {
			expirationDate = formatter.parse(expDate);
		} catch (ParseException e) {
			throw new QSenseException(
					"QSenseException : Invalid date format.Correct Format[yyyy-MM-dd].",
					CommonReturnCodes.ERROR_INVALID_DATE_FORMAT,
					ErrorTypeEnum.TYPE_ERROR.toString());
		}
		return expirationDate;
	}

	public static Document parseXmlFile(String in) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
			
	
	public static TimeZone getDefaultTimeZone() {
		return Calendar.getInstance().getTimeZone();
	}		
	
	public static Long getCurrentTimeMiliSeconds() {
		return getCurrentDate().getTime();
	}
	
	public static Date getCurrentDate() {
		return new Date();
	}	
	
	public static boolean isValidJson(String json) {
		try {
			new org.json.JSONObject(json);
		} catch (Exception e) {
			return false;
		}
		return true;
	}	
	
	public static String convertDate(Date date, String dateFormat,String toTimezoneId)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone(toTimezoneId));
		return simpleDateFormat.format(calendar.getTime());
	}
	
	public static Date convertDateToGMTDate(Date date){
		TimeZone timeZone = TimeZone.getDefault();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, -timeZone.getOffset(Calendar.getInstance().getTimeInMillis()));
		return calendar.getTime();
	}
	
	public static Date convertGMTDateToLocalDate(Date date){
		TimeZone timeZone = TimeZone.getDefault();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, +timeZone.getOffset(Calendar.getInstance().getTimeInMillis()));
		return calendar.getTime();
	}
	
	public static double round(double value, int places) {
		StringBuilder decimalPlaces = new StringBuilder("#.");
		for (int i=0; i< places; i++) {
			decimalPlaces.append("#");
		}
		DecimalFormat decimalFormat = new DecimalFormat(decimalPlaces.toString());
		return Double.valueOf(decimalFormat.format(value));
	}
	
}