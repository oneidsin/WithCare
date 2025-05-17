package com.withcare.board.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.withcare.board.dao.BoardDAO;
import com.withcare.board.dto.BoardDTO;

@Service
public class BoardService {

	Logger log = LoggerFactory.getLogger(getClass());
	
	HashMap<String, Object> result = null;
	
	@Autowired BoardDAO dao;

	public boolean boardWrite(BoardDTO boardDTO) {
		int row = dao.boardWrite(boardDTO);
		return row>0;
	}

	public boolean boardUpdate(BoardDTO boardDTO) {
		int row = dao.boardUpdate(boardDTO);
		return row>0;
	}

	public boolean boardDelete(BoardDTO boardDTO) {
		int row = dao.boardDelete(boardDTO);
		return row>0;
	}
	
}
