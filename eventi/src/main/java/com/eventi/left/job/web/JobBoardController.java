package com.eventi.left.job.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.job.service.JobBoardVO;
import com.eventi.left.job.service.JobService;

@Controller
public class JobBoardController {

	@Autowired 
	JobService jobService;
	
	//전체조회
	@RequestMapping(value = "/jobList", method=RequestMethod.GET) 
	public String jobList(Model model, JobBoardVO jobBoardVO, PagingVO paging) {
		model.addAttribute("jobList", jobService.getJobList(jobBoardVO, paging ));
		model.addAttribute("paging", paging);
		System.out.println(paging);
		return "content/job/jobList";
	}
	
	//정렬 전체 조회
	@PostMapping("/jobList")
	@ResponseBody
	public List<JobBoardVO> jSelectList(JobBoardVO jobBoardVO, PagingVO paging){
		List<JobBoardVO> list = jobService.getJobList(jobBoardVO, paging);
		return list;
	}
	
	//구인등록폼이동
	@RequestMapping(value = "/jobInsert", method=RequestMethod.GET) 
	public String jobUpload() {
		return "content/job/jobInsertForm";
	}
	
	//게시글 등록
		@PostMapping("/jobInsert")
		 //form으로 보냄
		public String JobInsertForm(JobBoardVO jobBoardVO, MultipartFile uploadFile) {
			jobService.jobInsert(jobBoardVO, uploadFile ); //값이 vo자동으로 저장
			return "redirect:/jobList";
			//rttr.addFlashAttribute("result", "게시글 등록 완료!"); //데이터전달
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
		model.addAttribute("update", jobService.getJob(jobBoardVO));
		return "content/job/jobUpdateForm";
	}
	
	//게시글 수정
		@PostMapping("/jobUpdate")
		@ResponseBody //ajax방식
		public int jobUpdate(JobBoardVO jobBoardVO) {
			return jobService.getJobUpdate(jobBoardVO);
			//rttr.addFlashAttribute("result", "게시글 수정 완료!");
		}
	
	//게시글 삭제
		@GetMapping("/jobDelete") //th:onclick = location
		public String  jobDelete(JobBoardVO jobBoardVO) {
			jobService.jobDelete(jobBoardVO);
			return "redirect:/jobList"; //이게 string 이니까 public 옆에도 string으로 
			
		}
	//정렬 조회
	/*
	 * @PostMapping("/jobList")
	 * 
	 * @ResponseBody public List<JobBoardVO> bSelectList(Model model, JobBoardVO
	 * jobBoardVO) { model.addAttribute("type", jobBoardVO.getType()); //정렬 조건이 포함된
	 * 전체리스트 조회 후 list에 담아서 보내기 List<JobBoardVO> list =
	 * jobService.getJobList(jobBoardVO); return list; }
	 */
}
