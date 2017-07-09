
package com.qsense.exception;

/**
 * 
 * @author Neeraj
 *
 */
public class QSenseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8749840246686260688L;
	//	private String message = null;
	private String type = null;
	private String errorCode = null;
	
	public QSenseException() {
		super();
	}
	public QSenseException(String message, Throwable cause, String errorCode, String type) {
		super(message, cause);
		this.errorCode = errorCode;
		this.type = type;
	}
		
	public QSenseException(String message, String errorCode, String type) {
		super(message);
		this.errorCode = errorCode;
		this.type = type;
	}

	public QSenseException(Throwable cause) {
		super(cause);
	}
	
	public QSenseException(String exception) {
		super(exception);
	}
	
	public QSenseException(String exception,Throwable cause) {
		super(exception,cause);
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public String getType() {
		return type;
	}
}
