package com.withcare.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.withcare.post.dto.PostDTO;

@Mapper
public interface SearchDAO {

	   void insertSearchHistory(String userId, String keyword);
	    List<PostDTO> searchPostsByKeyword(String userId, String keyword);
	}


