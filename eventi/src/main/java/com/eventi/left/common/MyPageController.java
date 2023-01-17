package com.eventi.left.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/myPage")
public class MyPageController {
	
	// 마이페이지(첫페이지)
		@GetMapping("/test")
		public String contestList() {
			return "content/myPage/firstMyPage";
		}

}
