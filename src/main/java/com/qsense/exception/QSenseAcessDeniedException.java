
package com.qsense.exception;

/**
 * 
 * @author Neeraj
 * 
 */
public class QSenseAcessDeniedException extends QSenseException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8082905535752490224L;

	/**
	 * default constructor
	 */
	public QSenseAcessDeniedException() {
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
	public QSenseAcessDeniedException(String message, Throwable cause, String errorCode, String type) {
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
	public QSenseAcessDeniedException(String message, String errorCode, String type) {
		super(message, errorCode, type);
	}

	/**
	 * constructor with one argument
	 * 
	 * @param cause
	 */
	public QSenseAcessDeniedException(Throwable cause) {
		super(cause);
	}

}
