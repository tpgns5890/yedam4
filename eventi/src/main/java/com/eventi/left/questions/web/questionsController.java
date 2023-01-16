package com.eventi.left.questions.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.eventi.left.contest.service.ContestVO;
import com.eventi.left.contest.service.WinnerVO;
import com.eventi.left.files.service.FilesVO;
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

}
