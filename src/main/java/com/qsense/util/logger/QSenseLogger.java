package com.qsense.util.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class QSenseLogger {
	private Logger logger;
	
	private QSenseLogger(Class clazz) {
		logger = LogManager.getLogger(clazz);
	}
	public static QSenseLogger getLogger(Class clazz) {
		return new QSenseLogger(clazz);
	}
	public void info(String message, Object ... args) {
		logger.info(String.format(message, args));
	}
	public void info(String message) {
		logger.info(message);
	}
	public void error(Throwable e, String message, Object ... args) {
		logger.error(String.format(message, args), e);
	}
	public void error(String message, Object ... args) {
		logger.error(String.format(message, args));
	}
	public void debug(String message, Object ... args) {
		logger.debug(String.format(message, args));
	}
	public void debug(Throwable e, String message, Object ... args) {
		logger.debug(String.format(message, args), e);
	}
	
	public void warn(String message, Object ... args) {
		logger.warn(String.format(message, args));
	}
	public void warn(Throwable e, String message, Object ... args) {
		logger.warn(String.format(message, args), e);
	}
}
