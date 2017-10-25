package org.project.health.controllers;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.apache.tiles.request.Request;
import org.project.health.models.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.mchange.v1.db.sql.ConnectionUtils;


@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	MemberDao memberDao;
	
	
	@PostMapping("/login")
	public ModelAndView sessionHandle(@RequestParam Map param, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int t = memberDao.existOne(param);
		System.out.println("t : " + t);
		if (t == 1) {
			HashMap u = memberDao.readOneByIdOrEmail((String)param.get("idmail"));
			System.out.println("정보가져옴");
			System.out.println("u : " + u);
			//session 추가
			session.setAttribute("auth", u);
			mav.setViewName("t_expr");
			mav.addObject("title", "PROJECT_H");
			mav.addObject("section", "main/main");
		} else {
			mav.setViewName("/index");
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
	
	@RequestMapping("/main")
	public void mainHandle() {

	}
	
	@GetMapping("/join")
	public void joinGetHandle(Model model) {
		System.out.println("회원가입하기.");
		
		model.addAttribute("section", "/login/join");

	}
	
	
	/*
		@GetMapping("/keep")
		 public void keepHandle(HttpSession session) {
	     String logid = Request.getParameter("ID"); 
	     String logpass = Request.getsParameter("PASS"); 
	     
			boolean rst = false; 
			try { 
				
				Connection conn = ConnectionUtils.open();
			String sql = "select * from member where id=? and pass=?";
				PreparedStatement ps = conn.prepareStatement(sql); // 전송 준비
			
			
			}
			conn.close();
		} Catch (Exception e) {
			System.out.println(e);
		}
		if (rst) {
			// 이왕에 세션에 데이터 저장시켜둘때, 로그인 id값을 올려두게 되면,
			session.setAttribute("auth", ID);
			// 체크박스가 선택이 된건지 안된거지 if 해서
			if(request.getParameter("keep") != null) {
				Cookie c = new Cookie("keep", keep);
				c.setPath("/");
				c.setMaxAge(60*60*24*7);
				response.addCookie(c);
			}
			
			if(session.getAttribute("move")!=null) {
					
			}
		}
		
		
		}
*/
		 
	}  









