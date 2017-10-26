package org.project.health.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.project.health.models.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberDao mdao;
	
	@Autowired
	SimpleDateFormat sdf;
	
	@Autowired
	ServletContext application;
	
	// 회원가입
		@GetMapping("/join")
		public ModelAndView joinGetHandle(Map map) {
			ModelAndView mav = new ModelAndView("/member/join");
			map.put("title", "JOIN");
			return mav;
		}  
		
		@PostMapping("/join")
		public ModelAndView joinPostHandle(@RequestParam Map map, HttpServletRequest request, HttpSession session) {
			ModelAndView mav = new ModelAndView();
			int r = mdao.join(map);
			//성공은 r=1 실패는 r=0
			//System.out.println("r : " + r);
			if(r == 1) {
				System.out.println("회원가입 성공");
			// 2. session auth에 id 등록하기
				Map auth = mdao.authsetting(map);
				session.setAttribute("auth", auth);
				mav.setViewName("t_expr");
				mav.addObject("title", "PROJECT_H");
				mav.addObject("section", "main/main");
			}else {
				System.out.println("회원가입 실패");
				mav.setViewName("redirect:member/join");
			}
			return mav;
		}

		// 회원가입 체크
		@PostMapping(path="/signup_check/{mode}", produces="text/html;charset=UTF-8")
		@ResponseBody
		public String signupHandle(@PathVariable String mode, @RequestBody(required=false) String body) {
			String msg="";
			if(mode.equals("id")) {
				List list = mdao.idcheck(body);
				if(list.size() == 0) {
					msg = "true";
				}else {
					msg = "false";
				}
			}
			if(mode.equals("email")) {
				List list = mdao.emailcheck(body);
				if(list.size() == 0) {
					msg = "true";
				}else {
					msg = "false";
				}
			}
			return msg;
		}
		
	// 로그인
	@GetMapping("/login")
	public String loginHandle() {
		return "/index";
	}
	
	@PostMapping("/login")
	public ModelAndView sessionHandle(@RequestParam Map param, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int r = mdao.login(param);
		System.out.println("r : " + r);
		if (r == 1) {
			//session 추가
			Map auth = mdao.authsetting(param);
			session.setAttribute("auth", auth);
			// redirect
			if(param.get("redirect") != null) {
				mav.setViewName("redirect:"+param.get("redirect"));
			}else {
				mav.setViewName("t_expr");
				mav.addObject("title", "PROJECT_H");
				mav.addObject("section", "main/main");
			}
		} else {
			mav.addObject("temp", "temp");
		}
		return mav;
	}
	
	@GetMapping("/logout")
	public String logoutHandle(HttpSession session) {
		session.invalidate();
		System.out.println("로그아웃 성공");
		return "index";
	}
	
}
