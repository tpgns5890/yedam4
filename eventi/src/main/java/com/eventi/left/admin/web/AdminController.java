package com.eventi.left.admin.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.admin.service.AdminService;
import com.eventi.left.common.PagingVO;
import com.eventi.left.member.service.CrtfVO;
import com.eventi.left.member.service.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService service;

	// 관리자페이지 이동
	@RequestMapping(value = "/adminMain")
	public String adminMain() {
		return "content/admin/adminMain";
	}
	
	// 자격증승인페이지 이동
	@RequestMapping(value = "/certList")
	public String certList() {
		return "content/admin/adminCert";
	}

	// 전체회원리스트 이동 및 회원 수 가져오기
	@GetMapping("/memberList")
	public String MemberList(Model model, MemberVO memberVO) {
		// 상단에 표시할 회원 수 모델에 저장
		model.addAttribute("countall", service.count(memberVO));
		memberVO.setUserState("Punish");
		model.addAttribute("countpunish", service.count(memberVO));
		memberVO.setUserState("Ban");
		model.addAttribute("countban", service.count(memberVO));
		memberVO.setUserState(null);
		return "content/admin/adminMember";
	}

	// 전체회원 리스트(ajax)
	@PostMapping("/memberListAjax")
	@ResponseBody
	public Map<String, Object> memberListAjax(MemberVO vo, PagingVO paging) {
		// 리턴할 최종Map(Member,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("mListAjax", service.memberList(vo, paging));
		result.put("paging", paging);
		return result;
	}
	
	@PostMapping("/certListAjax")
	@ResponseBody
	public Map<String, Object> certListAjax(CrtfVO vo, PagingVO paging) {
		// 리턴할 최종Map(Member,paging VO)
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("certList", service.certList(vo, paging));
		result.put("paging", paging);
		return result;
	}

}
