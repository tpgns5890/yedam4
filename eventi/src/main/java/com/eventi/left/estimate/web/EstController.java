package com.eventi.left.estimate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.estimate.service.EstService;
import com.eventi.left.estimate.service.EstVO;
import com.eventi.left.member.service.MemberVO;

@Controller
public class EstController {
	
	@Autowired
	EstService estService;
	
	//견적요청서 전체조회
	@RequestMapping(value = "/estList")
	public String estList(Model model) {
		model.addAttribute("estList", estService.getEstList(null));
		return "content/estimate/estList";
	}
	//견적요청서 작성페이지
	@RequestMapping(value = "/estForm")
	public String estFormType(Model model) {
		return "content/estimate/estForm";
	}
	//견적서 상세페이지
	@RequestMapping(value = "/estDetail")
	public String estDetail(Model model, @RequestParam String eno, @RequestParam String userId) {
		model.addAttribute("est", estService.getEst(eno));
		model.addAttribute("propList", estService.getPropList(eno));
		model.addAttribute("count", estService.getCount(eno, userId));
		return "content/estimate/estDetail";
	}
	
	//견적서 등록
	@PostMapping("/insertEst")
	@ResponseBody
	public String insertEst(Model model, @RequestBody EstVO estVO) {
		estService.insertEst(estVO);
		return estVO.getEno();
		}
	
	//견적요청서 작성완료페이지
		@RequestMapping(value = "/estFormDone")
		public String estFormDone(Model model, String eno) {
			model.addAttribute("est", estService.getEst(eno));
			return "content/estimate/estFormDone";
		}
}
