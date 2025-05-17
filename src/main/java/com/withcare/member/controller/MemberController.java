package com.withcare.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.withcare.member.service.MemberService;

@CrossOrigin
@RestController
public class MemberController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired MemberService svc;
	
	@PutMapping("/delete/{id}")
	public Map<String, Object> delete(@PathVariable String id) {
		
	    log.info("회원 탈퇴 요청: " + id);
	    
	    result = new HashMap<>();
	    boolean success = svc.delete(id);
	    result.put("success", success);
	    return result;
	}

}
