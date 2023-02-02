package com.eventi.left.prtfl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.prtfl.mapper.McMoveMapper;
import com.eventi.left.prtfl.service.McMoveService;
import com.eventi.left.prtfl.service.McMoveVO;

@Service
public class McMoveServiceImpl implements McMoveService{
	@Autowired McMoveMapper mcMoveMapper;
	@Autowired UploadFileMethod newUp;
	
	//사회자별 전체 동영상 리스트
	@Override
	public List<McMoveVO> moveList(McMoveVO mcMoveVO, PagingVO paging) {
		paging.setTotalRecord(mcMoveMapper.count(mcMoveVO));
		paging.setPageUnit(2);
		mcMoveVO.setFirst(paging.getFirst());
		mcMoveVO.setLast(paging.getLast());
		
		return mcMoveMapper.moveList(mcMoveVO);
	}

	//사회자별 동영상 개수
	@Override
	public int count(McMoveVO mcMoveVO) {
		return mcMoveMapper.count(mcMoveVO);
	}

	//동영상 추가
	@Override
	public int moveInsert(McMoveVO mcMoveVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		int r = mcMoveMapper.moveInsert(mcMoveVO);
		
		List<FileDto> list= new ArrayList<FileDto>();
	      //파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
	      try {
			list = newUp.uploadFiles(uploadFile, mcMoveVO.getMoveNo(), "T06");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	//다음 동영상 번호 찾기
	@Override
	public String getSeq() {
		return mcMoveMapper.getSeq();
	}
	
	
}
