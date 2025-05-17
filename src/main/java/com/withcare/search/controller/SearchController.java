package com.withcare.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.withcare.search.service.SearchService;

@CrossOrigin
@RestController
public class SearchController {
	
	@Autowired SearchService svc;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	// 검색 기능
	
	
	
	
}
