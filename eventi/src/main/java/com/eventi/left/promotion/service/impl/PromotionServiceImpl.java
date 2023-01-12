package com.eventi.left.promotion.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.promotion.mapper.PromotionBoardMapper;
import com.eventi.left.promotion.service.PromotionService;
import com.eventi.left.promotion.service.PromotionVO;

@Service
public class PromotionServiceImpl implements PromotionService{
	
	@Autowired 
	PromotionBoardMapper proMapper;

	//홍보게시글 전체조회
	@Override
	public List<PromotionVO> proList(PromotionVO promotionVO) {
		// TODO Auto-generated method stub
		return proMapper.proList(promotionVO);
	}
	
	//게시글 상세조회
	@Override
	public PromotionVO proDetail(PromotionVO promotionVO) {
		// TODO Auto-generated method stub
		return proMapper.proDetail(promotionVO);
	}
	

	//게시글 등록
	@Override
	public int proInsert(PromotionVO promotionVO, MultipartFile uploadFile) {
		
		//사진등록
		String realFolder = "/files/pro";
		File dir = new File(realFolder);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
				
		//파일 이름 저장
		String img = uploadFile.getOriginalFilename();
				
		//VO에 IMG 부분에 파일 이름 저장
		promotionVO.setImg(img);
				
		return proMapper.proInsert(promotionVO);
	}

}
