package com.eventi.left.questions.service;



import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
public class QuestionsVO { 
	String rNo;
	String qNo;
	String userId;
	Date writingDt;
	String rCntn;
	
}