package com.eventi.left.questions.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.questions.service.QuestionsRepService;
import com.eventi.left.questions.service.QuestionsService;
import com.eventi.left.questions.service.QuestionsVO;

@Controller
@RequestMapping("/questions")
public class questionsWeb {

	@Autowired
	QuestionsRepService qrepService;
	@Autowired
	QuestionsService qService;
	
	// 공모전글에 대한 문의사항리스트
	@PostMapping("/qList")
	@ResponseBody	
	public List<QuestionsVO> QuestionsList(Model model, QuestionsVO vo) {
		System.out.println(vo.getTargetId());
		return qService.questionsList(vo);
	}

	// 문의사항답변리스트
	@RequestMapping("/qrepList")
	public String QuestionsRepList(Model model) {
		model.addAttribute("qrepList", qrepService.questionsRepList(null));
		return "content/questions/questionsRepList";
	}



}
