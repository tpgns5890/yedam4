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
import com.eventi.left.prtfl.mapper.BusiPrtflMapper;
import com.eventi.left.prtfl.service.BusiPrtflService;
import com.eventi.left.prtfl.service.BusiPrtflVO;

@Service
public class BusiPrtflServiceImpl implements BusiPrtflService{
	@Autowired BusiPrtflMapper busiPrtflMapper;
	@Autowired UploadFileMethod newUp;
	
	//전체조회
	@Override
	public List<BusiPrtflVO> busiList(BusiPrtflVO busiPrtflVO, PagingVO paging) {
		paging.setTotalRecord(busiPrtflMapper.count(busiPrtflVO));
		paging.setPageUnit(6);
		busiPrtflVO.setFirst(paging.getFirst());
		busiPrtflVO.setLast(paging.getLast());
		return busiPrtflMapper.busiList(busiPrtflVO);
	}
	
	//단건조회
	@Override
	public BusiPrtflVO busiSelect(BusiPrtflVO busiPrtflVO) {
		return busiPrtflMapper.busiSelect(busiPrtflVO);
	}

	//입력
	@Override
	public int busiInsert(BusiPrtflVO busiPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		
		//대표사진세팅
		busiPrtflVO.setImg(uploadFile[0].getOriginalFilename());
		
		int r = busiPrtflMapper.busiInsert(busiPrtflVO);
		List<FileDto> list = new ArrayList<FileDto>();
		
		try {
			list = newUp.uploadFiles(uploadFile, busiPrtflVO.getUserId(), "T08");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	//업체 등록여부 확인
	@Override
	public int checkBusi(BusiPrtflVO busiPrtflVO) {
		return busiPrtflMapper.checkBusi(busiPrtflVO);
	}
}
