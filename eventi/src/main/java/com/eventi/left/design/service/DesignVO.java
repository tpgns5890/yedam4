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
	private String userLike; //회원 좋아요
	
	private String sevNm;		//서버내파일명
	
	public String getcNo() {
		return cNo;
	}
	public void setcNo(String cNo) {
		this.cNo = cNo;
	}
	Integer first = 1;            //첫페이지
	Integer last = 3;             //마지막페이지
	
	private List<FilesVO> files;
	
	
	
	
	
	
	
	
	//수정(지은)
	private Integer cnt;    //디자인별 좋아요 개수
	private String loginId; //로그인된 아이디
	private int mylike;     //좋아요 구분
	private String mainimg; //디자이너메인이미지
	private String order = "dt";   //정렬
}
