package org.project.health.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.project.health.models.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chart")
public class ChartController {

	@RequestMapping("/chart")
	public ModelAndView infoHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		mav.addObject("title", "CHART");
		mav.addObject("nav", "chart/chartnav");
		mav.addObject("section", "chart/chart");
		return mav;
	}
}
