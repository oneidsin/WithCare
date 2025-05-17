package com.withcare.profile.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.withcare.profile.dao.ProfileDAO;
import com.withcare.profile.dto.ProfileDTO;

@Service
public class ProfileService {

	@Autowired ProfileDAO dao;
	
	Logger log = LoggerFactory.getLogger(getClass());

	public boolean saveProfile(Map<String, Object> map) {
        return dao.insertProfile(map) > 0;
    }

	 public ProfileDTO getProfile(String id) {
	        return dao.getProfileById(id);
	}

	 public int updateProfile(ProfileDTO dto) {
	        return dao.updateProfile(dto);
}
}
