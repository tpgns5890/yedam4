package com.eventi.left.files.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;

@Service
public class FilesServiceImpl implements FilesService {
	
	@Autowired FilesMapper mapper;


	@Override
	public int insertFile(FilesVO vo) {
		return mapper.insertFile(vo);
	}


	@Override
	public List<FilesVO> fileList(String cNo) {
		return mapper.fileList(cNo);
	}


	@Override
	public FilesVO getfile(FilesVO vo) {
		return mapper.getfile(vo);
	}



}
