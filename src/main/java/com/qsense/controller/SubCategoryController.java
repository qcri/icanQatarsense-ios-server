package com.qsense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.service.SubCategoryService;
import com.qsense.util.logger.QSenseLogger;


/**
 * Role Controller
 * 
 * @author Kushal
 * 
 */

@RestController
@RequestMapping(value = "/subCategory")

public class SubCategoryController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired(required = true)
	private SubCategoryService subCategoryService;
	
	@RequestMapping(value = "/adminDashboardVisible", method = RequestMethod.GET)
	public Object fetchAllAdminDashboardVisible() throws Exception {	
		return subCategoryService.getAllAdminDashboardVisible();
	}
	
	@RequestMapping(value = "/isManuallyAllowed", method = RequestMethod.GET)
	public Object fetchAllIsManuallyAllowed() throws Exception {
		return subCategoryService.getAllIsManuallyAllowed();
	}
	
}
