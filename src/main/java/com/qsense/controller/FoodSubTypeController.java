package com.qsense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.service.FoodSubTypeService;
import com.qsense.util.logger.QSenseLogger;


/**
 * Food Sub-Type Controller
 * 
 * @author Kushal
 * 
 */

@RestController
@RequestMapping(value = "/foodSubType")

public class FoodSubTypeController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired(required = true)
	private FoodSubTypeService foodSubTypeService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object fetchAll() throws Exception {
		return foodSubTypeService.getAll();
	}
	
	@RequestMapping(value = "/foodType/{foodTypeId}", method = RequestMethod.GET)
	public Object fetchAllByFoodType(@PathVariable(value = "foodTypeId")final Long foodTypeId) throws Exception {	
		return foodSubTypeService.getAllByFoodTypeId(foodTypeId);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object fetchById(@PathVariable(value = "id")final Long id) throws Exception {	
		return foodSubTypeService.getById(id);
	}
	
}
