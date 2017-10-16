package org.project.health.controllers;

import java.util.Map;
import java.util.UUID;


import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.project.health.controller.model.MemberDao;
import org.project.health.controller.ws.AlertWSHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class JoinController {
	@Autowired
	MemberDao memberDao;

	@Autowired
	AlertWSHandler aws;	// ������ �ڵ鷯�� AutoWired �ɾ ���� ���.
	
	@Autowired
	JavaMailSender sender;
	
	@GetMapping(path = "/join")
	public String joinGetHandle(Model model) {
		model.addAttribute("section", "login/join");
		return "t_expr";
	}  
	
	@RequestMapping("/join/auth")
	@ResponseBody
	public String joinAuthHandle(@RequestBody String target, HttpSession session) {
		System.out.println("target : " + target);
		String key = UUID.randomUUID().toString().substring(0,13);
		session.setAttribute("authkey",key);
		try {
			MimeMessage msg = sender.createMimeMessage();
			msg.setRecipient(RecipientType.TO, new InternetAddress(target));
			msg.setSubject("[SPRING HUB] ����Ű�Դϴ�. ");
			RestTemplate tm = new RestTemplate();
			// String text = tm.getForObject("/auth/mail", String.class);
			String text ="<h1>SPRING HUB</h1>";
			text +="�������������� ���� ����Ű�� �����帳�ϴ�.";
			text += "����Ű : <b>"+key+"</b>";
			//
			msg.setText(text,"utf-8","html");
			sender.send(msg);
			return "true";	
		}catch(Exception e) {
			System.out.println(e);
			return "false";
		}
	}
	

	@RequestMapping("/join/authCheck")
	@ResponseBody
	public String joinAuthCheckHandle(@RequestParam String key, HttpSession session) {
		System.out.println(key);
		System.out.println(session.getAttribute("authkey"));
		System.out.println(key.equals(session.getAttribute("authkey")));
		if(key.equals(session.getAttribute("authkey")) ) {
			return "YYYYY";
		}else {
			return "NNNNN";
		}
	}
	
	@PostMapping("/join")
	public String joinPostHandle(@RequestParam Map map, HttpSession session, Model model) {
		try {
			boolean b = memberDao.addOne(map);
			
			session.setAttribute("auth", map);
			session.setAttribute("auth_id", map.get("id"));
			
			/*
			 * AlertWSHandler�� ���ؼ�, �޼����� ��������. 
			 */
			aws.sendMessage("������ �����Ͽ����ϴ�"); 
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("temp", map);
			model.addAttribute("section", "join");
			return "t_expr";
		}
	}

	@PostMapping("/signup_check/{mode}")
	public String signupHandle() {
		return "";
	}
}
