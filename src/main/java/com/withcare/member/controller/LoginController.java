package com.withcare.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withcare.member.service.LoginService;

@RestController
public class LoginController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired LoginService svc;
	
	// 로그인
	
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody Map<String, String> params, HttpSession session){
		
		log.info("param : {}", params);
		log.info("session ID : "+session.getId());
		
		result = new HashMap<String, Object>();
		boolean success = svc.login(params);
		result.put("success", success);
		
		return result;
	}
	
	// 로그아웃
	
	@PostMapping("/logout") // 일단 세션 처리 해놨는데 토큰 버리는 식으로 바꿀거
	public Map<String, Object> logout(HttpSession session) {
	    Map<String, Object> result = new HashMap<>();
	    
	    session.invalidate(); 
	    
	    result.put("success", true);
	    return result;
	}
	
	// 아이디 찾기
	
	@GetMapping("/find-id")
    public ResponseEntity<String> findId(
            @RequestParam String name,
            @RequestParam String year,
            @RequestParam String email) {

        String userId = svc.findId(name, year, email);

        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 아이디가 없습니다.");
        }
    }
	
	// 비밀번호 찾기
	
	@GetMapping("/find-pw")
	public ResponseEntity<String> findPw(
	        @RequestParam String id,
	        @RequestParam String name,
	        @RequestParam String year,
	        @RequestParam String email) {

	    String userPw = svc.findPw(id, name, year, email);

	    if (userPw != null) {
	        return ResponseEntity.ok(userPw);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 비밀번호가 없습니다.");
	    }
	}
	
	// 비밀번호 재설정

	@PutMapping("/reset-pw")
	public ResponseEntity<String> resetPw(@RequestBody Map<String, String> params) {
		
		log.info("resetPw 컨트롤러 진입, params: {}", params);
		
	    String id = params.get("id");
	    String newPw = params.get("newPw");

	    boolean success = svc.resetPw(id, newPw);

	    if (success) {
	        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 변경에 실패했습니다.");
	    }
	}

	
	
	
	
	


}
