package org.project.health.controllers;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.project.health.controllers.ws.WSHandler;
import org.project.health.models.MemberDao;
import org.project.health.models.MemoDao;
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
	@Autowired
	MemoDao medao;
	@Autowired
	WSHandler ws;
	
	@RequestMapping({"/","/index"})
	public ModelAndView indexHandle(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("title", "D.U.EXERCISE");
		if(session.getAttribute("auth") == null) {
			if(request.getSession().getAttribute("auth_id") == null){
				mav.setViewName("index");
			}else {
				String id = (String)request.getSession().getAttribute("auth_id");
				Map auth = mydao.myinfo(id);
				//System.out.println(auth);
				session.setAttribute("auth", auth);
				mav.setViewName("t_sub_expr");
				mav.addObject("title", "D.U.EXERCISE");
				mav.addObject("nav", "main/mainnav");
				mav.addObject("section", "calendar/view");
			}
		}else {
			mav.setViewName("t_sub_expr");
			mav.addObject("title", "D.U.EXERCISE");
			mav.addObject("nav", "main/mainnav");
			mav.addObject("section", "calendar/view");
		}
		return mav;
	}
	
	@RequestMapping("/main")
	public ModelAndView mainHomeHandle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		mav.addObject("title", "D.U.EXERCISE");
		mav.addObject("nav", "main/mainnav");
		mav.addObject("section", "calendar/view");
		
		return mav;
	}

	@RequestMapping("/main/myinfo")
	public ModelAndView mainHomeinfoHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		Map myinfo = mydao.myinfo(session.getAttribute("auth"));
		mav.addObject("myinfo", myinfo);
		mav.addObject("title", "D.U.EXERCISE");
		mav.addObject("nav", "main/mainnav");
		mav.addObject("section", "my/info");
		
		return mav;
	}
	
	@RequestMapping("/main/mycalendar")
	public ModelAndView mainHomecalendarHandle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		mav.addObject("title", "D.U.EXERCISE");
		mav.addObject("nav", "main/mainnav");
		mav.addObject("section", "calendar/view");
		
		return mav;
	}
	
	@RequestMapping("/main/mychart")
	public ModelAndView mainHomechartHandle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		mav.addObject("title", "D.U.EXERCISE");
		mav.addObject("nav", "main/mainnav");
		mav.addObject("section", "chart/mychart");
		
		return mav;
	}
	@RequestMapping("/main/mymemo")
	public ModelAndView receiveboxHandle(HttpSession session, @RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		List<Map> totreceiveList = medao.totreadReceiveMemo(id);
		int pagecontroll = 0;
		if(totreceiveList.size()%5.0 == 0) {
			pagecontroll = totreceiveList.size()/5;
		}else {
			pagecontroll = (totreceiveList.size()/5)+1;
		}
		
		if(page < 1) {
			page = 1;
		}else if(page > pagecontroll) {
			page = pagecontroll;
		}
		
		Map pagelist = new HashMap();
		if(page==1) {
			pagelist.put("RECEIVER", id);
			pagelist.put("START", 1);
			pagelist.put("END", 10);
			
		}else {
			pagelist.put("RECEIVER", id);
			pagelist.put("START", ((page-1)*10)+1);
			pagelist.put("END", ((page-1)*10)+10);
		}
		int tot=totreceiveList.size();
		int pagesize = 10;
		int size= tot/ pagesize;
		
		if(size % pagesize > 0 ) {
			size++;
		}
		mav.addObject("page",page);
		mav.addObject("totreceiveList",totreceiveList);
		mav.addObject("pagereceiveList",medao.pagereceiveList(pagelist));
		mav.addObject("pb", ((page-1)/10)*10+1 );
		mav.addObject("pe", (page-1)/10*10+10 <size ? (page-1)/10*10+10  : size );
		mav.addObject("last", size);
		mav.addObject("title", "D.U.EXERCISE");
		mav.addObject("nav", "main/mainnav");
		mav.addObject("section", "memo/receivebox");
		return mav; 
		
	}
	
	@RequestMapping("/main/view")
	public ModelAndView mainHomeviewHandle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		mav.addObject("title", "D.U.EXERCISE");
		mav.addObject("nav", "main/mainnav");
		mav.addObject("section", "main/view");
		
		return mav;
	}
	
}
