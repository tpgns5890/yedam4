package com.eventi.left.prtfl.web;

import java.util.ArrayList;
import java.util.List;

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
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.likes.mapper.LikesMapper;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.prtfl.service.McPrtflService;
import com.eventi.left.prtfl.service.McPrtflVO;
import com.eventi.left.reply.service.ReplyVO;
import com.eventi.left.review.service.ReviewService;
import com.eventi.left.review.service.ReviewVO;

@Controller
@RequestMapping("/prtfl")
public class McPrtlController {
	@Autowired McPrtflService mcPrtflService;
	@Autowired CodeService codeService;
	@Autowired FilesService filesService;
	@Autowired LikesMapper likesMapper;
	@Autowired ReviewService reviewService;
	
	//사회자 전체 조회
	@GetMapping("/mcList")
	public String mcList(Model model, McPrtflVO mcPrtflVO, PagingVO paging) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		
		List<McPrtflVO> mcList = mcPrtflService.mcAll(mcPrtflVO, paging);
		model.addAttribute("paging", paging);
		
		//사진 및 동영상
		List<FilesVO> files = new ArrayList<>();
		for(McPrtflVO mc : mcList) {
			files = filesService.fileList(mc.getUserId());
			mc.setFiles(files);
		}
		
		//좋아요
		for(McPrtflVO mc : mcList) {
			if(likesMapper.userlikecheck(mc.getUserId(), "T06", user.getUserId()) == 1) {
				mc.setUserLike(1);
			}
		}
		model.addAttribute("mcList", mcList);
		return "content/prtfl/mcList";
	}
	
	//사회자 단건 조회
	@GetMapping("/mcSelect")
	public String mcSelect(Model model, McPrtflVO mcPrtflVO, FilesVO filesVO, ReplyVO replyVO, LikesVO likesVO, ReviewVO reviewVO) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		
		model.addAttribute("mcSelect", mcPrtflService.mcSelect(mcPrtflVO));
		//사진가져오기
		filesVO.setTargetId(mcPrtflVO.getUserId());
		model.addAttribute("file", filesService.getfile(filesVO));
		
		//좋아요 눌렀는지 확인
		likesVO.setUserId(user.getUserId());
		likesVO.setTargetNo(mcPrtflVO.getUserId());
		likesVO.setCategory("T06");
		model.addAttribute("like", likesMapper.getLike(likesVO));
		
		//후기 관련
		reviewVO.setReviewTgt(mcPrtflVO.getUserId());
		reviewVO.setCategory("T06");
		model.addAttribute("reviews", reviewService.getReview(reviewVO));
				
		//후기 평균 별점, 전체건수
		model.addAttribute("recount", reviewService.getReviewAvg(reviewVO));
		
		//의뢰폼 행사 유형으로 사용
		model.addAttribute("types", codeService.getType());
		
		return "content/prtfl/mcSelect";
	}
	
	//해당 게시글의 댓글 및 대댓글 조회
	@PostMapping("/mcReply")
	@ResponseBody
	public List<ReplyVO> mcReply(@RequestBody ReplyVO vo) {
		List<ReplyVO> reply = mcPrtflService.mcReply(vo);
		return reply;
	}
	
	//사회자입력폼으로 이동
	@GetMapping("/mcInsert")
	public String mcInsert(Model model, McPrtflVO mcPrtlVo) {
		//option 코드 가져오기
		model.addAttribute("areas", codeService.getCountry());
		model.addAttribute("types", codeService.getType());
		model.addAttribute("mcStyles", codeService.getMcStyle());
		
		return "content/prtfl/mcInsert";
	}
	
	//사회자 입력
	@PostMapping("/mcInsert")
	public String mcInsertFrm(McPrtflVO mcPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		mcPrtflService.mcInsert(mcPrtflVO, filesVO, uploadFile);
		return "redirect:/prtfl/mcSelect?userId=" + mcPrtflVO.getUserId();
	}
	
	//사회자수정폼으로 이동
	@GetMapping("/mcUpdate")
	public String mcUpdate(Model model, McPrtflVO mcPrtflVo, FilesVO filesVO) {
		//option 코드 가져오기
		model.addAttribute("areas", codeService.getCountry());
		model.addAttribute("types", codeService.getType());
		model.addAttribute("mcStyles", codeService.getMcStyle());
		
		//사회자 정보 가져오기
		model.addAttribute("mcSelect", mcPrtflService.mcSelect(mcPrtflVo));
		//사진가져오기
		filesVO.setTargetId(mcPrtflVo.getUserId());
		model.addAttribute("file", filesService.getfile(filesVO));		
		
		return "content/prtfl/mcUpdate";
	}
	
	//사회자 정보 수정
	@PostMapping("/mcUpdate")
	public String mcUpdateFrm(McPrtflVO mcPrtflVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		mcPrtflService.mcUpdate(mcPrtflVO, filesVO, uploadFile);
		return "redirect:/prtfl/mcSelect?userId=" + mcPrtflVO.getUserId();
	}
	
}
