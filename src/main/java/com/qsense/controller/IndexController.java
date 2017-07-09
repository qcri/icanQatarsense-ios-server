package com.qsense.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "")
public class IndexController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
	    return "home";
	}
		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Boolean authentication_error, Model model) {
		if(authentication_error != null && authentication_error == Boolean.TRUE) {
			model.addAttribute("authentication_error", "Wrong user name or password");
		} else {
			model.addAttribute("authentication_error", "");
		}
		return "index";
	}
	
}
