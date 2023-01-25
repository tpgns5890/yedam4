package com.eventi.left.prtfl.service;

import java.util.Date;
import java.util.List;

import com.eventi.left.files.service.FilesVO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class McPrtflVO {
	private String userId; //회원ID
	private String oneIntro; //한줄소개
	private Integer carrYear; //경력
	private Integer castingPrice; //섭외비
	private String mcArea; //사회가능지역
	private String mcType; //세부분야
	private String progStyle; //진행스타일
	private String selfIntro; //자기소개
	private String career; //경력사항
	private Integer inq; //조회수
	private Date dt; //작성시간
	private String img; //대표이미지
	
	private int userLike; //로그인된 계정이 좋아요 누른 건
	private int cnt; //게시글당 좋아요 수
	private String order = "dt";
	
	private List<FilesVO> files;
	
	Integer first = 1; //첫페이지
	Integer last = 6; //마지막페이지
}
