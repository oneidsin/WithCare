package com.withcare.search.dto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
// 검색 결과 반환용 DTO
public class SearchResultDTO {
	 private List<SearchPostDTO> posts;
	    private int count;

	    public SearchResultDTO() {}
	    public SearchResultDTO(List<SearchPostDTO> posts, int count) {
	        this.posts = posts;
	        this.count = count;
	    }

	    public List<SearchPostDTO> getPosts() { return posts; }
	    public void setPosts(List<SearchPostDTO> posts) { this.posts = posts; }

	    public int getTotalCount() { return count; }
	    public void setTotalCount(int count) { this.count =count; }
	
}
