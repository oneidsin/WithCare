package com.withcare.profile.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.withcare.profile.dto.ProfileDTO;
import com.withcare.profile.service.ProfileService;

@CrossOrigin
@RestController
public class ProfileController {

	@Autowired
	ProfileService svc;

	Map<String, Object> result = null;

	Logger log = LoggerFactory.getLogger(getClass());

	// 프로필 저장 post
	 @PostMapping("/profile")
	    public Map<String, Object> saveProfile(@RequestBody Map<String, Object> pro) {
	       result = new HashMap<String, Object>();

	        try {
	            boolean success = svc.saveProfile(pro);
	            result.put("success", success);
	            result.put("message", success ? "프로필 저장 완료" : "프로필 저장 실패");
	        } catch (Exception e) {
	            log.error("[오류] 프로필 저장 중 예외 발생", e);
	            result.put("success", false);
	            result.put("message", "서버 오류");
	        }
	        return result;
	    }

	// 프로필 열람 get
	 @GetMapping("/profile/{id}")
	    public Map<String, Object> getProfile(@PathVariable("id") String id) {
	        result = new HashMap<String, Object>();
	        ProfileDTO dto = svc.getProfile(id);

	        if (dto != null) {
	            result.put("status", "success");
	            result.put("data", dto);
	        } else {
	            result.put("status", "fail");
	            result.put("message", "프로필을 찾을 수 없습니다.");
	        }
	        return result;
	    }
	
	// 회원 개인 정보 수정 기능 put
	 @PutMapping("/{id}/update")
	    public Map<String, Object> updateProfile(@PathVariable("id") String id, @RequestBody ProfileDTO dto) {
	        Map<String, Object> result = new HashMap<>();

	        try {
	            dto.setId(id); // 경로에서 받은 id를 DTO에 명시적으로 설정
	            int updated = svc.updateProfile(dto);
	            result.put("status", updated > 0 ? "success" : "fail");
	        } catch (Exception e) {
	            result.put("status", "error");
	            result.put("message", e.getMessage());
	        }

	        return result;
	    }
	 
	 
	// 프로필 확인 기능 get

	// 타인이 프로필 확인하는 기능 get

}
