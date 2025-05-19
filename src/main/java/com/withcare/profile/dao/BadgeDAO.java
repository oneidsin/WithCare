package com.withcare.profile.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BadgeDAO {

    List<Map<String, Object>> getMemberBadgeList(String id);

    Map<String, Object> getMemberAcquiredBadge(String id);
}
