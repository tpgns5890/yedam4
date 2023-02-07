package com.eventi.left.promotion.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.common.service.CodeService;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.promotion.service.PromotionService;
import com.eventi.left.promotion.service.PromotionVO;
import com.eventi.left.reply.service.ReplyService;
import com.eventi.left.reply.service.ReplyVO;

@Controller
public class PromotionController {

	@Autowired 
	PromotionService proService;
	@Autowired ReplyService service;
	@Autowired FilesService filesService;
	@Autowired CodeService codeService;
	
	//홍보게시물 전체조회
	@RequestMapping(value = "/proList", method=RequestMethod.GET)
	public String proList(Model model, PromotionVO promotionVO, PagingVO paging) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		
		//paging.setPageSize(4);
		//promotionVO.setOrderCol("see");
		
		
		//전체리스트 조회
		List<PromotionVO> contents = proService.proList(promotionVO, paging);
		model.addAttribute("paging", paging);
		
		//사진파일
		List<FilesVO> files = new ArrayList<>();
		for(PromotionVO content : contents) {
			files = filesService.fileList(content.getUserId());
			content.setFiles(files);
			
			//파일리스트 저장된 파일정보가 홍보번호와 같다면 이미지셋팅.
			/*
			 * for(FilesVO file : files) {
			 * if(file.getTargetId().equals(content.getUserId())) {
			 * content.setImg(file.getSevNm()); } }
			 */
		}
		model.addAttribute("proList", contents);
		
		promotionVO.setOrderCol("see");
		
		List<PromotionVO> posts = proService.proList(promotionVO, paging);
		 model.addAttribute("contents", posts);
		//이미지
		//model.addAttribute("file", filesService.fileList(promotionVO.getProNo()));
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
	public String proDetail(Model model, PromotionVO promotionVO, ReplyVO replyVO, PagingVO paging) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		
		promotionVO.setLast(4);
		promotionVO.setFirst(1);
		promotionVO.setOrderCol("see");
		
		/*
		 * List<PromotionVO> contents = proService.proList(promotionVO, paging);
		 * model.addAttribute("contents", contents);
		 */
		
		//조회수
		proService.seeUp(promotionVO);
		
		//상세내용
		model.addAttribute("proDetail", proService.proDetail(promotionVO));
		
		//사진 및 동영상
		List<FilesVO> files = new ArrayList<>();
		files = filesService.fileList(promotionVO.getUserId());
		model.addAttribute("files", files);
		/* model.addAttribute("file", filesService.fileList(promotionVO.getProNo())); */
		return "content/promotion/proDetail";
	}
		
	//게시글등록폼이동 
		@RequestMapping(value = "/proInsert", method=RequestMethod.GET) 
		public String proInsert(Model model, PromotionVO promotionVO, FilesVO filesVO, MultipartFile[] uploadFile) {
			//option 코드 가져오기
			model.addAttribute("nextNo", proService.getSeq());
			model.addAttribute("types", codeService.getType()); 
			return "content/promotion/proInsert";
		}
	
	//게시글 등록
		@PostMapping("/proInsert")
		public String proInsert(PromotionVO promotionVO, FilesVO filesVO, MultipartFile[] uploadFile) {
			proService.proInsert(promotionVO, filesVO, uploadFile); //값이 vo자동으로 저장
			//String userId = promotionVO.getUserId();
			return "redirect:/proList";
		}		
	
	//게시글수정페이지로이동
		@RequestMapping(value = "/proUpdate", method=RequestMethod.GET) 
		public String proUpdate(Model model, PromotionVO promotionVO) {
			//정보 가져오기 
			model.addAttribute("proUpdate", proService.proDetail(promotionVO));
			//사진 및 동영상
			List<FilesVO> files = new ArrayList<>();
			files = filesService.fileList(promotionVO.getProNo());
			model.addAttribute("files", files);
			/* model.addAttribute("file", filesService.fileList(promotionVO.getProNo())); */
			return "content/promotion/proUpdate";
		}
		
	//게시글 수정
		@PostMapping("/proUpdate")
		public String proUpdate(PromotionVO promotionVO, FilesVO filesVO, MultipartFile[] uploadFile) {
			proService.proUpdate(promotionVO, filesVO, uploadFile);
			return "redirect:/proList";
		}

	//게시글 삭제
		@GetMapping("/proDelete")
		public String proDelete(PromotionVO promotionVO) {
			proService.proDelete(promotionVO);
			return "redirect:/proList"; 
		}
		
	//마이페이지------------------------------------
	//1.나의 홍보게시물관리 페이지이동
		@GetMapping("/myProList")
		public String myProList(Model model, PromotionVO promotionVO, @ModelAttribute("paging") PagingVO paging) {
			model.addAttribute("promotionList", proService.myPromotionList(promotionVO, paging));
			return "content/myPage/myPromotionList";
		}
	//2.나의 홍보게시글 삭제
		@GetMapping("/proDeleteAjax")
		@ResponseBody
		public int proDeleteAjax(PromotionVO promotionVO) {
			int r = proService.proDelete(promotionVO);
			return r; 
	}	
		
}
