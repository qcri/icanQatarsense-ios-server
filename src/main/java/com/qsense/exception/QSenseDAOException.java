package com.qsense.exception;

/**
 * 
 * @author Neeraj
 * 
 */
public class QSenseDAOException extends QSenseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4413021129468336522L;

	/**
	 * default constructor
	 */
	public QSenseDAOException() {
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
	public QSenseDAOException(String message, Throwable cause, String errorCode, String type) {
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
	public QSenseDAOException(String message, String errorCode, String type) {
		super(message, errorCode, type);
	}

	/**
	 * constructor with one argument
	 * 
	 * @param cause
	 */
	public QSenseDAOException(Throwable cause) {
		super(cause);
	}

}
