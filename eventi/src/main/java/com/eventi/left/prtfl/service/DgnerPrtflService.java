package com.eventi.left.prtfl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.design.service.DesignVO;
import com.eventi.left.files.service.FilesVO;

public interface DgnerPrtflService {
	//디자이너상세조회 
	public DgnerPrtflVO dgnerSelect(DgnerPrtflVO dgnerPrtflVO);
	
	//디자이너별 디자인 리스트
	public List<DesignVO> desginList(DesignVO designVO, PagingVO paging);
	
	//디자이너 등록 여부
	public int checkDgner(DgnerPrtflVO dgnerPrtflVO);
	
	//디자이너 포트폴리오 입력
	public int dgnerInsert(DgnerPrtflVO dgnerPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile);
}
