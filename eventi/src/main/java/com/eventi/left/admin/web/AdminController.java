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
import com.eventi.left.member.service.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@GetMapping("/memberList")
	public String MemberList(Model model, MemberVO memberVO, PagingVO paging) {
		//상단에 표시할 회원 count 모델에 먼저 저장
		model.addAttribute("countall", service.count(memberVO));
		memberVO.setUserState("Punish");
		model.addAttribute("countpunish", service.count(memberVO));
		memberVO.setUserState("Ban");
		model.addAttribute("countban", service.count(memberVO));
		memberVO.setUserState(null);
		model.addAttribute("mList", service.memberList(memberVO, paging));
		//model.addAttribute("paging", paging);
		return "content/admin/adminMember";
	}
	
	// 공모전 전체리스트(ajax)
		@PostMapping("/memberListAjax")
		@ResponseBody
		public Map<String, Object> memberListAjax(MemberVO vo, PagingVO paging) {
			// 리턴할 최종Map(Member,paging VO)
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("mListAjax", service.memberList(vo, paging));
			result.put("paging", paging);
			return result;
		}
	
}
