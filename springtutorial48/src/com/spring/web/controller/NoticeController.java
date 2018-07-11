package com.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {// Smart way
	@RequestMapping("/")
	public String showHome(Model model) {
		model.addAttribute("name", "<b>Tipu Sultan</b>");
		return "home";
	}
	/***
public ModelAndView showHome() {// old schoolway
		ModelAndView mv = new ModelAndView("home");
		Map<String, Object> model = mv.getModel();
		model.put("name", "Mr Khan");
		return mv;
	}
	 * **/
}
