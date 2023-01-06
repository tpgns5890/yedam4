package com.eventi.left.questions.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eventi.left.questions.service.QuestionsRepService;
import com.eventi.left.questions.service.QuestionsService;

@Controller
@RequestMapping("/questions")
public class questionsWeb {

	@Autowired
	QuestionsRepService qrepService;
	QuestionsService qService;

	// 문의사항답변리스트
	@RequestMapping(value = "/qrepList")
	public String QuestionsRepList(Model model) {
		model.addAttribute("qrepList", qrepService.questionsRepList(null));
		return "content/questions/questionsRepList";
	}

	// 문의사항리스트(값이 안나옴)..
	@RequestMapping(value = "/qList")
	public String QuestionsList(Model model) {
		model.addAttribute("qList", qService.questionsList(null));
		return "content/questions/questionsList";
	}

}
