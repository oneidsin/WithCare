package com.withcare.post.dao;

import org.apache.ibatis.annotations.Mapper;

import com.withcare.post.dto.PostDTO;

@Mapper
public interface PostDAO {

	int postWrite(PostDTO post_title);

	int postUpdate(PostDTO post_title);

	int postDelete(PostDTO dto);

	PostDTO postDetail(int post_idx);

	int upHit(int post_idx);

	Object postList(int offset, int post_count);

	int postPages();


}
