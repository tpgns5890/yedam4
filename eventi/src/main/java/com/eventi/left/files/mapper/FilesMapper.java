package com.eventi.left.files.mapper;

import java.util.List;

import com.eventi.left.files.service.FilesVO;

public interface FilesMapper {
	
	public List<FilesVO> fileList(String cNo); // 공모전 1건에 대한 파일 전체조회
	
	public FilesVO getfile(FilesVO vo); // 공모전 1건에 대한 파일 1건조회
	
	public int insertFile(FilesVO vo); //파일 1건 추가
	
	public int NameCheck(FilesVO vo); //중복 파일명 확인(0이면 없음)

}
