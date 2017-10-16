package org.project.health.controllers;

import org.project.health.controller.model.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@Autowired
	MemberDao memberDao;
	
	
	@RequestMapping({"/", "/Main" })
	public String mainHandle() {
		return "index";

	
	}
}

