package com.eventi.left.resume.service;

import java.util.List;

import com.eventi.left.files.service.FilesVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResumeBoardVO {
	
	private String resumeNo; //구직신청게시글번호
	private String seekerId; //구직자ID
	private	String title; //제목
	private String jobNo; //구인게시글번호(FK)
	private String cntn; //자기소개서
	private String img; //첨부파일
	
	private String userName; //회원이름
	private String userEmail; //회원이메일
	private String userPhone; //회원전화번호
	private String hire; //채용여부
	private String jobWriter; //채용여부
	
	Integer first = 1; //첫 페이지
	Integer last = 4; //마지막 페이지
	
	//파일리스트
	private List<FilesVO> files; 
}
