package com.eventi.left.prtfl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.service.FilesVO;

public interface McMoveService {
	//사회자별 전체동영상리스트
	public List<McMoveVO> moveList(McMoveVO mcMoveVO, PagingVO paging);
	
	//사회자별 동영상개수
	public int count(McMoveVO mcMoveVO);
	
	//다음 동영상 번호 찾기
	public String getSeq();
	
	//동영상추가
	public int moveInsert(McMoveVO mcMoveVO, FilesVO filesVO, MultipartFile[] uploadFile);

	//동영상 삭제
	public int moveDelete(McMoveVO mcMoveVO);
}
