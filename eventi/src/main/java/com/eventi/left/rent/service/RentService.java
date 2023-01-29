package com.eventi.left.rent.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.service.FilesVO;

public interface RentService {
	//대여물품
	public List<RentGdVO> getRentGdList(RentGdVO rentGdVO, PagingVO paging); // 전체조회
	public RentGdVO getRentGd(RentGdVO rentGdVO); // 1건조회
	
	//물품등록
	public int rentGdInsert(RentGdVO rentGdVO, FilesVO filesVO, MultipartFile[] uploadFile);
	
	//다음 물품 번호 찾기
	public String getSeq();
}
