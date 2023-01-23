package com.eventi.left.likes.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesVO {
	private String likeNo; // 좋아요번호
	private String userId; // 작성자Id
	private String targetNo; // 대상번호
	private String category; // 대상구분

	// 파일이미지
	private String img; 

	// 페이징 처리관련
	private int first;
	private int last;
	private String order;
}