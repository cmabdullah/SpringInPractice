package com.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.web.dao.Notice;
import com.spring.web.service.NoticesService;

@Controller
public class NoticeController {// Smart way
	
	NoticesService noticesService;
	@Autowired
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	@RequestMapping("/")
	public String showHome(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
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
