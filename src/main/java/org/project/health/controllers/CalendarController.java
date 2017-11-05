package org.project.health.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
	
	@RequestMapping("/calendar")
	public ModelAndView infoHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		mav.addObject("title", "CHART");
		mav.addObject("nav", "calendar/calendarnav");
		mav.addObject("section", "calendar/calendar");
		return mav;
	}
	
	@RequestMapping("/add")
	public String addHandle() {
		return "calendar/add";
	}
	
	@RequestMapping("/view")
	public String viewHandle() {
		return "calendar/view";
	}
}
