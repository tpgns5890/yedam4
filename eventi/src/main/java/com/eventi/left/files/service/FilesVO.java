package com.eventi.left.files.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class FilesVO { 

	private String fNo;			//첨부파일번호
	private String targetId;	//게시글번호 or 회원Id
	private String fNm;			//실제파일명
	private String sevNm;		//서버내파일명
	private String saveAddr;	//저장경로
	private String category;	//대상구분
	
	public String getfNo() {
		return fNo;
	}
	public void setfNo(String fNo) {
		this.fNo = fNo;
	}
	public String getfNm() {
		return fNm;
	}
	public void setfNm(String fNm) {
		this.fNm = fNm;
	}
	
	
}