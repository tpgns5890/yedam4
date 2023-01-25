package com.eventi.left.money.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.eventi.left.money.service.MoneyService;

@Controller
public class MoneyController {

	@Autowired
	MoneyService service;
}
