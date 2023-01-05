package com.eventi.left.likes.service;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class LikesVO { 
	String likeNo;
	String userId;
	String targetNo;
	String category;
}