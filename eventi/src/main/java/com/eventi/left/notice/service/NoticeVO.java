package com.eventi.left.notice.service;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeVO {
	private String noticeNo; //공지사항번호
	private String ttl; //공지사항제목
	private String noticeCntn; //공지게시글내용
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private String dt; //작성날짜
	private String img; //이미지
	private String userId; //회원ID
	private Integer see;
}