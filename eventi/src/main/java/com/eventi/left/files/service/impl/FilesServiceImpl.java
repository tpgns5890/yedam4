package com.eventi.left.files.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;

@Service
public class FilesServiceImpl implements FilesService {

	@Autowired
	FilesMapper mapper;

	// 파일저장된 폴더경로.
	@Value("${spring.servlet.multipart.location}")
	String filePath;

	@Override
	public int insertFile(FilesVO vo) {
		return mapper.insertFile(vo);
	}

	@Override
	public List<FilesVO> fileList(String No) {
		return mapper.fileList(No);
	}

	@Override
	public FilesVO getfile(FilesVO vo) {
		return mapper.getfile(vo);
	}

	@Override
	public int updateFile(FilesVO vo) {
		return mapper.updateFile(vo);
	}

	@Override
	public int deleteFile(String No) {

		// 파일명을 담아서 하드디스크 삭제
		List<FilesVO> fileList = mapper.fileList(No);
		int result = 0;

		// 리스트 반복문
		for (FilesVO oneFile : fileList) {
			File file = new File(filePath + "/" + oneFile.getSevNm()); // 파일경로설정

			if (file.exists()) {
				if (file.delete()) {
					result = mapper.deleteFile(No); // 타겟번호삭제
				} 
			}
		}
		return result;

	}

	@Override
	public int oneDeleteFile(String No) {
		return mapper.deleteFile(No);
	}

}