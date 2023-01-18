package com.eventi.left.design.mapper;

import java.util.List;

import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.design.service.DesignVO;

public interface DesignMapper {
	
	//디자인 전체 조회
	public List<DesignVO> designList(DesignVO vo);
	
	//등록된 디자인 개수
	public int count(DesignVO vo);

	public List<DesignVO> contestDesignList(String cNo); //게시글 1건 디자인리스트.
	
	public List<DesignVO> userDesignList(String userId); //유저 디자인리스트.
	
	public DesignVO getDesign(String dgnNo); // 디자인1건.
	
	public int insert(DesignVO vo); //추가
	 
	public int update(DesignVO vo); //수정
	
	public int delete(DesignVO vo); //삭제
	
	public String getSequence(); //시퀀스 맥시멈번호 찾기.

}
