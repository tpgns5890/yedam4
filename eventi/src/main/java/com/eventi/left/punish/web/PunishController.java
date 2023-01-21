package com.eventi.left.punish.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.punish.service.PunishService;
import com.eventi.left.punish.service.PunishVO;

@Controller
public class PunishController {

	@Autowired PunishService punishService;
	
	//신고 추가
	@PostMapping("/punishInsert")
	@ResponseBody
	public int punishInsert(PunishVO punishVO) {
		return punishService.punishInsert(punishVO);
	}
}
