package org.project.health.controllers;
 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.project.health.models.MemberDao;
import org.project.health.models.MyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
	MemberDao mdao;
	@Autowired
	MyDao mydao;
	
	@RequestMapping({"/","/index"})
	public ModelAndView indexHandle(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("title", "PROJECT_H");
		if(session.getAttribute("auth") == null) {
			if(request.getSession().getAttribute("auth_id") == null){
				mav.setViewName("index");
			}else {
				String id = (String)request.getSession().getAttribute("auth_id");
				Map auth = mydao.myinfo(id);
				System.out.println(auth);
				session.setAttribute("auth", auth);
				mav.setViewName("t_sub_expr");
				mav.addObject("title", "PROJECT_H");
				mav.addObject("nav", "main/mainnav");
				mav.addObject("section", "calendar/view");
			}
		}else {
			mav.setViewName("t_sub_expr");
			mav.addObject("title", "PROJECT_H");
			mav.addObject("nav", "main/mainnav");
			mav.addObject("section", "calendar/view");
		}
		return mav;
	}
	
	@RequestMapping("/main")
	public ModelAndView mainHomeHandle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		mav.addObject("title", "PROJECT_H");
		mav.addObject("nav", "main/mainnav");
		mav.addObject("section", "calendar/view");
		
		return mav;
	}
	
}
