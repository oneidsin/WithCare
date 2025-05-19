package com.withcare.post.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.withcare.board.dao.BoardDAO;
import com.withcare.post.dao.PostDAO;
import com.withcare.post.dto.LikeDislikeDTO;
import com.withcare.post.dto.PostDTO;

@Service
public class PostService {

	Logger log = LoggerFactory.getLogger(getClass());
	
	HashMap<String, Object> result = null;
	
	private int post_count = 5;
	
	@Autowired PostDAO dao;
	@Autowired BoardDAO boardDao;
	
    private final String uploadDir = "/uploads";
	
	public boolean postWrite(PostDTO dto, MultipartFile[] files) {
	    // 게시판 com_yn 가져오기
	    boolean boardComYn = boardDao.boardComYn(dto.getBoard_idx());
	    
	    // 게시글에 적용
	    dto.setCom_yn(boardComYn);
	    
        int row = dao.postWrite(dto);
        if (row > 0 && files != null && files.length > 0) {
            saveFiles(dto.getPost_idx(), files);
        }
        return row > 0;
	}

	private void saveFiles(int post_idx, MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            try {
                String originalName = file.getOriginalFilename();
                String extension = "";

                if (originalName != null && originalName.contains(".")) {
                    extension = originalName.substring(originalName.lastIndexOf("."));
                }

                // UUID로 파일명 생성
                String savedName = java.util.UUID.randomUUID().toString() + extension;

                // 파일 저장 경로 생성
                java.nio.file.Path path = java.nio.file.Paths.get(uploadDir, savedName);

                // 폴더가 없으면 생성
                java.nio.file.Files.createDirectories(path.getParent());

                // 파일 저장
                file.transferTo(path.toFile());

                // DB에 파일 URL 저장
                Map<String, Object> param = new HashMap<>();
                param.put("post_idx", post_idx);
                param.put("file_url", savedName);  // 혹은 full path로 저장하고 싶으면 변경

                dao.insertFile(param);

            } catch (Exception e) {
                e.printStackTrace();
                // 파일 저장 실패시에도 무조건 실패처리할지, 로그만 남기고 진행할지 정책에 따라 조정 필요
            }
        }
    }

	public boolean postUpdate(PostDTO dto, String userId, MultipartFile[] files) {
		
	    // 1) 게시글 작성자 조회
	    String writerId = dao.postWriter(dto.getPost_idx());
	    if (writerId == null || !writerId.equals(userId)) {
	        // 작성자가 아니면 수정 불가
	        return false;
	    }
		
		int row = dao.postUpdate(dto);
		if (row > 0 && files != null && files.length > 0) {
            saveFiles(dto.getPost_idx(), files);
        }
		return row>0;
	}

	public boolean postDelete(PostDTO dto, String userId) {
		
	    // 1) 게시글 작성자 조회
	    String writerId = dao.postWriter(dto.getPost_idx());
	    if (writerId == null) {
	        return false;
	    }
	    
	    // 2) 작성자 아니면 관리자 여부 체크
	    if (!writerId.equals(userId)) {
	        int lv_idx = dao.userLevel(userId);
	        if (lv_idx != 7) {
	            // 작성자도 아니고 관리자도 아니면 삭제 불가
	            return false;
	        }
	    }
		
		int row = dao.postDelete(dto);
		return row>0;
	}

	public Map<String, Object> postDetail(int post_idx, boolean up) {
		result = new HashMap<String,Object>();
		
		if (up) {
			dao.upHit(post_idx); // 상세보기 시 조회수 증가
		}
		
	    PostDTO dto = dao.postDetail(post_idx);
	    result.put("post", dto);
	    result.put("likes", dao.likeCnt(post_idx)); // 추천 수
	    result.put("dislikes", dao.dislikeCnt(post_idx)); // 비추천 수
	    List<Map<String, String>> photos = dao.fileList(post_idx);
	    result.put("photos", photos);

	    return result;
	}
	
	public Map<String, Object> postList(int page, int board_idx) {
	    Map<String, Object> result = new HashMap<String, Object>();
	    result.put("page", page);
	    int offset = (page - 1) * post_count; // 페이징 처리하지기

	    List<PostDTO> postList = dao.postList(offset, post_count, board_idx); // 게시글 목록, 한 페이지 당 보여줄 게시글 수, 게시판 번호
	    int totalPosts = dao.postPages(board_idx);
	    int totalPages = (int) Math.ceil((double) totalPosts / post_count); // 한 페이지당 보여줄 게시글 개수로 나눈 후 올림 (double 값 정수 변환)
	    
	    List<Map<String, Object>> postMapList = new ArrayList<>();

	    for (PostDTO dto : postList) {
	        Map<String, Object> postMap = new HashMap<>();
	        postMap.put("post", dto);
	        postMap.put("likes", dao.likeCnt(dto.getPost_idx()));
	        postMap.put("dislikes", dao.dislikeCnt(dto.getPost_idx()));
	        List<Map<String, String>> photos = dao.fileList(dto.getPost_idx());
	        postMap.put("photos", photos);
	        postMapList.add(postMap);
	    }
	    
	    result.put("list", postMapList);
	    result.put("totalPages", totalPages);
	    result.put("totalPosts", totalPosts);
	    result.put("page", page);

	    return result;
	}

	public boolean handleLike(LikeDislikeDTO dto) {
		String id = dto.getId(); // 같은 ID 인지 확인하려고 id 값 dto 에서 가져옴.
	    int post_idx = dto.getPost_idx();
	    int newType = dto.getLike_type(); // 추천 상태 확인

	    Integer currentType = dao.LikeType(id, post_idx); // Integer 가 아니라 int 면 null 값 지정 못함. (아무것도 안 눌렀을 때 상태)
	    
	    if (currentType == null) {
	        currentType = 0;
	    }
	    
	    if (currentType == newType) {
	        // 같은 상태 → 취소
	        return dao.likeDelete(id, post_idx) > 0;
	    } else if (currentType == 0) {
	        // 처음 → 삽입
	        return dao.likeInsert(dto) > 0;
	    } else {
	        // 다른 상태 → 업데이트
	        return dao.likeUpdate(dto) > 0;
	    } 
	}

	public List<Map<String, String>> fileList(int post_idx) {
		return dao.fileList(post_idx);
	}

	public boolean fileDelete(String file_idx, String userId) {
	    // 파일이 속한 게시글 확인 -> 작성자 권한 체크 필요
	    int post_idx = dao.fileIdx(file_idx);
	    String writerId = dao.postWriter(post_idx);

	    if (!writerId.equals(userId)) {
	        // 권한 없으면 실패
	        return false;
	    }
	    // 파일 삭제
	    int row = dao.fileDelete(file_idx);
	    if (row > 0) {
	        // 실제 저장된 파일도 삭제 처리 (파일시스템)
	        // 실제 파일 경로 생성 후 삭제
	    }
	    return row > 0;
	}
}
