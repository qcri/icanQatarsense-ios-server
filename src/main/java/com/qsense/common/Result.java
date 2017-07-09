       
package com.qsense.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qsense.exception.QSenseWebException;
import com.qsense.util.ErrorTypeEnum;

/**
 * @author Neeraj
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@XStreamAlias(value="Result")
public class Result implements java.io.Serializable
{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 7658147265615165643L;
	/**
	 * 
	 */
	private Object            valueList[]      = new Object[0];
    private Error             errorList[]      = new Error[0];
    private boolean           errorSet         = false;
    private boolean           valueSet         = false;//set to true, if service responded true or valueList contains values
    private String            referenceId      = null;
    /**
     * Default constructor
     */
    public Result()
    {
    }
    
    /**
     * Return all errors in the list.
     * 
     * @return - List containing error messages.
     */
    public Error[] getErrorList()
    {
        return (errorList);
    }

    /**
     * Return list of errors based on type
     * 
     * @param type
     *            Error types defined in
     * 
     * @return A collection of Errors of a specific type
     */
    public Error[] getErrorListByType(String type)
    {
        Error returnErrorList[] = new Error[0];
        int count = 0;

        // Calculate size of new array
        for (int i = 0; i < errorList.length; i++)
        {
            if (errorList[i].getType().equals(type))
                count++;
        }

        // Allocate return array
        if (count != 0)
        {
            returnErrorList = new Error[count];
            int idx = 0;

            for (int i = 0; i < errorList.length; i++)
            {
                if (errorList[i].getType().equals(type))
                {
                    returnErrorList[idx++] = errorList[i];
                }
            }
        }
        return (returnErrorList);
    }

    /**
     * Set the error code.
     * 
     * @param messageId
     *            Message id for the resource string to load from the
     *            definitions defined for the service component. See
     * @see com.elinkbiz.common.CommonResultConstants or derived children for
     *      more details.
     * @param type
     *            Error type of error. See definitions in Error for available
     *            types. Custom types are also allowed
     * @param arglist =
     *            List of arguments available for the error, in case user wants
     *            to map messages to presentation layer via MessageFormat.
     */
    public final void addError(String messageId, String type, Object argList[])
    {
        // Create new Error record
        Object cloneList[] = (argList!=null)?argList.clone():null;
        
        Error error = new Error(messageId, type, cloneList );
        
        addError(error);
    }

    /**
     * Set the error code.
     * 
     * @param messageId
     *            Message id for the resource string to load from the
     *            definitions defined for the service component.
     * @param reference
     *            to map to the Struts error tag.
     * @param type
     *            Error type of error. See definitions in Error for available
     *            types. Custom types are also allowed
     * @param arglist =
     *            List of arguments available for the error, in case user wants
     *            to map messages to presentation layer via MessageFormat.
     */
    public final void addError( String errorId, 
                                String reference, 
                                String type,
                                Object argList[], 
                                String url )
    {
        // Create new Error record
        Error error = 
              new Error(errorId, reference, 
                        type, ((Object[]) argList.clone()), 
                        url );
        addError(error);
    }

    /**
     * Add Error instance to result
     * 
     * @param error
     *            A Error class
     */
    public final void addError(Error error)
    {
        errorList = (Error[]) ArrayList.add(errorList, error);
        errorSet = true;
    }

    /**
     * Clears all the error messages within errors list.
     */
    public void clearErrorList()
    {
        errorSet = false;
        ArrayList.clear(errorList);
    }

    /**
     * Return the value of the result request. Returns the first value
     * in result set if multiple exist.
     * 
     * @return A Object result
     */
    @JsonIgnore
    public Object getValue()
    {
        Object returnBean = null;
        
        if(valueList!=null && valueList.length !=0 )
        {
            returnBean = valueList[0];
        }        
        return( returnBean );
    }    
    
    /**
     * Return the value of the result request. Results are returned as
     * collection of objects, but need to be cast like Vectors, etc. This is any
     * application specific data. This is always stored in an Array to support
     * single or multiple objects being returne
     * 
     * @return A Concrete Collection of objects
     */
    public Object[] getValueList()
    {
        return valueList;
    }

    /**
     * Set a collection of objects to be returned. This is any application
     * specific data. This is always stored in an Array to support single or
     * multiple objects being returned. Replace any existing items. This method
     * can not take Collection as its argument, for Collection setValueList should be used.
     * 
     * @param valueList
     *            Array of Object data to be stored in the result
     */
    public void setValue(Object value)
    {
    	if(value instanceof Collection){
    		
    		throw new QSenseWebException("XCheckWebException : Illegal argument, setValue method can not take its argument as Collection",
					CommonReturnCodes.ERROR_EXCEPTION, ErrorTypeEnum.TYPE_ERROR.toString());
    		
    	}else{
    		this.valueList = new Object[]{ value };
            valueSet = true;
    	}
    	
        
    }    
    
    /**
     * Set a collection of objects to be returned. This is any application
     * specific data. This is always stored in an Array to support single or
     * multiple objects being returned. Replace any existing items.
     * 
     * @param valueList
     *            Array of Object data to be stored in the result
     */
    public void setValueList(Object[] valueList)
    {
        this.valueList = valueList;
        valueSet = true;
    }

    /**
     * Add value to array of Values. This incrementaly adds the value to the
     * list, unlike the sets which replaces the entire list
     * 
     * @return
     */
    public void addValue(Object value)
    {
        valueList = (Object[]) ArrayList.add(valueList, value);
        valueSet = true;
    }

    /**
     * @return the errorSet
     */
    public boolean isErrorSet()
    {
        return errorSet;
    }

    /**
     * @param errorSet
     *            the errorSet to set
     */
    public void setErrorSet(boolean errorSet)
    {
        this.errorSet = errorSet;
    }

    /**
     * @param errorSet
     *            the errorSet to set
     */
    public void setErrorList(Error errorList[])
    {
        setErrorSet(true);
        this.errorList = errorList;
    }

    /**
     * Returns true if value is set during processing
     * 
     * @return A true value if error occured
     */
    public boolean isValueSet()
    {
        return valueSet;
    }
    
    /**
     * Set a reference id
     * 
     * @param referenceId
     */
    public void setReferenceId(String referenceId)
    {
        this.referenceId = referenceId;
    }
    
    /**
     * Return associated reference id
     * 
     * @return
     */
    public String getReferenceId()
    {
        return(this.referenceId);
    }
    
    /**
     * Add collection of errors to object
     * 
     * @param errors
     */
    public void addError(Error[] errors)
    {
        for (int i = 0; i < errors.length; i++)
        {
            errorList = (Error[]) ArrayList.add(errorList, errors[i]);
        }
    }

    /**
     * Return error list as string data
     * 
     * @return
     */
    @JsonIgnore
    public String getErrorListAsString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream detailsStream = new PrintStream(byteArrayOutputStream);
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < errorList.length; i++)
        {
            String argString = "";
            Object argList[] = (Object[]) (errorList[i].getArgList());
            for (int a = 0; a < argList.length; a++)
            {
                Object argument = argList[a];
                argString += argument;
                if (a + 1 < argList.length)
                    argString += ",";

                // unwind exception if exist
                if (argument instanceof Exception)
                {
                    String header = "\n   Arg [" + i + "] details:\n";
                    String footer = "\n\n";
                    detailsStream.write(header.getBytes(), 0, header.length());
                    ((Exception) argument).printStackTrace(detailsStream);
                    detailsStream.write(footer.getBytes(), 0, footer.length());
                }
            }

            stringBuffer.append("( ID="
                    + errorList[i].getMessageId()
                    + ", TYPE="
                    + errorList[i].getType()
                    + ", REF="
                    + errorList[i].getReference()
                    + ", ARGS=["
                    + argString
                    + "]"
                    + " )\n"
                    + ((byteArrayOutputStream.size() != 0) ? "ID Details:"
                            + byteArrayOutputStream.toString() + "\n" : ""));
        }
        return (stringBuffer.toString());
    }
    
    
	@Override
	public String toString() {
		return "Result [valueList=" + Arrays.toString(valueList) + ", errorList=" + Arrays.toString(errorList)
				+ ", errorSet=" + errorSet + ", valueSet=" + valueSet + ", referenceId=" + referenceId + "]";
	}
    
}
