package com.eventi.left.design.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.files.service.FilesVO;

public interface DesignService {
	public List<DesignVO> designList(DesignVO vo, PagingVO paging);// 디자인 전체리스트.

	public List<DesignVO> contestDesignList(DesignVO vo, PagingVO paging); // 공모전 1건에 대한 리스트.

	public List<DesignVO> userDesignList(String userId); // 회원의 대한 디자인리스트.

	public DesignVO getDesign(String dgnNo); // 디자인1건.

	public int insert(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile); // 추가

	public int update(DesignVO vo, FilesVO filesVO, MultipartFile[] uploadFile); // 수정

	public int delete(DesignVO vo); // 삭제

	public String getSequence(); // 시퀀스 맥시멈번호 찾기.

}
