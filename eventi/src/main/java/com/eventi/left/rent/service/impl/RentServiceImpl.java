package com.eventi.left.rent.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.FileDto;
import com.eventi.left.files.UploadFileMethod;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.rent.mapper.RentMapper;
import com.eventi.left.rent.service.RentGdVO;
import com.eventi.left.rent.service.RentService;

@Service
public class RentServiceImpl implements RentService{

	@Autowired
	RentMapper rentGdMapper;
	@Autowired UploadFileMethod newUp;
	
	@Override
	public List<RentGdVO> getRentGdList(RentGdVO rentGdVO, PagingVO paging) { //대여물품 전체조회
		paging.setTotalRecord(rentGdMapper.count(rentGdVO));
		paging.setPageUnit(5);
		rentGdVO.setFirst(paging.getFirst());
		rentGdVO.setLast(paging.getLast());
		
		return rentGdMapper.getRentGdList(rentGdVO);
	}

	@Override
	public RentGdVO getRentGd(RentGdVO rentGdVO) { //대여물품 한건조회
		return rentGdMapper.getRentGd(rentGdVO);
	}
	
	//물품등록
	@Override
	public int rentGdInsert(RentGdVO rentGdVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		int r = rentGdMapper.rentGdInsert(rentGdVO);
		 List<FileDto> list= new ArrayList<FileDto>();
	      //파일 업로드하는 기능 부르기+데베에 저장하기/첨부파일 테이블에 저장할 때 쓰임
	      try {
			list = newUp.uploadFiles(uploadFile, rentGdVO.getGoodsNo(), "T10");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	//다음 물품 시퀀스 번호
	@Override
	public String getSeq() {
		return rentGdMapper.getSeq();
	}

}
