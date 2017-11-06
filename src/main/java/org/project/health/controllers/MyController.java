package org.project.health.controllers;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.project.health.models.MemberDao;
import org.project.health.models.MyDao;
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
@RequestMapping("/my")
public class MyController {


	@Autowired
	MemberDao memberdao;
	@Autowired
	MyDao mydao;
	@Autowired
	ServletContext application;
	@Autowired
	SimpleDateFormat sdf;
	
	
	// 개인정보 뷰
	@RequestMapping("/info")
	public ModelAndView infoHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		Map myinfo = mydao.myinfo(session.getAttribute("auth"));
		System.out.println("myinfo : " + myinfo);
		mav.addObject("myinfo", myinfo);
		mav.addObject("title", "MYINFO");
		mav.addObject("nav", "my/mynav");
		mav.addObject("section", "my/info");
		return mav;
	}
	
	// 개인정보 수정 페이지 뷰
	@GetMapping("/adjust")
	public ModelAndView adjustGetHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		Map myinfo = mydao.myinfo(session.getAttribute("auth"));
		System.out.println("myinfo : " + myinfo);
		//session.setAttribute("myinfo", myinfo);
		mav.addObject("myinfo", myinfo);
		mav.addObject("title", "title");
		mav.addObject("nav", "my/mynav");
		mav.addObject("section", "/my/adjust");
		return mav;
	}
	
	// 개인정보 수정
	 @PostMapping("/adjust")
     public ModelAndView userinfoHandle(@RequestParam Map param, HttpSession session) {
 		ModelAndView mav = new ModelAndView ("t_sub_expr");
 		int r = mydao.update(param);
 		if(r == 1) {
 			System.out.println("개인정보 변경 성공");
 		}else {
 			System.out.println("개인정보 변경 실패");
 		}
 		Map myinfo = mydao.myinfo(session.getAttribute("auth"));
         mav.addObject("myinfo", myinfo);
         mav.addObject("title","session");
         mav.addObject("nav", "my/mynav");
         mav.addObject("section","/my/info");
         return mav;
 	}
	
	// 프로필 사진 수정
    @PostMapping("/changepic")
    @ResponseBody
	public int changpicHandle(@RequestParam Map param, @RequestParam(name="changpic", required=false) MultipartFile f, HttpSession session) {
		System.out.println(f.isEmpty());
    	String id = (String)((Map)session.getAttribute("auth")).get("ID");
        String fileName = id +"_"+sdf.format(System.currentTimeMillis());
        System.out.println("FIleName : "+ fileName);
        File dst = new File(application.getRealPath("/profiles"), fileName);
        try { 
        	f.transferTo(dst);
        }catch(Exception e) { 
        	e.printStackTrace();
        }
        Map picMap = new HashMap<>(); 
        picMap.put("id", id);
        picMap.put("url", "/profiles/"+fileName); 
        int r = mydao.addPic(picMap); 
        System.out.println("사진변경결과 : " +r );
        return r;
	}

    //================================================================================
	//회원탈퇴 
        @RequestMapping("/drop")
	    public ModelAndView dropGetHandle( HttpSession session ) {
        	ModelAndView mav = new ModelAndView();
        	String id = (String)((Map)session.getAttribute("auth")).get("ID");
        	System.out.println(id);
			int r = mydao.drop(id); 
			//성공했을 시 r=1, 실패했을 시 r=0으로 출력 
			System.out.println("r : "+ r );

			if(r == 1) { 
				session.invalidate();
				mav.setViewName("/redirect:/index");
			}else { 
				 System.out.println("회원탈퇴에 실패하였습니다.");
				
				}
			return mav;
        }        

       
}
