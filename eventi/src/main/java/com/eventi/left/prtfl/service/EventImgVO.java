package com.eventi.left.prtfl.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventImgVO {
	private String eventNo;       //행사이미지번호
	private String userId;        //업체id
	private String eventTtl;      //제목
	private String eventCaregory; //행사유형
	
	Integer first = 1;            //첫페이지
	Integer last = 3;             //마지막페이지
}
