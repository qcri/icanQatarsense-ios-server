
package com.qsense.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import com.qsense.util.logger.QSenseLogger;

public class TimeUtils {
	
	private static QSenseLogger logger = QSenseLogger.getLogger(TimeUtils.class);
	 private static final HashMap<String,String> formatMap = new HashMap<String,String>();
	    static {
	        formatMap.put("MM/DD/YY hh:mm", "MM/dd/yy HH:mm");
	        formatMap.put("MM/DD/YY", "MM/dd/yy");
	        formatMap.put("MM/DD/YYYY hh:mm", "MM/dd/yyyy HH:mm");
	        formatMap.put("MM/DD/YYYY", "MM/dd/yyyy");
	        formatMap.put("MM-DD-YY hh:mm", "MM-dd-yy HH:mm");
	        formatMap.put("MM-DD-YY", "MM-dd-yy");
	        formatMap.put("MM-DD hh:mm", "MM-dd HH:mm");
	        formatMap.put("MM-DD-YYYY hh:mm", "MM-dd-yyyy HH:mm");
	        formatMap.put("MM-DD", "MM-dd");
	        formatMap.put("YYYY-MM-DD hh:mm", "yyyy-MM-dd HH:mm");
	        formatMap.put("YYYY-MM-DD", "yyyy-MM-dd");
	        formatMap.put("YY-MM-DD hh:mm", "yy-MM-dd HH:mm");
	        formatMap.put("YY-MM-DD", "yy-MM-dd");
	        formatMap.put("DD.MM.YYYY hh:mm", "dd.MM.yyyy HH:mm");
	        formatMap.put("DD.MM.YYYY", "dd.MM.yyyy");
	        formatMap.put("DD.MM.YY hh:mm", "dd.MM.yy HH:mm");
	        formatMap.put("DD.MM.YY", "dd.MM.yy");
	        formatMap.put("DD.MM hh:mm", "dd.MM HH.mm");
	        formatMap.put("DD.MM", "dd.MM");
	        formatMap.put("yyyy-MM-dd-HH-mm-ss-SSS","yyyy-MM-dd-HH-mm-ss-SSS");
	        formatMap.put("yyyy-MM-dd-HH-mm-ss-SSS-z","yyyy-MM-dd-HH-mm-ss-SSS-z");
	        formatMap.put("yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss");
	        formatMap.put("HH:mm", "HH:mm");
	    }
	    
	    
	    public static Date getPreviousDate(Date date){
	    	Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);			
			calendar.add(Calendar.DATE, -1);		
			return calendar.getTime();
	    }
	    
	    public static Date getNextDate(Date date){
	    	Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);			
			calendar.add(Calendar.DATE, +1);		
			return calendar.getTime();
	    }
	    
	    public static Date subtractDate(Date date, int noOfDays){
	    	Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);			
			calendar.add(Calendar.DATE, -noOfDays);		
			return calendar.getTime();
	    }
	    
	    public static Date addDays(Date date, int noOfDays){
	    	Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);			
			calendar.add(Calendar.DATE, noOfDays);		
			return calendar.getTime();
	    }
	    
	    public static Long getDifferenceInMilliseconds(Date startDate, Date endDate){
	    	
	    	long startTime=startDate.getTime();
	    	long endTime=endDate.getTime();
	    	
	    	return (endTime-startTime);
	    	
	    }
	    
	    public static Date parseFormattedDate(String dateStr, String format) throws ParseException{
	    	SimpleDateFormat dateFormat=new SimpleDateFormat(format);
	    	return dateFormat.parse(dateStr);
	    }
	    
	    /**
	     * @param dateStr
	     * @param format
	     * @param fromtimezoneId 
	     * @return
	     * @throws ParseException
	     */
	    public static Date parseFormattedDate(String dateStr, String format, String fromtimezoneId) throws ParseException{
	    	SimpleDateFormat dateFormat=new SimpleDateFormat(format);
	    	TimeZone tzone = TimeZone.getTimeZone(fromtimezoneId);
	    	dateFormat.setTimeZone(tzone);
	    	return dateFormat.parse(dateStr);
	    }
	    
	    public static String parseFormattedDate(Date date,String format){
	    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
	    	return simpleDateFormat.format(date);
	    }

	    public static Date nextDateStartTime(Date date){
	    	Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
	    }
	    
	    public static Date dateEndTime(Date date){
	    	Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return calendar.getTime();
	    }
	    
	    public static Date getLastWeekDayDateByCurrentDate(String date, String format, int lastWeekDay) throws ParseException{
	    	return getLastWeekDayDateByCurrentDate(parseFormattedDate(date, format), lastWeekDay);
	    }
	    
	    public static Date getLastWeekDayDateByCurrentDate(Date date, int lastWeekDay){
	    	Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add( Calendar.DAY_OF_WEEK, -(calendar.get(Calendar.DAY_OF_WEEK)-lastWeekDay)); 
			return calendar.getTime();
	    }
	    
	    public static Date dateStartTime(Date date){
	    	Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return calendar.getTime();
	    }
	    
	    public static String dateTimeFormatterForQsense(Long seconds, int places){
			
			int SECONDS_IN_WEEK = 604800;
			int SECONDS_IN_DAY = 86400;
			int SECONDS_IN_HOUR = 3600;
			int SECONDS_IN_MINUTE = 60;
		
			String result="";
			
			if (seconds>=SECONDS_IN_WEEK && places>0)
			{
				result = result+ (seconds/SECONDS_IN_WEEK) + "w ";
				seconds= seconds%SECONDS_IN_WEEK; 
				places--;
			}
			
			if (seconds>=SECONDS_IN_DAY && places>0)
			{
				result = result + (seconds/SECONDS_IN_DAY) + "d ";
				seconds= seconds%SECONDS_IN_DAY; 
				places--;
			}
					
			if (seconds>=SECONDS_IN_HOUR && places>0)
			{
				result = result + (seconds/SECONDS_IN_HOUR) + "h ";
				seconds= seconds%SECONDS_IN_HOUR; 
				places--;
			}
			
			if (seconds>=SECONDS_IN_MINUTE && places>0)
			{
				result = result + (seconds/SECONDS_IN_MINUTE) + "m ";
				seconds= seconds%SECONDS_IN_MINUTE; 
				places--;
			}
			
			if (seconds>=0 && places>0)
			{
				result = result + seconds + "s ";
			}
			
			return result.trim();
			
		}
}
