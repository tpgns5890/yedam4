package com.eventi.left.questions.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	// 문의사항리스트(값이 안나옴)..
	@RequestMapping("/qList")
	public String QuestionsList(Model model) {
		model.addAttribute("qList", qService.questionsList(null));
		return "content/questions/questionsList";
	}

	// 문의사항답변리스트
	@RequestMapping("/qrepList")
	public String QuestionsRepList(Model model) {
		model.addAttribute("qrepList", qrepService.questionsRepList(null));
		return "content/questions/questionsRepList";
	}



}
