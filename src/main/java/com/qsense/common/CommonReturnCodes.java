package com.qsense.common;

/**
 * Common Return Codes
 * 
 * @author Neeraj
 */
public interface CommonReturnCodes
{
    public final static String NO_ERROR = null;
    public final static String ERROR_FILE_NOT_FOUND = "error.file.not.found";
    public final static String ERROR_EXCEPTION      = "error.exception";        

    public final static String ERROR_PERSISTENCE_PROPERTIES_NOT_FOUND = "error.persistence.properties.not.found";
    public final static String ERROR_PERSISTENCE_FACTORY_NOT_FOUND = "error.persistence.factory.not.found";

    public final static String ERROR_PROPERTIES_NOT_INITIALIZED = "error.properties.not.initialized";
    public final static String ERROR_PROPERTY_NOT_FOUND = "error.property.not.found";      
    public final static String ERROR_PROPERTY_MACRO_NOT_FOUND = "error.property.macro.not.found";

    public final static String ERROR_SERVICE_MANAGER_FAILED = "error.service.manager.failed";
    public final static String ERROR_SERVICE_PROPERTY_NAME_NOT_FOUND = "error.service.property.name.not.found";
    public final static String ERROR_SERVICE_NOT_CREATED = "error.service.not.created";
    public final static String ERROR_SERVICE_DESCRIPTOR_PROPERTY_INVALID = "error.service.descriptor.property.invalid";
    public final static String ERROR_SERVICE_DESCRIPTOR_NOT_CREATED = "error.serviced.descriptor.not.created";
    public final static String ERROR_SERVICE_PROPERTIES_CONTEXT_NOT_INITIALIZED = "error.service.properties.context.not.initialized";

    public final static String ERROR_ACCOUNT_INVALID_CONTEXT = "error.account.invalid.context";
    public final static String ERROR_ACCOUNT_LOGIN_INVALID = "error.account.login.invalid";
    public final static String ERROR_ACCOUNT_CREDENTIALS_EXPIRED = "error.account.credentials.expired";
    public final static String ERROR_ACCOUNT_EXPIRED    = "error.account.expired";

    public final static String ERROR_LICENSE_EXPIRED    = "error.license.expired";
    public final static String ERROR_LICENSE_INVALID    = "error.license.invalid";
    public final static String ERROR_LICENSE_TIMEOUT    = "error.license.timeout"; 

    public final static String ERROR_DECODE_FAULT       = "error.decode.error";
    public final static String ERROR_ENCODE_FAULT       = "error.encode.fault";
    public final static String ERROR_CONNECTION_INVALID = "error.connection.invalid";   
    public final static String ERROR_CAPTCHA_VALIDATION_FAILED = "error.captcha.validation.failed";
    
    
    public final static String ERROR_INCORRECT_URI = "error.Incorrect.uri";
    public final static String ERROR_RECORD_NOT_FOUND = "error.record.not.found";
	public static final String ERROR_INVALID_DATE_FORMAT = "error.invalid.date.format";
	public static final String ERROR_UNSUBSCRIBED_USER = "error.unsubscribed.user";
	public static final String ERROR_LIMIT_EXCEEDED = "error.limit.exceeded";
	
	public static final String ERROR_ACCESS_DENIED = "error.access.denied";

	public static final String ERROR_USER_ALREADY_EXISTS = "error.user.already.exists";
	public static final String ERROR_USER_REGISTRATION_CAPTCHA_INCORRECT = "error.user.registration.captcha.incorrect";
	public static final String ERROR_INVALID_CREDENTIALS_USED_WHILE_AUTHENTICATION = "error.authentication.credentials.invalid";
	
	public static final String ERROR_NOT_AUTHORIZED = "error.not.authorized";
	
	public static final String ERROR_SERVICE_ALREADY_EXISTS = "error.service.already.exists";
	
	public static final String INVALID_STREAM = "error.invalid.stream.data";
}
