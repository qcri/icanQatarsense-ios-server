package com.qsense.exception;

/**
 * 
 * @author Neeraj
 * 
 */

public class QSenseServiceException extends QSenseException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3154506436067997190L;

	/**
	 * default constructor
	 */
	public QSenseServiceException() {
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
	public QSenseServiceException(String message, Throwable cause, String errorCode, String type) {
		super(message, cause, errorCode, type);
	}

	/**
	 * constructor with argument message,errorCode and type
	 * 
	 * @param message
	 *            exception message
	 * @param errorCode
	 *            code of error
	 * @param type
	 *            error type
	 */
	public QSenseServiceException(String message, String errorCode,	String type) {
		super(message, errorCode, type);
	}

	/**
	 * constructor with one argument
	 * 
	 * @param cause
	 */
	public QSenseServiceException(Throwable cause) {
		super(cause);
	}
	
	public QSenseServiceException(String message,Throwable cause) {
		super(message,cause);
	}
}
