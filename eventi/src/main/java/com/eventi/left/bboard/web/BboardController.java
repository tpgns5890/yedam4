package com.eventi.left.bboard.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.bboard.service.BboardService;
import com.eventi.left.bboard.service.BboardVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.files.service.FilesService;
import com.eventi.left.files.service.FilesVO;
import com.eventi.left.likes.service.LikesService;
import com.eventi.left.likes.service.LikesVO;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.reply.service.ReplyVO;

@Controller
@RequestMapping("/bboard")
public class BboardController {
	//블라인드 게시글 service
	@Autowired BboardService bboardService;
	//좋아요 service
	@Autowired LikesService likeService;
	//파일 service
	@Autowired FilesService filesService;
	
	//전체조회
	@GetMapping("/bList")
	public String bList(Model model, BboardVO bboardVO) {
		//전체리스트와 타입 구하기
		model.addAttribute("bList", bboardService.bboardList(bboardVO));
		model.addAttribute("type", bboardVO.getType());
		return "content/bboard/bList";
	}
	
	//정렬 전체 조회
	@PostMapping("/bList")
	@ResponseBody //ajax로 보낼때 필요
	public List<BboardVO> bSelectList(Model model, BboardVO bboardVO) {
		model.addAttribute("type", bboardVO.getType());
		//정렬 조건이 포함된 전체리스트 조회 후 list에 담아서 보내기
		List<BboardVO> list = bboardService.bboardList(bboardVO);
		return list;
	}
	
	//좋아요 전체 조회
	@PostMapping("/bLikeList")
	@ResponseBody
	public List<BboardVO> bLikeList(BboardVO bboardVO){
		//내가 좋아요 누른 게시글 전체리스트 조회수 list에 담아서 보내기
		List<BboardVO> list = bboardService.bboardLike(bboardVO);
		return list;
	}
	
	//단건조회
	@GetMapping("/bSelect")
	public String bSelect(Model model, BboardVO bboardVO, LikesVO likesVO) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");

		//내가 좋아요 눌렀는지 확인
		likesVO.setUserId(user.getUserId());
		likesVO.setTargetNo(bboardVO.getBBoardNo());
		likesVO.setCategory(bboardVO.getType());
		model.addAttribute("like", likeService.getLike(likesVO));
		//해당 게시글 좋아요 수
		//model.addAttribute("likeCount", likeService.countLike(likesVO));
		//해당 게시글 상세 내용
		model.addAttribute("bSelect", bboardService.bboardSelect(bboardVO));
		//이미지
		model.addAttribute("file", filesService.fileList(bboardVO.getBBoardNo()));
		return "content/bboard/bSelect";
	}
	
	//임시저장된 게시글 들고오기
	@PostMapping("/bSave")
	@ResponseBody
	public BboardVO bSave(@RequestBody BboardVO bboardVO) {
		return bboardService.bSave(bboardVO);
	}
	
	//게시글 등록폼 이동
	@GetMapping("/bInsert")
	public String bInsert(Model model, BboardVO bboardVO) {
		model.addAttribute("type", bboardVO.getType());
		model.addAttribute("nextNo", bboardService.getSeq());
		return "content/bboard/bInsert";
	}
	
	//게시글 등록
	@PostMapping("/bInsert")
	public String bInsertForm(BboardVO bboardVO, FilesVO filesVO, MultipartFile[] uploadFile) {
		bboardService.bboardInsert(bboardVO, filesVO, uploadFile);
		//내가 등록한 게시글 전체리스트 페이지로 이동
		return "redirect:/bboard/bList?type=" + bboardVO.getType();
	}
	
	//게시글 수정폼 이동
	@GetMapping("/bUpdate")
	public String bUpdate(Model model, BboardVO bboardVO) {
		model.addAttribute("bSelect", bboardService.bboardSelect(bboardVO));
		//이미지
		model.addAttribute("file", filesService.fileList(bboardVO.getBBoardNo()));
		return "content/bboard/bUpdate";
	}
	
	//게시글 수정
	@PostMapping("/bUpdate")
	public String bFreeUpdateForm(BboardVO bboardVO, MultipartFile uploadFile) {
		bboardService.bboardUpdate(bboardVO, uploadFile);
		return "redirect:/bboard/bSelect?bBoardNo=" + bboardVO.getBBoardNo();
	}
	
	//게시글 삭제
	@GetMapping("/bDelete")
	public String bDeleteForm(Model model, BboardVO bboardVO) {
		String type = bboardVO.getType();
		bboardService.bboardDelete(bboardVO);
		
		return "redirect:/bboard/bList?type=" + type;
	}
}
