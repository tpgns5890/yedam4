package com.eventi.left.files.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class FilesVO { 
	String fNo;			//첨부파일번호
	String targetId;	//게시글번호 or 회원Id
	String fNm;			//실제파일명
	String sevNm;		//서버내파일명(중복일경우 rename)
	String saveAddr;	//저장경로
	String category;	//대상구분
}