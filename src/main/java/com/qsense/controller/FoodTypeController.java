package com.qsense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.service.FoodTypeService;
import com.qsense.util.logger.QSenseLogger;


/**
 * Food Type Controller
 * 
 * @author Kushal
 * 
 */

@RestController
@RequestMapping(value = "/foodType")

public class FoodTypeController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired(required = true)
	private FoodTypeService foodTypeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object fetchAll() throws Exception {	
		return foodTypeService.getAll();
	}
	
}
