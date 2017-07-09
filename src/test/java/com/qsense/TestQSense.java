package com.qsense;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath* :applicationContext.xml"})
public class TestQSense {
	
	private Logger logger = LogManager.getLogger(getClass());

	@Test
	public void test() {
		logger.info("Test");	
		Assert.assertTrue(true);
	}

}
