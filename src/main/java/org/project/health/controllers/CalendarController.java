package org.project.health.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
	@RequestMapping("/add")
	public String addHandle() {
		return "calendar/add";
	}
	
	@RequestMapping("/view")
	public String viewHandle() {
		return "calendar/view";
	}
}
