package com.withcare.profile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.withcare.profile.dao.TimelineDAO;

@Service
public class TimelineService {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired TimelineDAO dao;

}
