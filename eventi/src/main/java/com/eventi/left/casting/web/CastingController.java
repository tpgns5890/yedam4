package com.eventi.left.casting.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.casting.service.CastingService;
import com.eventi.left.casting.service.CastingVO;

@Controller
@RequestMapping("/casting")
public class CastingController {
	@Autowired CastingService castingService;
	
	//사회자별 전체 조회
	@GetMapping("/mcCastingList")
	public String castingList(Model model, CastingVO castingVO) {
		model.addAttribute("casting", castingService.mcCastingList(castingVO));
		System.out.println(castingService.mcCastingList(castingVO));
		return "content/casting/castingList";
	}
	
	//의뢰자별 전체 조회
	@GetMapping("/clientCastingList")
	public String clientCastingList(Model model, CastingVO castingVO) {
		model.addAttribute("casting", castingService.clientCastingList(castingVO));
		return "content/casting/castingList";
	}
	
	//섭외 요청 추가
	@PostMapping("/castingInsert")
	@ResponseBody
	public int castingInsert(CastingVO castingVO) {
		return castingService.castingInsert(castingVO);
	}
	
	//진행현황업데이트
	@PostMapping("/progUpdate")
	@ResponseBody
	public int progUpdate(@RequestBody CastingVO castingVO) {
		return castingService.progressUpdate(castingVO);
	}
}
