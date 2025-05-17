package com.withcare.post.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.withcare.post.dao.PostDAO;
import com.withcare.post.dto.PostDTO;

@Service
public class PostService {

	Logger log = LoggerFactory.getLogger(getClass());
	
	HashMap<String, Object> result = null;
	
	private int post_count = 5;
	
	@Autowired PostDAO dao;
	
	public boolean postWrite(PostDTO post_title) {
		int row = dao.postWrite(post_title);
		return row>0;
	}

	public boolean postUpdate(PostDTO post_title) {
		int row = dao.postUpdate(post_title);
		return row>0;
	}

	public boolean postDelete(PostDTO dto) {
		int row = dao.postDelete(dto);
		return row>0;
	}

	public PostDTO postDetail(int post_idx, boolean up) {
		if (up) {
			dao.upHit(post_idx);
		}
		return dao.postDetail(post_idx);
	}

	public Map<String, Object> postList(int page) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("page", page);
		int offset = (page-1)*post_count;
		result.put("list", dao.postList(offset,post_count));
		
		int totalPosts = dao.postPages();
	    int totalPages = (int) Math.ceil((double) totalPosts / post_count);
	    
	    result.put("totalPages", totalPages);
	    result.put("totalPosts", totalPosts);
		return result;
	}
	
}
