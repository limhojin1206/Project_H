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

	
	@RequestMapping("/")
	public String indexHandle() {
		return "index";
	}
	
	@RequestMapping("/main")
	public ModelAndView mainHandle(HttpSession session, @RequestParam Map param) {
		ModelAndView mav = new ModelAndView("t_expr");
		Map map = new HashMap();
		System.out.println(param.toString());
		if(param.get("ID").equals("asd")) {
			map.put("ID", "asd");
		}else {
			map.put("ID", "qwe");
		}
		session.setAttribute("auth", map);
		mav.addObject("title", "PROJECT_H");
		mav.addObject("section", "main/main");
		return mav;
	}
}
