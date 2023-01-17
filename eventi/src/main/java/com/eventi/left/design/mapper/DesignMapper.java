package com.eventi.left.design.mapper;

import java.util.List;

import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.design.service.DesignVO;

public interface DesignMapper {
	
	//디자인 전체 조회
	public List<DesignVO> designList(DesignVO vo);
	
	//등록된 디자인 개수
	public int count(DesignVO vo);

	public List<DesignVO> contestDesignList(String cNo); //공모전 1건에 대한 리스트.
	
	public int insert(DesignVO vo); //추가
	 
	public int update(DesignVO vo); //수정
	
	public int delete(String dNo); //삭제
	
	public String getSequence(); //시퀀스 맥시멈번호 찾기.

}
