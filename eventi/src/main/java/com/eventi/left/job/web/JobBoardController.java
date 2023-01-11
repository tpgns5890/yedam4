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
	
	//전체조회
	@RequestMapping(value = "/jobList", method=RequestMethod.GET) 
	public String jobList(Model model) {
		model.addAttribute("jobList", jobService.getJobList(null));
		return "content/job/jobList";
	}
	
	//구인등록폼이동
	@RequestMapping(value = "/jobUpload", method=RequestMethod.GET) 
	public String jobUpload() {
		return "content/job/jobUploadForm";
	}
	
	//신청자조회페이지이동(메인)
	@RequestMapping(value = "/getSeekerAll", method=RequestMethod.GET) 
	public String getSeekerAll(Model model, JobBoardVO jobBoardVO) {
		model.addAttribute("seekerAll", jobService.getSeekerAll(jobBoardVO));
		return "content/job/jobSeekerAll";
	}
	
	//게시글상세조회로이동
	@RequestMapping(value = "/jobDetail", method=RequestMethod.GET) 
	public String jobDetail(Model model, JobBoardVO jobBoardVO) {
		model.addAttribute("job", jobService.getJob(jobBoardVO));
		return "content/job/jobDetailBoard";
	}
	
	//게시글수정페이지로이동
	@RequestMapping(value = "/jobUpdate", method=RequestMethod.GET) 
	public String jobUpdate(Model model, JobBoardVO jobBoardVO) {
		model.addAttribute("update", jobService.getJobUpdate(jobBoardVO));
		return "content/job/jobUpdateForm";
	}
}
