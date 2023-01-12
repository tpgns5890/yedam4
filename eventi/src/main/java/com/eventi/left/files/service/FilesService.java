package com.eventi.left.files.service;

import java.util.List;

public interface FilesService {

	public List<FilesVO> fileList(String cNo); // 공모전 1건에 대한 파일 전체조회

	public FilesVO getfile(FilesVO vo); // 공모전 1건에 대한 파일 1건조회

	public int insertFile(FilesVO vo); // 파일 1건 추가

}
