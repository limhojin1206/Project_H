package org.project.health.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.project.health.models.MemberDao;
import org.project.health.models.MyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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
	MyDao mydao;
	@Autowired
	SimpleDateFormat sdf;
	@Autowired
	ServletContext application;
	@Autowired
	JavaMailSender sender;
	
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
			String id = (String)map.get("id");
			Map auth = mydao.myinfo(id);
			System.out.println(auth);
			session.setAttribute("auth", auth);
			mav.setViewName("t_sub_expr");
			mav.addObject("title", "PROJECT_H");
			mav.addObject("nav", "main/mainnav");
			mav.addObject("section", "calendar/view");
		}else {
			System.out.println("회원가입 실패");
			mav.setViewName("redirect:member/join");
		}
		return mav;
	}

	// 회원가입 체크
		@PostMapping(path="/signup_check/{mode}")
		@ResponseBody
		public String signupHandle(@PathVariable String mode, @RequestParam(required=false) String param) {
			String msg="";
			//System.out.println("mode : " + mode);
			//System.out.println("param : " + param);
			if(mode.equals("id")) {
				int r = mdao.idcheck(param);
				System.out.println("r : " + r);
				if(r == 1) {
					msg = "true";
				}else {
					msg = "false";
				}
			}
			if(mode.equals("email")) {
				int r = mdao.emailcheck(param);
				if(r == 1) {
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
	public ModelAndView sessionHandle(@RequestParam Map param, HttpSession session, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		
		//session 추가
		Map auth = mdao.authsetting(param);
		session.setAttribute("auth", auth);
		//자동 로그인 체크
		String auto = (String)param.get("auto");
		if("auto".equals(auto)){
			System.out.println("쿠키생성");
			Cookie c = new Cookie("auto",(String)auth.get("ID"));
			c.setPath("/");
			c.setMaxAge(60*60*24*3);
			response.addCookie(c);
		}
		
		// redirect
		if(param.get("redirect") != null) {
			mav.setViewName("redirect:"+param.get("redirect"));
		}else {
			mav.setViewName("t_sub_expr");
			mav.addObject("title", "PROJECT_H");
			mav.addObject("nav", "main/mainnav");
			mav.addObject("section", "main/view");
		}
		return mav;
	}
	
	@RequestMapping("/loginck")
	@ResponseBody
	public int loginckHandle(@RequestParam Map param) {
		int r = mdao.login(param);
		System.out.println("로그인 가능 확인 : " + r);
		return r;
	}
	
	// 로그아웃
	@RequestMapping("/logout")
	public ModelAndView logoutHandle(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/");
		//쿠키삭제
		Cookie[] ar = request.getCookies();	
		ar = (ar==null) ? new Cookie[0] : ar;
		for(Cookie t : ar){
			String name = t.getName();
			if("auto".equals(name)) {
				System.out.println("쿠키삭제");
				System.out.println("name : "+ name);
				t.setPath("/");
				t.setMaxAge(0);
				response.addCookie(t);
			}
		}
		session.invalidate();
		System.out.println("로그아웃 성공");
		return mav;
	}
	
	// 비밀번호 찾기
	@RequestMapping("/key")
	@ResponseBody
	public String keyHandle(@RequestParam Map map, HttpSession session) {
		int r = mdao.emailcheck((String)map.get("email"));
		if(r==1) {
			UUID u = UUID.randomUUID();
			String auth_str = u.toString().substring(0,13);
			System.out.println("인증키 번호 : " + auth_str);
			session.setAttribute("confirmck", auth_str);
			try {
				String email = (String)map.get("email");
				MimeMessage msg = sender.createMimeMessage();
				//to
				msg.setRecipient(RecipientType.TO, new InternetAddress(email));
				//subject
				msg.setSubject("welcome");
				//text
				String text = "<h1>비밀번호 확인을 위한 인증키</h1>";
				text += "홈페이지에서 인증키를 입력해주세요.";
				text += "[ " + auth_str +" ]";
				msg.setText(text,"utf-8", "html");
				//from
				sender.send(msg);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return "true";
		}else {
			return "false";
		}
		
		
	}
	
	// 인증키
	@RequestMapping("/keyck")
	@ResponseBody
	public String keyckHandle(@RequestParam Map map, HttpSession session) {
		String keyinput = (String)map.get("keyinput");
		String confirmck = (String)session.getAttribute("confirmck");
		if(keyinput.equals(confirmck)) {
			session.setAttribute("email", map.get("email"));
			return "true";
		}else {
			return "false";
		}
	}
	
	// 바뀐 비번으로 로그인
	@RequestMapping("/changelogin")
	public ModelAndView changeloginHandle(@RequestParam Map param, HttpSession session) {
		int r = mydao.changepw(param);
		System.out.println("param : " + param);
		System.out.println("변경된 비밀번호 : " + r);
		
		ModelAndView mav = new ModelAndView("t_sub_expr");
		//session 추가
		Map auth = mdao.authsetting(param);
		session.setAttribute("auth", auth);
		mav.addObject("title", "PROJECT_H");
		mav.addObject("nav", "main/mainnav");
		mav.addObject("section", "calendar/view");
		return mav;
	}
}