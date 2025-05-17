package com.withcare.board.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.withcare.board.dto.BoardDTO;

@Mapper
public interface BoardDAO {

	int boardWrite(BoardDTO boardDTO);

	int boardUpdate(BoardDTO boardDTO);

	int boardDelete(BoardDTO boardDTO);

}
