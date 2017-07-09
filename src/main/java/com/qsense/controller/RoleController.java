package com.qsense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.service.RoleService;
import com.qsense.util.logger.QSenseLogger;


/**
 * Role Controller
 * 
 * @author Neeraj
 * 
 */

@RestController
@RequestMapping(value = "/role")

public class RoleController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired(required = true)
	private RoleService roleService ;	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object fetchAll() throws Exception {		
		return roleService.getAll();
	}
	
	@RequestMapping(value = "/withoutAdmin", method = RequestMethod.GET)
	public Object fetchAllWithoutAdmin() throws Exception {		
		return roleService.getAllWithoutAdmin();
	}
	
		
}
