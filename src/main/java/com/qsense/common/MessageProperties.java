
package com.qsense.common;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
/**
 * @author Neeraj
 *
 */
public class MessageProperties extends ReloadableResourceBundleMessageSource {
	
	
	public String getProperty(Locale locale, String key){
		
		PropertiesHolder propHolder=getMergedProperties(locale);
		Properties properties= propHolder.getProperties();
		return properties.getProperty(key);
		
	}
	
	

}
