package com.withcare.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.withcare.member.service.LoginService;

@RestController
public class LoginController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired LoginService svc;
	
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody Map<String, String> params, HttpSession session){
		
		log.info("param : {}", params);
		log.info("session ID : "+session.getId());
		
		result = new HashMap<String, Object>();
		boolean success = svc.login(params);
		result.put("success", success);
		
		return result;
	}


}
