package com.eventi.left.resume.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResumeBoardVO {
	private String resumeNo; //구직신청게시글번호
	private String seekerId; //구직자ID
	private	String seekerCntn; //구직게시글내용
	private String jobNo; //구인게시글번호(FK)
	private String resumeCntn; //자기소개서
	private String files; //첨부파일
	
	private String userName;
	private String userEmail;
	private String userPhone;
	
	
}
