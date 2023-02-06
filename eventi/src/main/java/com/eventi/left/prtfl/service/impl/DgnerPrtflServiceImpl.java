package com.eventi.left.prtfl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.design.service.DesignVO;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.mapper.FilesMapper;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.prtfl.mapper.DgnerPrtflMapper;
import com.eventi.left.prtfl.service.DgnerPrtflService;
import com.eventi.left.prtfl.service.DgnerPrtflVO;

@Service
public class DgnerPrtflServiceImpl implements DgnerPrtflService{
	@Autowired DgnerPrtflMapper dgnerPrtflmapper;
	@Autowired FilesMapper filesMapper;
	@Autowired UploadFileMethod newUp;
	
	//디자이너 상세 조회
	@Override
	public DgnerPrtflVO dgnerSelect(DgnerPrtflVO dgnerPrtflVO) {
		dgnerPrtflmapper.inqUpdate(dgnerPrtflVO);
		return dgnerPrtflmapper.dgnerSelect(dgnerPrtflVO);
	}
	
	//디자이너별 디자인 리스트
	@Override
	public List<DesignVO> desginList(DesignVO designVO, PagingVO paging) {
		System.out.println("========" + paging);
		
		paging.setTotalRecord(dgnerPrtflmapper.count(designVO));
		paging.setPageUnit(3);
		designVO.setFirst(paging.getFirst());
		designVO.setLast(paging.getLast());
		return dgnerPrtflmapper.desginList(designVO);
	}
	
	//디자이너 등록 여부
	@Override
	public int checkDgner(DgnerPrtflVO dgnerPrtflVO) {
		return dgnerPrtflmapper.checkDgner(dgnerPrtflVO);
	}
	
	//디자이너 포트폴리오 입력
	@Override
	public int dgnerInsert(DgnerPrtflVO dgnerPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		//대표사진세팅
		dgnerPrtflVO.setImg(uploadFile[0].getOriginalFilename());
		
		int r = dgnerPrtflmapper.dgnerInsert(dgnerPrtflVO);
		List<FileDto> list = new ArrayList<FileDto>();
		
		try {
			list = newUp.uploadFiles(uploadFile, dgnerPrtflVO.getUserId(), "T07");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
}
