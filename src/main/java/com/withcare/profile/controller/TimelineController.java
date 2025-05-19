package com.withcare.profile.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.withcare.profile.service.TimelineService;



@RestController
public class TimelineController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired TimelineService svc;

}
