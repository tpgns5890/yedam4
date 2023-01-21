package com.eventi.left.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.admin.service.AdminService;
import com.eventi.left.common.PagingVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@GetMapping("/MemberList")
	public String MemberList(Model model, PagingVO paging) {
		return "content/admin/adminMember";
	}
	
}
