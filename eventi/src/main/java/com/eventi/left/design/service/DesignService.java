package com.eventi.left.design.service;

import java.util.List;

import com.eventi.left.common.PagingVO;

public interface DesignService {
	public List<DesignVO> designList(DesignVO vo, PagingVO paging);
	
	public List<DesignVO> contestDesignList(String cNo); //공모전 1건에 대한 리스트.
	
	public int insert(DesignVO vo); //추가
	 
	public int update(DesignVO vo); //수정
	
	public int delete(String dNo); //삭제
	
	public String getSequence(); //시퀀스 맥시멈번호 찾기.
	
}	
