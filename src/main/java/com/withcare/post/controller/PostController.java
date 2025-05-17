package com.withcare.post.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.withcare.board.dto.BoardDTO;
import com.withcare.post.dto.PostDTO;
import com.withcare.post.service.PostService;

@CrossOrigin
@RestController
public class PostController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	HashMap<String, Object> result = null;
	
	@Autowired PostService svc;
	
	// 게시글 작성 (PostMapping)
	@PostMapping("/post/write")
	public Map<String, Object>postWrite(
			@RequestBody PostDTO post_title,
			@RequestHeader Map<String, String>header){
		
		result = new HashMap<String, Object>();
		boolean success = svc.postWrite(post_title);
		result.put("idx", post_title.getPost_idx());
		result.put("success", success);
		
		return result;
	}
	
	// 게시글 수정 (PutMapping)
	@PutMapping("/post/update")
	public Map<String, Object>postUpdate(
			@RequestBody PostDTO post_title,
			@RequestHeader Map<String, String>header){
		
		result = new HashMap<String, Object>();
		boolean success = svc.postUpdate(post_title);
		result.put("idx", post_title.getPost_idx());
		result.put("success", success);
		
		return result;
		
	}
	
	// 게시글 삭제 (PutMapping 블라인드 처리라서 blind_yn 만 true 로 바꿔주면 될듯?)
	@PutMapping("/post/delete")
	public Map<String, Object>postDelete(
			@RequestBody PostDTO dto,
			@RequestHeader Map<String, String>header){
		
		result = new HashMap<String, Object>();
		boolean success = svc.postDelete(dto);
		result.put("idx", dto.getPost_idx());
		result.put("success", success);
		
		return result;
	}
	
	// 게시글 상세보기 (GetMapping)
	@PutMapping("/post/detail/{post_idx}")
	public Map<String, Object>postDetail(
			@PathVariable String post_idx,
			@RequestHeader Map<String, String>header){

		result = new HashMap<String, Object>();
		PostDTO dto = svc.postDetail(Integer.parseInt(post_idx),true);
		result.put("post", dto);
		result.put("success", dto != null);
		
		return result;
	}
	
	// 게시글 리스트 (GetMapping) 수정해야 돼요ㅠㅠㅠㅠㅠㅠㅠㅠ
	@GetMapping("/post/list/{page}")
	public Map<String, Object> postList(
			@PathVariable String page,
			@RequestHeader Map<String, String> header){
		
		return svc.postList(Integer.parseInt(page));
	}
	
	// 게시글 추천, 비추천 (GetMapping)
	
}
