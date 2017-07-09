package com.qsense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.service.GroupService;
import com.qsense.util.logger.QSenseLogger;


/**
 * Role Controller
 * 
 * @author Neeraj
 * 
 */

@RestController
@RequestMapping(value = "/group")

public class GroupController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired(required = true)
	private GroupService groupService ;	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object fetchAll() throws Exception {		
		return groupService.getAll();
	}
	
		
}
