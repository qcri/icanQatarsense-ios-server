
package com.qsense.common;


/**
 * @author Neeraj
 *
 */

public class Error implements java.io.Serializable
{
        
    /**
	 * 
	 */
	private static final long serialVersionUID = -7058561200331017051L;
	private String messageId = null;
    private String type = null;
    private String reference = null;
    private String url = null;
    private Object argList[] = new Object[0];
    
    /**
     * Default Constructor
     */
    public Error()
    {
    }

    /**
     * Constructor with no argument parms 
     * 
     * @param id Message id to use in the resource bundle
     * @param type Type of message. Warning, Error, etc   
     */
    public Error( String messageId, String type )
    {
        this.messageId = messageId;
        this.type = type;
    }
    
    /**
     * Default Constructor 
     * 
     * @param id Message id to use in the resource bundle
     * @param type Type of message. Warning, Error, etc   
     * @param argList Arg list to pass to message. See MessageFormatter.
     */
    public Error( String messageId, String type, Object argList[] )
    {
        this.messageId = messageId;
        this.type = type;
        if( argList != null )
            this.argList = argList;
    }

    /**
     * Constructor with only one argument  
     * 
     * @param id Message id to use in the resource bundle
     * @param type Type of message. Warning, Error, etc   
     * @param argument Single argument to pass to message. See MessageFormatter.
     */
    public Error( String messageId, String type, Object argument )
    {
        this.messageId = messageId;
        this.type = type;
        addArgument(argument);
    }

    /**
     * Overloaded Constructor to create an error object containing an id, reference,
     * type and argList. 
     * 
     * @param messageId Message id to use in the resource bundle
     * @param reference Reference area to be used for id 
     * @param type Type of message. Warning, Error, etc   
     * @param argList Arg list to pass to message. See MessageFormatter.
     */
    public Error( String messageId, String reference, String type, Object argList[], String url )
    {
        this.messageId = messageId;
        this.reference = reference;
        this.type = type;
        this.url = url;
        if( argList != null)
            this.argList = argList;
    }   

    /**
     * Overloaded Constructor to create an error object containing an id, reference,
     * type and argList. 
     * 
     * @param messageId Message id to use in the resource bundle
     * @param reference Reference area to be used for id 
     * @param type Type of message. Warning, Error, etc   
     * @param argument Single argument for message. See MessageFormatter.
     */
    public Error( String messageId, String reference, String type, Object argument, String url )
    {
        this.messageId = messageId;
        this.reference = reference;
        this.type = type;
        this.url  = url;
        addArgument(argument);
    }   

    /**
     * Return id for error. This can also be used
     * as the message id
     * 
     * @return
     */
    public String getMessageId()
    {
        return messageId;
    }

    /**
     * Return the error type. This can be overloaded
     * with custom types. Current types include.
     * <ul>
     * <li>TYPE_ERROR</li>
     * <li>TYPE_WARNING</li>
     * <li>TYPE_INFO</li>
     * <li>TYPE_DEBUG</li>
     * <li>TYPE_TRACE</li>
     * </ul>
     * 
     * @return A type defined in the Error instance or 
     * custom type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * Return arglist if applicable for error id
     * 
     * @return A object array of arguments
     */
    public Object[] getArgList()
    {
        return (Object[])argList;
    }

    /**
     * Return the reference data. This can 
     * be package and field information for mapping
     * to user screens, or other type of reference
     * information
     * 
     * @return A String containing reference details
     */
    public String getReference()
    {
        return reference;
    }

    /**
     * Return the url reference to more user documentation
     * online for the specific error
     * 
     * @return A String containing url detail message
     */
    public String getURL()
    {
        return url;
    }    
    
    /**
     * Set the message id. 
     * 
     * @param messageId A String message id
     */ 
    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    /**
     * Set the type. 
     * 
     * @param type A Type of error
     */     
    public void setType(String type)
    {
        this.type = type;
    }    
    
    /**
     * Set the reference data. This can be a package
     * and field information used in user screen mapping
     * or other type or cross reference information.
     * 
     * @param reference A String containing reference
     * information
     */
    public void setReference(String reference)
    {
        this.reference = reference;
    }
    
    /**
     * Add argument to list 
     * 
     * @param argument
     */
    public void addArgument( Object argument )
    {
        //argList = ArrayList.add( argList, argument );
    }
}

