package org.project.health.controllers;
 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	
	@RequestMapping({"/","/index"})
	public String indexHandle() {
		return "index";
	}
	
	@RequestMapping("/main")
	public ModelAndView mainHomeHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("title", "PROJECT_H");
		mav.addObject("section", "main/main");
		return mav;
	}
	
}
