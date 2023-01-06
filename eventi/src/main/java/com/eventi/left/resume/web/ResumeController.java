package com.eventi.left.resume.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.resume.mapper.ResumeBoardMapper;

@Controller
public class ResumeController {

	@Autowired 
	ResumeBoardMapper resumeMapper;
	
	@RequestMapping(value = "/resumeList", method=RequestMethod.GET)
	public String noticeList(Model model) {
		model.addAttribute("resumeList", resumeMapper.getResumeList(null));
		return "content/resume/resumeList";
	}
}
