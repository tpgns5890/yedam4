package com.eventi.left.casting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.casting.service.CastingService;
import com.eventi.left.casting.service.CastingVO;

@Controller
@RequestMapping("/casting")
public class CastingController {
	@Autowired CastingService castingService;
	
	//섭외 전체 조회
	@RequestMapping("/castingList")
	public String castingList(Model model, CastingVO castingVO) {
		model.addAttribute("casting", castingService.castingList(castingVO));
		return "content/casting/castingList";
	}
	
	//섭외 요청 추가
	@PostMapping("/castingInsert")
	@ResponseBody
	public int castingInsert(CastingVO castingVO) {
		return castingService.castingInsert(castingVO);
	}
}
