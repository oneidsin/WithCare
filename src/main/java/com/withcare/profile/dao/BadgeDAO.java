package com.withcare.profile.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BadgeDAO {

    Map<String, Object> getMemberBadgeList(String id);
}
