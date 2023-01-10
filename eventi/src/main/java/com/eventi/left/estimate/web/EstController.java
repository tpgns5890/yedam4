package com.eventi.left.estimate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eventi.left.estimate.mapper.EstMapper;

@Controller
public class EstController {
	
	@Autowired
	EstMapper estMapper;
	
	//견적요청서 전체조회
	@RequestMapping(value = "/estList")
	public String estList(Model model) {
		model.addAttribute("estList", estMapper.getEstList(null));
		return "content/estimate/estList";
	}
	//견적요청서 행사유형 선택페이지
	@RequestMapping(value = "/estFormType")
	public String estFormType(Model model) {
		return "content/estimate/estFormType";
	}
	//견적요청서 행사규모 선택페이지
	@RequestMapping(value = "/estFormPats")
	public String estFormPats(Model model) {
		return "content/estimate/estFormPats";
	}
	//견적요청서 행사예정장소 선택페이지
	@RequestMapping(value = "/estFormPlace")
	public String estFormPlace(Model model) {
		return "content/estimate/estFormPlace";
	}
	//견적요청서 행사날짜 선택페이지
	@RequestMapping(value = "/estFormDate")
	public String estFormDate(Model model) {
		return "content/estimate/estFormDate";
	}
	//견적요청서 행사시작시간 선택페이지
	@RequestMapping(value = "/estFormTime")
	public String estFormTime(Model model) {
		return "content/estimate/estFormTime";
	}
	//견적요청서 행사소요시간 선택페이지
	@RequestMapping(value = "/estFormDuration")
	public String estFormDuration(Model model) {
		return "content/estimate/estFormDuration";
	}
	//견적요청서 행사예정지역 선택페이지
	@RequestMapping(value = "/estFormLocal")
	public String estFormLocal(Model model) {
		return "content/estimate/estFormLocal";
	}
	//견적요청서 행사희망사항 선택페이지
	@RequestMapping(value = "/estFormWishes")
	public String estFormWishes(Model model) {
		return "content/estimate/estFormWishes";
	}
	//견적요청서 작성완료 선택페이지
	@RequestMapping(value = "/estFormDone")
	public String estFormDone(Model model) {
		return "content/estimate/estFormDone";
	}
	//견적서 상세페이지
	@RequestMapping(value = "/estDetail")
	public String estDetail(Model model, @RequestParam String eno, @RequestParam String userId) {
		model.addAttribute("est", estMapper.getEst(eno));
		model.addAttribute("propList", estMapper.getPropList(eno));
		model.addAttribute("count", estMapper.getCount(eno, userId));
		return "content/estimate/estDetail";
	}
//	//업체 제안서 채택/후기 회수 조회
//	@RequestMapping(value = "/getCount")
//	public String getCount(Model model, @RequestParam String eno, @RequestParam String userId) {
//		model.addAttribute("count", estMapper.getCount(eno, userId));
//		return "content/estimate/estDetail";
//	}
}
