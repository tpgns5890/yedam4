package com.eventi.left.questions.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.eventi.left.questions.service.QuestionsService;
import com.eventi.left.questions.service.QuestionsVO;

@Controller
@RequestMapping("/questions")
public class questionsWeb {

	@Autowired
	QuestionsService qService;

	// 공모전글에 대한 문의사항리스트
	@PostMapping("/qList")
	@ResponseBody
	public List<QuestionsVO> QuestionsList(QuestionsVO vo) {

//		//전체리스트, 반환 리스트객체 선언
//		List<QuestionsVO> qList = qService.questionsList(vo);
//		for (QuestionsVO setVO : qList) {
//			System.out.println(setVO.getWritingDt()); //Sun Jan 08 00:00:00 KST 2023
//		}
//		
//		List<QuestionsVO> setList = new ArrayList<>();
//
//		// Date => String타입 vo세팅
//		for (QuestionsVO setVO : qList) {
//			java.util.Date utilDate = new java.util.Date(rs.getDate("vo.getWritingDt()").getTime());
//
//			
//			Date date = new Date();
//			String writingDt = new SimpleDateFormat("yyyy-MM-dd").format(vo.getWritingDt());
//			
//			System.out.println("============================");
//			System.out.println(vo.getWritingDt());
//			System.out.println(writingDt);
//
//			setVO.setDtStr(writingDt);
//			setList.add(setVO);
//		}
//
//		return setList;
		return qService.questionsList(vo);
	}
}
