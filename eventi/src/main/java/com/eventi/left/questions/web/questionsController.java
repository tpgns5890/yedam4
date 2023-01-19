package com.eventi.left.questions.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.common.SessionUtil;
import com.eventi.left.member.service.MemberVO;
import com.eventi.left.questions.service.QuestionsService;
import com.eventi.left.questions.service.QuestionsVO;

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

	// 등록처리
	@PostMapping("/insert")
	public String qestionsInsert(QuestionsVO vo) {
		 qService.questionsInsert(vo); //등록처리
		 return "redirect:/contest/select?cNo=" + vo.getTargetId();
	}
	
	// 나의 문의내역 전체조회
	@GetMapping("/myqnaList") 
	public String qnaList(Model model, QuestionsVO questionsVO) {
		MemberVO user = (MemberVO) SessionUtil.getSession().getAttribute("member");
		questionsVO.setUserId(user.getUserId());
		model.addAttribute("qnaList", qService.qnaList(questionsVO));
		System.out.println(questionsVO);
		//model.addAttribute("paging", paging);
		return "content/questions/queList";
	}
	
}
