package com.withcare.profile.controller;

import com.withcare.profile.service.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class LevelController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    LevelService svc;

    Map<String, Object> result = null;

    // 사용자 레벨 출력
    @GetMapping("/{id}/level/list")
    public Map<String, Object> getMemberLevel(@PathVariable String id) {
        log.info("레벨 요청 아이디 : {}", id);
        result = new HashMap<>();
        result = svc.getMemberLevel(id);
        return result;
    }

    // 사용자의 작성글 수, 댓글 수, 추천받은 수, 타임라인 수, 접속 수
    @GetMapping("/{id}/level/list")
    public Map<String, Object> getMemberLevelCondition(@PathVariable String id) {
        result = new HashMap<>();
        result = svc.getMemberLevelCondition(id); // 사용자의 작성글, 댓글, 추천받은 수, 타임라인 수, 접속 수를 가져옴(getLevelList)
        return result;
    }

    // 레벨 자동 계산
    @GetMapping("/{id}/level/levelCount")
    public Map<String, Object> getMemberLevelCount(@PathVariable String id) {
        log.info("레벨 자동 계산 : {}", id);
        result = new HashMap<>();
        boolean success = svc.getMemberLevelCondition(id).isEmpty();
        result.put("success", success);
        return result;
    }
}
