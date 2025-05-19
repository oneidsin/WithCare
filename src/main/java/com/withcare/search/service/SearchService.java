package com.withcare.search.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.withcare.post.dto.PostDTO;
import com.withcare.search.dao.SearchDAO;
import com.withcare.search.dto.SearchDTO;
import com.withcare.search.dto.SearchResultDTO;

@Service
public class SearchService {

	@Autowired SearchDAO dao;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	public SearchResultDTO searchPosts(String userId, String keyword) {
        // 1. 검색 이력 저장
        dao.insertSearchHistory(userId, keyword);

        // 2. 게시글 검색
        List<PostDTO> posts = dao.searchPostsByKeyword(userId, keyword);

        // 3. 결과 반환
        int count = (posts == null) ? 0 : posts.size();

        return null;
    }
	
}
