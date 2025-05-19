package com.withcare.profile.controller;

import com.withcare.profile.service.BadgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class BadgeController {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BadgeService svc;

    Map<String, Object> result = null;

    @GetMapping("/{id}/badge/list")
    public Map<String, Object> getMemberBadgeList(@PathVariable String id) {
        log.info("배지 리스트 요청 : {}", id);
        result = new HashMap<>();
        result.put("result", svc.getMemberBadgeList(id));
        return result;
    }

    @GetMapping("/{id}/badge/acquired")
    public Map<String, Object> getMemberAcquiredBadge(@PathVariable String id) {
        log.info("배지 획득 요청 : {}", id);
        result = new HashMap<>();
        result = svc.getMemberAcquiredBadge(id);

        return result;
    }
    
}
