package com.eventi.left.design.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.common.PagingVO;
import com.eventi.left.design.service.DesignService;
import com.eventi.left.design.service.DesignVO;

@Controller
@RequestMapping("/design")
public class DesignController {
	@Autowired
	DesignService service;

	//디자인 리스트 조회
	@RequestMapping("/designList")
	public String bfList(Model model, DesignVO vo, PagingVO paging) {
		model.addAttribute("designList", service.designList(vo, paging));
		System.out.println(paging);
		return "content/design/designList";
	}
	
	// 디자인 응모 처리(마이페이지 응모리스트 페이지.)
	@PostMapping("/designInsert")
	public String designInsert(DesignVO design) {
		service.insert(design);
		return "content/myPage/myDesignList"; 
	}
	//디자인 수정하기
	
	//디자인 삭제하기
}
