package com.eventi.left.prtfl.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class McMoveVO {
	private String moveNo;  //동영상번호
	private String userId;  //동영상올린회원ID
	private String moveTtl; //동영상제목
	
	private String move;    //동영상
	
	Integer first = 1;            //첫페이지
	Integer last = 4;
}
