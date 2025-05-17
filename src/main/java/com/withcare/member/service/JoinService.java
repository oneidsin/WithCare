package com.withcare.member.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.withcare.member.dao.JoinDAO;


@Service
public class JoinService {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired JoinDAO dao;

	public boolean overlay(String id) {
		
		int cnt = dao.overlay(id);
		
		return cnt == 0;
	}

	public boolean join(Map<String, String> params) {
		int row = dao.join(params);
		return row > 0;
	}


}
