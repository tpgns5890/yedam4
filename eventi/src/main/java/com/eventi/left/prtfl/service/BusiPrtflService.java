package com.eventi.left.prtfl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.files.service.FilesVO;

public interface BusiPrtflService {
	//전체조회
	public List<BusiPrtflVO> busiList(BusiPrtflVO busiPrtflVO);
	
	//단건조회
	public BusiPrtflVO busiSelect(BusiPrtflVO busiPrtflVO);
	
	//등록
	public int busiInsert(BusiPrtflVO busiPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile);
}
