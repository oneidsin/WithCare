package com.withcare.member.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDAO {

	int login(Map<String, String> params);

}
