package com.qsense.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {
     private static ApplicationContext applicationContext = null;

      public static ApplicationContext getApplicationContext() {
          return applicationContext;
      }
      public void setApplicationContext(ApplicationContext context) throws BeansException {
            // Assign the ApplicationContext into a static variable
    	  	applicationContext = context;
      }
      public static <T> T getBean(String beanId,Class<T> classType) {
          return applicationContext.getBean(beanId,classType);
      }
      public static <T> T getBean(Class<T> classType) {
          return applicationContext.getBean(classType);
      }
}