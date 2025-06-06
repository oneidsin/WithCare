package com.withcare.profile.service;

import com.withcare.profile.dao.BadgeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BadgeService {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    BadgeDAO dao;

    public Map<String, Object> getMemberBadgeList(String id) {
        return dao.getMemberBadgeList(id);
    }
}
