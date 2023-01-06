package com.eventi.left.notice.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeVO {
	private String noticeNo; //공지사항번호
	private String ttl; //공지사항제목
	private String noticeCntn; //공지게시글내용
	private String dt; //제목
	private String img; //이미지
	private String userId; //회원ID
}