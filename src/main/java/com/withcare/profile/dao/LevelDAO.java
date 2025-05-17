package com.withcare.profile.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface LevelDAO {

    Map<String, Object> getMemberLevel(String id);

    Map<String, Object> getMemberLevelCondition(String id);

    //Map<String, Object> getMemberLevelCount(String id);
}
