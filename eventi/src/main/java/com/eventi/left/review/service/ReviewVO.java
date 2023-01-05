package com.eventi.left.review.service;


import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class ReviewVO { 
	String reviewNo;
	String uesrId;
	String reviewTgt;
	Integer star;
	String reviewCntn;
	Date writingDt;
	String img;
	String category;
}