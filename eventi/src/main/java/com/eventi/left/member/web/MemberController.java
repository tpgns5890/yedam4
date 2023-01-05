package com.eventi.left.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@RequestMapping(value = "/qualification")
	public String index() {
		return "content/member/qualification";
	}

}
