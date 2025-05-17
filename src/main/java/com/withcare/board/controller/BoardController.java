package com.withcare.board.controller;

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

import com.withcare.board.dto.BoardDTO;
import com.withcare.board.service.BoardService;

@CrossOrigin
@RestController
public class BoardController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	HashMap<String, Object> result = null;
	
	@Autowired BoardService svc;
	
	// 게시판 생성 (PostMapping)
	@PostMapping("/board/write")
	public Map<String, Object>boardWrite(
			@RequestBody BoardDTO boardDTO,
			@RequestHeader Map<String, String>header){
		
		result = new HashMap<String, Object>();
		boolean success = svc.boardWrite(boardDTO);
		result.put("idx", boardDTO.getBoard_idx());
		result.put("success", success);
		
		return result;
	}
	
	// 게시판 수정 (PutMapping)
	@PutMapping("/board/update")
	public Map<String, Object>boardUpdate(
			@RequestBody BoardDTO boardDTO,
			@RequestHeader Map<String, String>header){
		
		result = new HashMap<String, Object>();
		boolean success = svc.boardUpdate(boardDTO);
		result.put("idx", boardDTO.getBoard_idx());
		result.put("success", success);
		
		return result;
	}
	
	// 게시판 블라인드 (PutMapping 블라인드 처리라서 blind_yn 만 true 로 바꿔주면 될듯?)
	@PutMapping("/board/delete")
	public Map<String, Object>boardDelete(
			@RequestBody BoardDTO boardDTO,
			@RequestHeader Map<String, String>header){
		
		result = new HashMap<String, Object>();
		boolean success = svc.boardDelete(boardDTO);
		result.put("idx", boardDTO.getBoard_idx());
		result.put("success", success);
		
		return result;
	}
	
}
