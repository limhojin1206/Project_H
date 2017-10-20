package org.project.health.controllers;

import java.util.List;
import java.util.Map;

import org.project.health.models.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberDao mdao;
	
	@RequestMapping("/searchmember")
	public ModelAndView searchmemberHandle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		mav.addObject("title", "회원검색");
		mav.addObject("nav", "memo/memonav");
		mav.addObject("section","member/memberlist");
		return mav;
	}
	
	@RequestMapping("/searchAjax")
	@ResponseBody
	public List<Map> searchIdAjaxHandle(@RequestParam String id) {
		return mdao.friendList(id+"%");
	}
}
