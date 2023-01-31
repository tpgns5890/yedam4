package com.eventi.left.money.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eventi.left.money.service.MoneyService;
import com.eventi.left.money.service.MoneyVO;

@Controller
@RequestMapping("/pay")
public class MoneyController {

	@Autowired
	MoneyService service;
	
	@GetMapping("/paytest")
	public String paytest() {
		return "/content/money/paymentTest";
	}
	
	//입금
	@PostMapping("/requestPay")
	@ResponseBody
	public MoneyVO ContestRequestPay(@RequestBody MoneyVO vo) {
		service.insertMoney(vo);
		return vo;
	}
}
