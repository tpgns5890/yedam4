package com.eventi.left.design.service;

import java.util.Date;
import java.util.List;

import com.eventi.left.files.service.FilesVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesignVO {
	private String dgnNo; //디자인번호
	private String userId; //회원ID
	private String cNo; //공모전번호
	private String name; //디자인이름
	private String caregory; //디자인카테고리
	private String cntn; // 디자인소개
	private String winYn; //우승여부
	private Date dt; //등록시간
	private String centerImg; //대표이미지
	private String pubcYn = "N"; //공개여부
	private String save = "N";
	
	private String contestTtl; //공모전제목
	
	private String sevNm;		//서버내파일명
	
	public String getcNo() {
		return cNo;
	}
	public void setcNo(String cNo) {
		this.cNo = cNo;
	}
	private int first;
	private int last;
	
	private List<FilesVO> files;
	
	
}
