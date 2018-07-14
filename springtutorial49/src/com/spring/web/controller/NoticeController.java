package com.spring.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.web.dao.Notice;
import com.spring.web.service.NoticesService;

@Controller
public class NoticeController {// Smart way
	
	NoticesService noticesService;
	@Autowired
	public void setNoticesService(NoticesService noticesService) {
		this.noticesService = noticesService;
	}

	//http://localhost:8081/notices/test?id=100
	//Getting URL Parameters
	@RequestMapping("/test")
	public String showTest(Model model , @RequestParam("id")String id) {
		System.out.println("ID is : "+id);
		return "home";
	}	
		
	
	
	@RequestMapping("/notices")
	public String showNotice(Model model) {
		List<Notice> notices = noticesService.getCurrent();

		model.addAttribute("notices", notices );
		return "notices";
	}
	
	
	

	@RequestMapping("/createnotice")
	public String createNotice(Model model) {
		model.addAttribute(new Notice());
		return "createnotice";
	}
	//notice beans will inject automatically
	@RequestMapping(value = "/docreate", method=RequestMethod.POST)
	public String doCreate(Model model,@Valid Notice notice, BindingResult result) {
		System.out.println(notice);
		
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error: errors) {
				System.out.println(error.getDefaultMessage());
			}
			
			
			return "createnotice";
		}
		noticesService.create(notice);
//		else {
//			System.out.println("Form is validet");
//		}
		return "noticecreated";
	}
}