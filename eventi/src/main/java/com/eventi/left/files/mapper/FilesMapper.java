package com.eventi.left.files.mapper;

import java.util.List;

import com.eventi.left.files.service.FilesVO;

public interface FilesMapper {

	public List<FilesVO> fileList(String No); // 1건에 대한 파일 전체조회

	public FilesVO getfile(FilesVO vo); // 1건에 대한 파일 1건조회

	public int insertFile(FilesVO vo); // 파일 1건 추가

	public int updateFile(FilesVO vo); // 파일 1건 수정

	public int deleteFile(String No); /// 파일1건 여러건 삭제 
	 
	public int oneDeleteFile(String No); // 파일 1건 삭제

}