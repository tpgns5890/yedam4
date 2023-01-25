package com.eventi.left.questions.web;

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

import com.eventi.left.common.PagingVO;
import com.eventi.left.common.SessionUtil;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.promotion.service.PromotionVO;
import com.eventi.left.questions.service.QuestionsService;
import com.eventi.left.questions.service.QuestionsVO;
import com.eventi.left.reply.service.ReplyVO;

@Controller
@RequestMapping("/questions")
public class questionsController {

	@Autowired
	QuestionsService qService;

	// 공모전글에 대한 문의사항리스트
	@PostMapping("/qList")
	@ResponseBody
	public List<QuestionsVO> qestionsList(QuestionsVO vo) {
		return qService.questionsList(vo);
	}
	
	//게시글등록폼이동
	@RequestMapping(value = "/queInsert", method=RequestMethod.GET) 
	public String proInsert(Model model) {
		model.addAttribute("nextNo", qService.getSeq());
		return "content/questions/queInsert";
	}
			
	// 등록처리
	@PostMapping("/insert")
	public String qestionsInsert(QuestionsVO vo) {
		qService.questionsInsert(vo); // 등록처리
		return "redirect:/questions/qList";
	}

	// 나의 문의내역 전체조회
	@GetMapping("/myqnaList")
	public String qnaList(Model model, QuestionsVO vo, PagingVO paging) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		vo.setUserId(user.getUserId());
		System.out.println(vo);
		model.addAttribute("qnaList", qService.qnaList(vo, paging));
		model.addAttribute("paging", paging);
		return "content/questions/queList";
	}
	//게시글상세조회
	@RequestMapping(value = "/queDetail", method=RequestMethod.GET) 
	public String proDetail(Model model, QuestionsVO vo) {
		//MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		//상세내용
		model.addAttribute("queDetail", qService.getQuestions(vo));
		return "content/questions/queDetail";
	}
			
	// 댓글 수정
	@PostMapping("/update")
	@ResponseBody
	public QuestionsVO replyUpdate(QuestionsVO vo) {
		 qService.questionsUpdate(vo);
		 return qService.getQuestions(vo);
	}

	// 댓글 삭제
	@PostMapping("/delete")
	@ResponseBody
	public int qestionsDelete(QuestionsVO vo) {
		return qService.questionsDelete(vo);
	}

}
