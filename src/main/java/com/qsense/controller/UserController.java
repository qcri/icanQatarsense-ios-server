package com.qsense.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.qsense.service.UserService;
import com.qsense.transfer.ResponseTO;
import com.qsense.transfer.UserTO;
import com.qsense.util.logger.QSenseLogger;


/**
 * User Controller
 * 
 * @author Neeraj
 * 
 */

@RestController
@RequestMapping(value = "/user")

public class UserController extends CommonController{

	QSenseLogger logger = QSenseLogger.getLogger(getClass());
	
	@Autowired(required = true)
	private UserService userService ;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Object create(@RequestBody String jsonString) throws Exception {
		UserTO userTO = null;
	    try {
	    	userTO = new Gson().fromJson(jsonString, UserTO.class);
		} catch (Exception e) {
			logger.error("Error occured while creating  with data [%s]: [%s]", jsonString, e.getMessage());
			throw e;
		}	   
		return userService.create(userTO);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object fetchAll() throws Exception {		
		return userService.getAll();
	}
	
	@RequestMapping(value = "/participant", method = RequestMethod.GET)
	public Object fetchAllUnAssociatedParticipant() throws Exception {		
		return userService.getAllUnAssociatedParticipant();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object delete(@PathVariable(value = "id")final Long id) throws Exception {
		logger.info("Begin : UserController.delete");	
		return userService.delete(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object get(@PathVariable(value = "id")final Long id) throws Exception {
		logger.info("Begin : UserController.get");	
		return userService.get(id);
	}
	
	@RequestMapping(value = "/getCoins", method = RequestMethod.GET)
	public Object getCoins(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Begin : UserController.getCoins");	
		Long userId = Long.parseLong(request.getHeader("userId"));
		return userService.getCoins(userId);
	}
	
	@RequestMapping(value = "/getCoinsForGroup", method = RequestMethod.GET)
	public Object getCoinsForGroup(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Begin : UserController.getCoinsForGroup");	
		Long userId = Long.parseLong(request.getHeader("userId"));
		return userService.getCoinsForGroup(userId);
	}
	
	@RequestMapping(value = "/getOryx", method = RequestMethod.GET)
	public Object getOryx(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Begin : UserController.get");	
		Long userId = Long.parseLong(request.getHeader("userId"));
		return userService.getOryx(userId);
	}
	
	@RequestMapping(value = "/getAllOryx", method = RequestMethod.GET)
	public Object getAllOryx(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Begin : UserController.get");	
		return userService.getAllOryx();
	}

	@RequestMapping(value = "/updateCoins", method = RequestMethod.POST)
	public Object updateCoins(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		try{
			Long userId = Long.parseLong(request.getHeader("userId"));
			Long coins = Long.parseLong(request.getHeader("coins"));			
			boolean returnResult = userService.updateCoins(userId, coins);
			if(returnResult){
				responseTO.setSuccess(true);
			}
		}
		catch(Exception e)
		{
			logger.error("Error occured while updating coins.");		
			responseTO.setSuccess(false);
		}
		return responseTO;
	}
	
	@RequestMapping(value = "/updateOryx", method = RequestMethod.POST)
	public Object updateOryx(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		try{
			Long userId = Long.parseLong(request.getHeader("userId"));
			Long oryxId = Long.parseLong(request.getHeader("oryxId"));			
			boolean returnResult = userService.updateOryx(userId, oryxId);
			if(returnResult){
				responseTO.setSuccess(true);
			}
		}
		catch(Exception e)
		{
			logger.error("Error occured while updating oryx.");			
			responseTO.setSuccess(false);
		}
		return responseTO;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	protected Object update(@PathVariable(value = "id")final Long id, @RequestBody String jsonString ) throws Exception {	    	
		logger.info("Begin : UserController.update");	
		try {
			UserTO userTO = new Gson().fromJson(jsonString, UserTO.class);
			userTO.setId(id);
			return userService.update(userTO);
		} catch (Exception e) {
			logger.error("Error occured while creating  with data [%s]: [%s]", jsonString, e.getMessage());
			throw e;
		}		
	}
		
	
}
