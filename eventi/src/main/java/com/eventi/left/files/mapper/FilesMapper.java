package com.eventi.left.files.mapper;

import com.eventi.left.files.service.FilesVO;

public interface FilesMapper {
	
	public int insertFile(FilesVO vo); //파일 1건 추가
	
	public int NameCheck(FilesVO vo); //중복 파일명 확인(0이면 없음)

}
