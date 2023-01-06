package com.eventi.left.promotion.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.promotion.mapper.PromotionBoardMapper;

@Controller
public class PromotionController {

	@Autowired 
	PromotionBoardMapper promotionMapper;
	
	@RequestMapping(value = "/promotionList", method=RequestMethod.GET)
	public String noticeList(Model model) {
		model.addAttribute("promotionList", promotionMapper.getPromotionList(null));
		return "content/promotion/promotionList";
	}
}
