package com.eventi.left.design.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.files.service.FilesVO;

public interface DesignService {
	public List<DesignVO> designList(DesignVO vo, PagingVO paging);// 디자인 전체리스트.

	public List<DesignVO> contestDesignList(DesignVO vo, PagingVO paging); // 공모전 1건에 대한 리스트.

	public List<DesignVO> userDesignList(DesignVO vo,PagingVO paging); // 회원의 대한 디자인리스트.

	public DesignVO getDesign(DesignVO vo); // 디자인1건.

	public int insert(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile); // 추가

	public int update(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile); // 수정

	public int delete(String dgnNo); // 삭제

	public String getSequence(); // 시퀀스 맥시멈번호 찾기.
	
	public List<DesignVO> saveGetDesign(DesignVO DesignVO); // 임시저장 1건,디자인파일 여러건조회

	public List<DesignVO> dSave(DesignVO DesignVO); //임시저장 리스트.
	
	public int saveUpdateDesign(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile) ;//임시저장 수정
	
}
