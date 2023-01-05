package com.eventi.left.likes.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class LikesVO { 
	String likeNo;		//좋아요번호
	String userId;		//작성자Id
	String targetNo;	//대상번호
	String category;	//대상구분
}