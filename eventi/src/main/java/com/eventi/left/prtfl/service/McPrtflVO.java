package com.eventi.left.prtfl.service;

import java.util.Date;
import java.util.List;

import com.eventi.left.files.service.FilesVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class McPrtflVO {
	private String userId;        //회원ID
	private String userName;      //회원이름
	private String oneIntro;      //한줄소개
	private Integer carrYear;     //경력
	private Integer castingPrice; //섭외비
	private String mcArea;        //사회가능지역
	private String mcType;        //세부분야
	private String progStyle;     //진행스타일
	private String selfIntro;     //자기소개
	private String career;        //경력사항
	private Integer inq;          //조회수
	private Date dt;              //작성시간
	private String img;           //대표이미지
	private String mainimg;       //대표이미지 띄울때 필요
	
	private String mcAreaCode;    //사회가능지역코드
	private String mcTypeCode;    //세부분야 코드
	private String mcProgStyleCode;   //진행스타일 코드
	
	private int userLike;         //로그인된 계정이 좋아요 누른 건
	private int cnt;              //게시글당 좋아요 수
	private String order = "dt";  //정렬
	
	private List<FilesVO> files;  //파일리스트
	
	Integer first = 1;            //첫페이지
	Integer last = 6;             //마지막페이지
}
