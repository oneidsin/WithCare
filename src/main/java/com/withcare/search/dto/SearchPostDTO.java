package com.withcare.search.dto;

import java.sql.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
// 게시글 검색 결과용 DTO
public class SearchPostDTO {
	private int postIdx;
    private String postTitle;
    private String postContent;
    private Date postCreateDate;

    public int getPostIdx() { return postIdx; }
    public void setPostIdx(int postIdx) { this.postIdx = postIdx; }

    public String getPostTitle() { return postTitle; }
    public void setPostTitle(String postTitle) { this.postTitle = postTitle; }

    public String getPostContent() { return postContent; }
    public void setPostContent(String postContent) { this.postContent = postContent; }

    public Date getPostCreateDate() { return postCreateDate; }
    public void setPostCreateDate(Date postCreateDate) { this.postCreateDate = postCreateDate; }

}
