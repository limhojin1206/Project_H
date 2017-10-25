package org.project.health.controllers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.project.health.models.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	
	@GetMapping("/profile")
	public ModelAndView profileGetHandle() {
		ModelAndView mav = new ModelAndView("myprofile/profile");
		mav.addObject("title", "내 프로필");
		return mav;
	}
	
	
	
	@PostMapping("/profile")
	public ModelAndView profilePostHandle(@RequestParam Map param, @RequestParam(name="profile") MultipartFile f, HttpSession session) throws InterruptedException {
		//System.out.println(request.getParameter("nick"));
		System.out.println(application.getRealPath("/myprofile/profiles"));
		
		ModelAndView mav = new ModelAndView("redirect:/myprofile/info");
		
		
		System.out.println("��������==================");
		System.out.println(f.toString());
		System.out.println(f.isEmpty());
		System.out.println(f.getContentType());
		System.out.println(f.getName());
		System.out.println(f.getOriginalFilename());
		System.out.println(f.getSize());
		//transferTo(File f) .. ���� ���ε�. getInputStream()
		String fileName = ((Map)session.getAttribute("auth")).get("ID")+"_"
		+sdf.format(System.currentTimeMillis());
		
		File dst = new File(application.getRealPath("/profiles"), fileName);
		try {
			f.transferTo(dst);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map picMap = new HashMap<>();
			picMap.put("id", ((Map)session.getAttribute("auth")).get("ID"));
			picMap.put("uri", "myprofile/profiles/"+ fileName);
			int r = mdao.addPic(picMap); 
			System.out.println("R : " + r);
		
		mav.addObject("title", "������ ����");
		mav.addObject("section", "/myprofile/info");
		
		return mav;
	}
	
	
}
