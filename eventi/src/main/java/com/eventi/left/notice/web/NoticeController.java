package com.eventi.left.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.notice.mapper.NoticeMapper;

@Controller
public class NoticeController {

	@Autowired 
	NoticeMapper noticeMapper;
	
	@RequestMapping(value = "/noticeList", method=RequestMethod.GET)
	public String noticeList(Model model) {
		model.addAttribute("noticeList", noticeMapper.getNoticeList(null));
		return "content/notice/noticeList";
	}
}


