package org.project.health.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.project.health.models.MemberDao;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/join")
public class JoinController {
	@Autowired
	MemberDao memberDao;

	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	JavaMailSender sender;
	
	@GetMapping("/join")
	public String joinGetHandle(Map map) {
		map.put("title", "JOIN");
	    map.put("section", "/login/join");
		return "t_expr";
	}  

	
	@PostMapping("/join")
	public String joinPostHandle(@RequestParam Map map, HttpServletRequest request, HttpSession session) throws ClassNotFoundException, SQLException, JsonParseException,JsonMappingException, IOException {
		// 1. DB에 등록하기
		
		int r = memberDao.join(map);
		//성공은 r=1 실패는 r=0
		System.out.println("r : " + r);
		
		if(r == 1) {
			System.out.println("회원가입 성공");
		// 2. session auth에 id 등록하기
			Map lmap = memberDao.login(map);
			System.out.println(lmap.toString());
			session.setAttribute("auth", lmap);
		return "redirect:/"; 
		}
		
		System.out.println("회원가입 실패");
		return "redirect:join"; 
}
	// 3. 메인으로 이동
//			return "/login/main";
//		}

	// 
	@PostMapping(path="/signup_check/{mode}", 
				produces="text/html;charset=UTF-8")
		@ResponseBody
		public String signupHandle(@PathVariable String mode, @RequestBody(required=false) String body) {
			String msg="";
			if(mode.equals("id")) {
				List list = memberDao.idcheck(body);
				if(list.size() == 0) {
					msg = "true";
				}else {
					msg = "false";
				}
			}
			
			if(mode.equals("email")) {
				List list = memberDao.emailcheck(body);
				if(list.size() == 0) {
					msg = "true";
				}else {
					msg = "false";
				}
			}
			return msg;
		}
	}		
