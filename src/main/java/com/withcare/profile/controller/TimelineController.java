package com.withcare.profile.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.withcare.profile.dto.TimelineDTO;
import com.withcare.profile.service.TimelineService;



@RestController
public class TimelineController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	Map<String, Object> result = null;
	
	@Autowired TimelineService svc;
	
	
	@PostMapping("/timeline/write") // id 들어가야 되는 거 아니야?
	public Map<String, Object> writeTimeline (@RequestBody TimelineDTO dto){
		log.info("일정 요청 등록: {}", dto);
		
		svc.writeTimeline(dto);
		
		result = new HashMap<String,Object>();
		result.put("status", "success");
		result.put("msg", "타임라인 등록이 완료 되었습니다.");
		
		return result;
	}
	

}
