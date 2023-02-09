package com.eventi.left.job.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.bboard.service.BboardVO;
import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.common.service.CodeService;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.job.service.JobBoardVO;
import com.eventi.left.job.service.JobService;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.promotion.service.PromotionVO;
import com.eventi.left.resume.service.ResumeBoardVO;
import com.eventi.left.resume.service.ResumeService;

@Controller
public class JobBoardController {

	@Autowired 
	JobService jobService;
	//좋아요 service
	@Autowired LikesService likeService;
	//파일 service
	@Autowired FilesService filesService;
	@Autowired CodeService codeService;
	@Autowired ResumeService resumeService;
	
	//전체조회
	@RequestMapping(value = "/jobList", method=RequestMethod.GET) 
	public String jobList(Model model, JobBoardVO jobBoardVO, PagingVO paging) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		if(user != null) {
			jobBoardVO.setLikes(user.getUserId());
		}
		
		List<JobBoardVO> imgs = jobService.getJobList(jobBoardVO, paging);
		model.addAttribute("paging", paging);
		
		//사진파일
		List<FilesVO> files = new ArrayList<>();
		for(JobBoardVO img : imgs) {
			files = filesService.fileList(img.getJobNo());
			img.setFiles(files);
			
			//파일리스트 저장된 파일정보가 홍보번호와 같다면 이미지셋팅.
			/*
			 * for(FilesVO file : files) { if(file.getTargetId().equals(img.getJobNo())) {
			 * img.setImg(file.getSevNm()); } }
			 */
		}
		model.addAttribute("jobList", imgs);
		//이미지
		//model.addAttribute("file", filesService.fileList(jobBoardVO.getJobNo()));
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
	public String jobUpload(Model model, JobBoardVO jobBoardVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		model.addAttribute("types", codeService.getType());
		return "content/job/jobInsertForm";
	}
	
	//게시글 등록
		@PostMapping("/jobInsert")
		 //form으로 보냄
		public String JobInsertForm(JobBoardVO jobBoardVO, FilesVO filesVO, MultipartFile[] uploadFile) {
			jobService.jobInsert(jobBoardVO, filesVO, uploadFile); //값이 vo자동으로 저장
			return "redirect:/jobList";
			//rttr.addFlashAttribute("result", "게시글 등록 완료!"); //데이터전달
	}
	
	//게시글상세조회로이동
	@RequestMapping(value = "/jobDetail", method=RequestMethod.GET) 
	public String jobDetail(Model model, JobBoardVO jobBoardVO, LikesVO likesVO) {
		//좋아요 눌렀는지 확인
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		likesVO.setUserId(user.getUserId());
		likesVO.setTargetNo(jobBoardVO.getJobNo());
		likesVO.setCategory("T03");
		model.addAttribute("like", likeService.getLike(likesVO));
		//상세내용
		model.addAttribute("job", jobService.getJob(jobBoardVO));
		//이미지
		model.addAttribute("file", filesService.fileList(jobBoardVO.getJobNo()));
		return "content/job/jobDetailBoard";
	}
	
	//임시저장된 게시글 전체들고오기
	@PostMapping("/jSave")
	@ResponseBody
	public List<JobBoardVO> jSave(@RequestBody JobBoardVO jobBoardVO) {
		return jobService.jSave(jobBoardVO);
	}
		
	//임시저장된 게시글 상세들고오기
	@PostMapping("/jSelect")
	@ResponseBody
	public JobBoardVO jSaveSelect(@RequestBody JobBoardVO jobBoardVO) {
		return jobService.getJob(jobBoardVO);
	}
		
	//게시글수정페이지로이동
	@RequestMapping(value = "/jobUpdate", method=RequestMethod.GET) 
	public String jobUpdate(Model model, JobBoardVO jobBoardVO) {
		model.addAttribute("update", jobService.getJob(jobBoardVO));
		
		//사진 및 동영상
		List<FilesVO> files = new ArrayList<>();
		files = filesService.fileList(jobBoardVO.getJobNo());
		model.addAttribute("files", files);
		//이미지
		//model.addAttribute("file", filesService.fileList(jobBoardVO.getJobNo()));
		return "content/job/jobUpdateForm";
	}
	
	//게시글 수정
	@PostMapping("/jobUpdate")
	@ResponseBody //ajax방식
	public int jobUpdate(JobBoardVO jobBoardVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		return jobService.getJobUpdate(jobBoardVO, filesVO, uploadFile);
			//rttr.addFlashAttribute("result", "게시글 수정 완료!");
	}
	
	//게시글 삭제
	@GetMapping("/jobDelete") //th:onclick = location
	public String jobDelete(JobBoardVO jobBoardVO) {
		jobService.jobDelete(jobBoardVO);
		return "redirect:/jobList"; //이게 string 이니까 public 옆에도 string으로 
			
	}
	//마이페이지------------------------------------
	//1.나의 구인게시물관리 페이지이동
	@GetMapping("/myJobList")
	public String myJobList(Model model, JobBoardVO jobBoardVO, @ModelAttribute("paging") PagingVO paging) {
		model.addAttribute("jobList", jobService.myJobList(jobBoardVO, paging));
		return "content/myPage/myJobList";
	}
	//2.내가 구직신청한 게시물 조회
	@GetMapping("/myApplyList")
	public String myApplyList(Model model, JobBoardVO jobBoardVO, @ModelAttribute("paging") PagingVO paging) {
		model.addAttribute("applyList", jobService.myApplyList(jobBoardVO, paging));
		return "content/myPage/myApplyList";
	}
	//2.나의 구인게시글 삭제
	@GetMapping("/myJobDelete")
	@ResponseBody
	public int myJobDelete(JobBoardVO jobBoardVO) {
		int r = jobService.jobDelete(jobBoardVO);
		return r; 
}	
}
