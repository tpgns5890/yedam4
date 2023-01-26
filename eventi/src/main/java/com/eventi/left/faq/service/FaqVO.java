package com.eventi.left.faq.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FaqVO {
	private String faqNo; //번호
	private String faqAns; //답변
	private String faqTtl; //제목
	
}
