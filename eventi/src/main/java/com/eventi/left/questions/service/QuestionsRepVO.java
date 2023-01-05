package com.eventi.left.questions.service;



import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class QuestionsRepVO { 
	String qNo;
	String userId;
	String targetId;
	String qTtl;
	String qCntn;
	Date writingDt;
	String replyYn;
	String secretYn;
	String category;
	
}