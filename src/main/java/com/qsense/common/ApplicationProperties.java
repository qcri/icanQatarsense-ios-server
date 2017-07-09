package com.qsense.common;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import com.qsense.exception.QSenseException;

/**
 * Application Properties class is hold the properties of application.properties
 * file.
 * 
 * @author Neeraj
 * 
 */

public class ApplicationProperties extends PropertyPlaceholderConfigurer {

	/**
	 * 
	 */
	private static Properties properties = new Properties();

	/**
	 * default constructor this load the all application properties in
	 * properties object
	 * 
	 * @throws IOException
	 */
	public ApplicationProperties() throws IOException {
		super();
	}

	/**
	 * getProperty(key) return the property value on basis of key.
	 * 
	 * @param key
	 *            the property key.
	 * @return the value in this property list with the specified key value.
	 */
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * Set a location of a properties file to be loaded and load the properties.
	 */
	@Override
	public void setLocation(Resource location) {
		super.setLocation(location);
		loadProperties();
	}

	/**
	 * Set locations of properties files to be loaded and load the properties.
	 * 
	 */
	@Override
	public void setLocations(Resource[] locations) {
		super.setLocations(locations);
		loadProperties();
	}

	/**
	 * Load properties into the given instance.
	 */
	private void loadProperties() {
		try {
			super.loadProperties(properties);
		} catch (IOException e) {
			throw new QSenseException(e);
		}
	}
}
