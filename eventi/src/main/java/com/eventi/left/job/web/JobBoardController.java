package com.eventi.left.job.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.job.mapper.JobBoardMapper;

public class JobBoardController {

	@Autowired 
	JobBoardMapper jobMapper;
	
	@RequestMapping(value = "/jobList", method=RequestMethod.GET)
	public String jobList(Model model) {
		model.addAttribute("jobList", jobMapper.getJobList(null));
		return "content/job/jobList";
	}
}
