package com.qsense.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qsense.service.UserObservationService;
import com.qsense.service.UserService;
import com.qsense.transfer.LoginResponseTO;
import com.qsense.transfer.ResponseTO;
import com.qsense.util.DeviceTypeEnum;
import com.qsense.util.logger.QSenseLogger;

/**
 * Mobile Login Controller
 * 
 * @author Neeraj
 * 
 */

@RestController
@RequestMapping(value = "")
public class MobileLoginController extends CommonController {

	QSenseLogger logger = QSenseLogger.getLogger(getClass());

	@Autowired(required = true)
	private UserService userService;
	
	@Autowired
	private UserObservationService userObservationService;

	@RequestMapping(value = "/mlogin", method = RequestMethod.POST)
	public Object login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userName = request.getHeader("userName");
		String password = request.getHeader("password");
		String permanentDeviceId = request.getHeader("permanentDeviceId");
		String timezone = request.getHeader("timezone");
		String md5Password = DigestUtils.md5Hex(password);
		LoginResponseTO loginResponseTO;
		try {
				loginResponseTO = userService.verifyLoginAndGenerateToken(
						userName, md5Password, permanentDeviceId, timezone);

		} catch (Exception e) {
			logger.error(String
					.format("Error occured while login  with userName [%s]: password : [%s] Exception  [%s] ",
							userName, password, e.getMessage()));
			throw e;
		}
		return loginResponseTO;
	}

	@RequestMapping(value = "/mloginDeviceChanged", method = RequestMethod.POST)
	public Object loginOnDeviceChanged(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userId = Long.parseLong(request.getHeader("userId"));
		String oldAuthToken = request.getHeader("authToken");
		String permanentDeviceId = request.getHeader("permanentDeviceId");
		String timezone = request.getHeader("timezone");
		LoginResponseTO loginResponseTO;
		try {
			loginResponseTO = userService.generateNewAuthToken(userId,
					oldAuthToken, permanentDeviceId, timezone);
		} catch (Exception e) {
			logger.error(String.format(
					"Error occured while login  with userId " + userId,
					e.getMessage()));
			throw e;
		}
		return loginResponseTO;
	}

	@RequestMapping(value = "/registerDevice", method = RequestMethod.POST)
	public Object registerDevice(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userId = request.getHeader("userId");
		String deviceId = request.getHeader("deviceId");
		Long sessionId = Long.parseLong(request.getHeader("sessionId"));
		Boolean isStepCountSensorAvailable = Boolean.parseBoolean(request.getHeader("isStepCountSensorAvailable"));
		String deviceType = request.getHeader("deviceType");
		DeviceTypeEnum deviceTypeEnum = getDeviceType(deviceType);
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		try {
			userService.registerDevice(userId, deviceId, isStepCountSensorAvailable, sessionId, deviceTypeEnum);
			responseTO.setSuccess(true);
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while registering device  with userId %s , deviceId %s and deviceType %s Exception %s",
							userId, deviceId, deviceType, e.getMessage()));
			throw e;
		}
		return responseTO;
	}

	@RequestMapping(value = "/mlogout", method = RequestMethod.POST)
	public Object logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userId = Long.parseLong(request.getHeader("userId"));
		Long sessionId = Long.parseLong(request.getHeader("sessionId"));
		ResponseTO responseTO = new ResponseTO();
		responseTO.setSuccess(false);
		boolean result = false;
		try {
			result = userService.removeAuthToken(userId, sessionId);
			if(result){
				responseTO.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error(String
					.format("Error occured while logout  with userId [%s] Exception [%s]",
							userId, e.getMessage()));
			throw e;
		}
		return responseTO;
	}
	
	private DeviceTypeEnum getDeviceType(String deviceType){
		
		if(!StringUtils.isEmpty(deviceType)){
			switch(deviceType.toUpperCase()){
			case "IPHONE" : return DeviceTypeEnum.IPHONE;
			case "ANDROID" : 
			default : return DeviceTypeEnum.ANDROID;
		}
		
		}
		else{
			return DeviceTypeEnum.ANDROID;
		}
		
	}

}
