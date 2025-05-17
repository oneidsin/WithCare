package com.withcare.profile.service;

import com.withcare.profile.dao.LevelDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LevelService {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    LevelDAO dao;

    public Map<String, Object> getMemberLevel(String id) {
        return dao.getMemberLevel(id);
    }

    public Map<String, Object> getMemberLevelCondition(String id) {
        return dao.getMemberLevelCondition(id);
    }

    /*public Map<String, Object> getMemberLevelCount(String id) {
        Map<String, Object> result = new HashMap<>();
        result = dao.getMemberLevelCondition(id);
        return dao.getMemberLevelCount(id);
    }*/

}
