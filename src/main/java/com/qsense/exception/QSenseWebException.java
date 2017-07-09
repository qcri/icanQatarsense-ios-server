package com.qsense.exception;

/**
 * 
 * @author Neeraj
 * 
 */
public class QSenseWebException extends QSenseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7872304769419684327L;

	/**
	 * default constructor
	 */
	public QSenseWebException() {
		super();
	}

	/**
	 * constructor with argument message,cause, errorCode and type of error
	 * 
	 * @param message
	 *            exception message
	 * @param cause
	 * @param errorCode
	 *            code of error
	 * @param type
	 *            error type
	 */
	public QSenseWebException(String message, Throwable cause, String errorCode, String type) {
		super(message, cause, errorCode, type);
	}

	/**
	 * constructor with argument message,errorCode and error type
	 * 
	 * @param message
	 *            exception message
	 * @param errorCode
	 *            code of error
	 * @param type
	 *            error type
	 */
	public QSenseWebException(String message, String errorCode, String type) {
		super(message, errorCode, type);
	}

	/**
	 * constructor with one argument
	 * 
	 * @param cause
	 */
	public QSenseWebException(Throwable cause) {
		super(cause);
	}
	
	public QSenseWebException(String message,Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * @param message
	 */
	public QSenseWebException(String message) {
		super(message);
	}

}
