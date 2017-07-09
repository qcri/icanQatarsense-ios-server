package com.qsense.common;

import java.lang.reflect.Array;

/**
 * Common utility class to support common functions
 * 
 * @author Neeraj
 * 
 */
public final class ArrayList implements java.io.Serializable
{
        
    /**
	 * 
	 */
	private static final long serialVersionUID = 2256407042718867064L;

	/**
     * Default Constructor
     */
    public ArrayList()
    {
        
    }

    /**
	 * List management worker factory to add objects
	 * 
	 * @param array
	 * @param object
	 * @return
	 */
	public final static Object[] add(Object array[], Object object)
	{
		int GROW_SIZE = 1;
		
		if( array != null )
		{
    		// Find last index location
    		int idx = 0;
    		for (; idx < array.length && array[idx] != null; )
    		{
    			idx++;
    		}
    
    		// Assign object value
    		if (idx < array.length)
    		{
    			array[idx] = object;
    		}
    		else
    		{
    			// Increase size of array to hold new objects
    			// This is an array factory method
    			Object newArray[] =
    				(Object[]) Array.newInstance(
    					array.getClass().getComponentType(),
    					array.length + GROW_SIZE);
    					
    			// Copy data to new structure
    			System.arraycopy( array, 0, newArray, 0, array.length);
    			array = newArray;
    			
    			// Assign object in array
    			array[idx] = object;
    		}
		}
		return (array);
	}
	
	/**
	 * Return an object mapped to requested element
	 * 
	 * @param array List containing all object
	 * @param object Value of the object being returned
	 * @return A object instance that matches the request
	 */
	public final static Object get(Object array[], Object object)
	{
		Object found = null;
		
		for( int idx=0; idx < array.length; idx++ )
		{
			if( array[idx] == object )
			{
				found = array[idx];
			}
		}
		return( found );
	}
	
	/**
	 * Clear the list
	 */
	public final static void clear(Object array[])
	{
		for (int idx = 0; idx < array.length; idx++)
			array[idx] = null;
	}

	/**
	 * List management worker factory to remove objects
	 * 
	 * @param array
	 * @param object
	 * @return
	 */
	public final static Object[] remove(Object array[], Object object)
	{
		// Find last index location
		int idx = 0;
		for (; idx < array.length && array[idx] != object; idx++)
			break;

		if (array[idx] == object)
			array[idx] = null;

		return (array);
	}
}
