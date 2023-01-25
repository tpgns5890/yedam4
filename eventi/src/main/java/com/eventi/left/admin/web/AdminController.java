package com.eventi.left.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.admin.service.AdminService;
import com.eventi.left.common.PagingVO;
import com.eventi.left.member.service.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@GetMapping("/MemberList")
	public String MemberList(Model model, MemberVO memberVO, PagingVO paging) {
		System.out.println(service.memberList(memberVO, paging));
		model.addAttribute("mList", service.memberList(memberVO, paging));
		model.addAttribute("paging", paging);
		model.addAttribute("countall", service.count(memberVO));
		memberVO.setUserState("Punish");
		model.addAttribute("countpunish", service.count(memberVO));
		memberVO.setUserState("Ban");
		model.addAttribute("countban", service.count(memberVO));
		return "content/admin/adminMember";
	}
}
