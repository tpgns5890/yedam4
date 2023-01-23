package com.eventi.left.promotion.web;

import java.util.ArrayList;
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
import com.eventi.left.common.SessionUtil;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.job.service.JobBoardVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.promotion.service.PromotionService;
import com.eventi.left.promotion.service.PromotionVO;
import com.eventi.left.reply.service.ReplyService;
import com.eventi.left.reply.service.ReplyVO;

@Controller
public class PromotionController {

	@Autowired 
	PromotionService proService;
	//댓글 service
	@Autowired
	ReplyService service;
	//파일 service
	@Autowired FilesService filesService;
	
	//홍보게시물 전체조회
	@RequestMapping(value = "/proList", method=RequestMethod.GET)
	public String proList(Model model, PromotionVO promotionVO, PagingVO paging) {
		//전체리스트 조회
		List<PromotionVO> contents = proService.proList(promotionVO, paging);
		model.addAttribute("paging", paging);
		
		//사진파일
		List<FilesVO> files = new ArrayList<>();
		for(PromotionVO content : contents) {
			files = filesService.fileList(content.getProNo());
			
			//파일리스트 저장된 파일정보가 홍보번호와 같다면 이미지셋팅.
			for(FilesVO file : files) {
				if(file.getTargetId().equals(content.getProNo())) {
					content.setImg(file.getSevNm());
				}
			}
		}
		model.addAttribute("proList", contents);
		//이미지
		model.addAttribute("file", filesService.fileList(promotionVO.getProNo()));
		return "content/promotion/proList";
	}
	
	//정렬 전체 조회
	@PostMapping("/proList")
	@ResponseBody
	public List<PromotionVO> jSelectList(PromotionVO promotionVO, PagingVO paging){
		List<PromotionVO> list = proService.proList(promotionVO, paging);
		return list;
	}
	//게시글상세조회
		@RequestMapping(value = "/proDetail", method=RequestMethod.GET) 
		public String proDetail(Model model, PromotionVO promotionVO, ReplyVO replyVO) {
			//조회수
			proService.seeUp(promotionVO);
			MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
			//상세내용
			model.addAttribute("proDetail", proService.proDetail(promotionVO));
			//이미지
			model.addAttribute("file", filesService.fileList(promotionVO.getProNo()));
			return "content/promotion/proDetail";
		}
		
	//게시글등록폼이동
		@RequestMapping(value = "/proInsert", method=RequestMethod.GET) 
		public String proInsert(Model model) {
			model.addAttribute("nextNo", proService.getSeq());
			return "content/promotion/proInsert";
		}
	
	//게시글 등록
		@PostMapping("/proInsert")
		public String proInsert(PromotionVO promotionVO, FilesVO filesVO, MultipartFile[] uploadFile) {
			proService.proInsert(promotionVO, filesVO, uploadFile); //값이 vo자동으로 저장
			return "redirect:/proList";
		}		
	
	//게시글수정페이지로이동
		@RequestMapping(value = "/proUpdate", method=RequestMethod.GET) 
		public String proUpdate(Model model, PromotionVO promotionVO) {
			model.addAttribute("proUpdate", proService.proDetail(promotionVO));
			//이미지
			model.addAttribute("file", filesService.fileList(promotionVO.getProNo()));
			return "content/promotion/proUpdate";
		}
		
	//게시글 수정
		@PostMapping("/proUpdate")
		public String proUpdate(PromotionVO promotionVO, MultipartFile uploadFile) {
			proService.proUpdate(promotionVO,uploadFile );
			return "redirect:/proList";
		}

	//게시글 삭제
		@GetMapping("/proDelete") //th:onclick = location
		public String proDelete(PromotionVO promotionVO) {
			proService.proDelete(promotionVO);
			return "redirect:/proList"; 
		}
}
