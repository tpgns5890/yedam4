package com.eventi.left.prtfl.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.common.service.CodeService;
import com.eventi.left.design.service.DesignVO;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.likes.mapper.LikesMapper;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.prtfl.service.DgnerPrtflService;
import com.eventi.left.prtfl.service.DgnerPrtflVO;
import com.eventi.left.reply.service.ReplyVO;
import com.eventi.left.review.service.ReviewService;
import com.eventi.left.review.service.ReviewVO;

@Controller
@RequestMapping("/prtfl")
public class DgnerPrtflController {
	@Autowired DgnerPrtflService dgnerPrtflservice;
	@Autowired CodeService codeService;
	@Autowired FilesService filesService;
	@Autowired LikesMapper likesMapper;
	@Autowired ReviewService reviewService;
	
	//디자이너 상세조회(수정해야됨)
	@GetMapping("/dgnerSelect")
	public String dgnerList(Model model, DgnerPrtflVO dgnerPrtflVO, ReplyVO replyVO, LikesVO likesVO, ReviewVO reviewVO, PagingVO paging) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		
		model.addAttribute("dgnerSelect", dgnerPrtflservice.dgnerSelect(dgnerPrtflVO));
		System.out.println("=============="+ user.getUserId());
		//좋아요 눌렀는지 확인
		likesVO.setUserId(user.getUserId());
		likesVO.setTargetNo(dgnerPrtflVO.getUserId());
		likesVO.setCategory("T07");
		model.addAttribute("like", likesMapper.getLike(likesVO));
		
		//후기 관련
		reviewVO.setReviewTgt(dgnerPrtflVO.getUserId());
		reviewVO.setCategory("T07");
		model.addAttribute("reviews", reviewService.getReview(reviewVO));
				
		//후기 평균 별점, 전체건수
		model.addAttribute("recount", reviewService.getReviewAvg(reviewVO));
		
		return "content/prtfl/dgnerSelect";
	}
	
	//디자인 리스트
	@PostMapping("/desginList")
	@ResponseBody
	public Map<String, Object> desginList(DesignVO designVO, PagingVO paging){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("designs", dgnerPrtflservice.desginList(designVO, paging));
		result.put("paging", paging);
		return result;
	}
	
	//디자이너 입력폼으로 이동
	@GetMapping("/dgnerInsert")
	public String dgnerInsert(Model model, DgnerPrtflVO dgnerPrtflVO) {
		model.addAttribute("designStyle", codeService.designStyle());
		
		return "content/prtfl/dgnerInsert";
	}
	
	//디자이너 입력
	@PostMapping("/dgnerInsert")
	public String dgnerInsertFrm(DgnerPrtflVO dgnerPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		dgnerPrtflservice.dgnerInsert(dgnerPrtflVO, filesVO, uploadFile);
		return "redirect:/prtfl/dgnerSelect?userId=" + dgnerPrtflVO.getUserId();
	}
}
