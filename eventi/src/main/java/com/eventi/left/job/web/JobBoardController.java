package com.eventi.left.job.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eventi.left.job.service.JobBoardVO;
import com.eventi.left.job.service.JobService;

@Controller
public class JobBoardController {

	@Autowired 
	JobService jobService;
	
	@RequestMapping(value = "/jobList", method=RequestMethod.GET) //전체조회
	public String jobList(Model model) {
		model.addAttribute("jobList", jobService.getJobList(null));
		return "content/job/jobList";
	}
	
	@RequestMapping(value = "/jobUpload", method=RequestMethod.GET) //구인등록폼이동
	public String jobUpload() {
		return "content/job/jobUploadForm";
	}
	
	@RequestMapping(value = "/jobViewAll", method=RequestMethod.GET) //신청자조회페이지이동
	public String jobViewAll() {
		return "content/job/jobViewAll";
	}

	@RequestMapping(value = "/jobDetail", method=RequestMethod.GET) //게시글상세조회로이동
	public String jobDetail(Model model, JobBoardVO jobBoardVO) {
		System.out.println(jobBoardVO.getJobNo());
		model.addAttribute("jobDetail", jobService.getJob(jobBoardVO));
		return "content/job/jobDetailBoard";
	}
}
