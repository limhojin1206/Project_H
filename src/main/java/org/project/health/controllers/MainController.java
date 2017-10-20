package org.project.health.controllers;
 
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	
	@RequestMapping("/")
	public String indexHandle() {
		return "index";
	}
	
	@RequestMapping("/main")
	public ModelAndView mainHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("title", "PROJECT_H");
		mav.addObject("section", "main/main");
		return mav;
	}
	
}
