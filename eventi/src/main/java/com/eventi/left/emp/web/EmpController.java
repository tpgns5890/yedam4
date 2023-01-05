package com.eventi.left.emp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventi.left.emp.mapper.EmpMapper;


@Controller
public class EmpController {

	@Autowired
	EmpMapper empMapper;
	
	@RequestMapping(value = "/empList", method=RequestMethod.GET)
	public String empList(Model model) {
		model.addAttribute("empList", empMapper.getEmpList(null));
		return "empList";
	}
	
	@RequestMapping(value = "/home")
	public String index() {
		return "content/home";
	}
	@RequestMapping(value = "/about")
	public String about() {
		return "content/product-single";
	}
}