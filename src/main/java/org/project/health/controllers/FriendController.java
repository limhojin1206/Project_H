package org.project.health.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.project.health.models.FriendDao;
import org.project.health.models.MemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	FriendDao fdao;
	
	@PostMapping("/makefriend")
	@ResponseBody
	public int makefriendPostHandle(@RequestParam Map map) {
		System.out.println("친구인지 확인 : " + fdao.existfriend(map));
		System.out.println(map.toString());
		if(fdao.existfriend(map) != 0) {
			fdao.deletemsg(map);
			return -1;
		}
		fdao.deletemsg(map);
		return fdao.send(map);
	}
	
	@GetMapping("/makefriend")
	public ModelAndView receiveboxHandle(HttpSession session, @RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		// 임시
		//String id = (String)((Map)session.getAttribute("auth")).get("ID");
		String id = "asd";
		
		
		List<Map> totreceiveList = fdao.totreadReceiveMemo(id);
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
		int tot = totreceiveList.size();
		int pagesize = 10;
		int size= tot/ pagesize;
		
		if(size % pagesize > 0 ) {
			size++;
		}
		
		mav.addObject("page",page);
		mav.addObject("totreceiveList",totreceiveList);
		mav.addObject("pagereceiveList",fdao.pagereceiveList(pagelist));
		mav.addObject("pb", ((page-1)/10)*10+1 );
		mav.addObject("pe", (page-1)/10*10+10 <size ? (page-1)/10*10+10  : size );
		mav.addObject("last", size);
		mav.addObject("title", "친구 요청");
		mav.addObject("nav","memo/memonav");
		mav.addObject("section", "friend/makefriend");
		return mav; 
		
	}
	
	@PostMapping("/agreefriend")
	@ResponseBody
	public int agreefriendHandle(@RequestParam Map map) {
		System.out.println("친구인지 확인 : " + fdao.existfriend(map));
		System.out.println(map.toString());
		if(fdao.existfriend(map) != 0) {
			fdao.deletemsg(map);
			return -1;
		}
		fdao.deletemsg(map);
		return fdao.agreefriend(map);
	}
	
	@PostMapping("/rejectfriend")
	@ResponseBody
	public int rejectfriendHandle(@RequestParam Map map) {
		System.out.println("친구인지 확인 : " + fdao.existfriend(map));
		System.out.println(map.toString());
		if(fdao.existfriend(map) != 0) {
			fdao.deletemsg(map);
			return -1;
		}
		fdao.deletemsg(map);
		return fdao.agreefriend(map);
	}
	
	@RequestMapping("/myfriendlist")
	public ModelAndView myfriendlistHandle(HttpSession session, @RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		// 임시
		//String id = (String)((Map)session.getAttribute("auth")).get("ID");
		String id = "asd";
		List<Map> totreceiveList = fdao.myfriendlist(id);
		System.out.println("길이 : " + totreceiveList.size());
		System.out.println(totreceiveList.toString());
		mav.addObject("totreceiveList",totreceiveList);
		mav.addObject("title", "친구 요청");
		mav.addObject("nav","memo/memonav");
		mav.addObject("section", "member/memberlist");
		return mav; 
		
	}
	
	@RequestMapping("/searchAjax")
	@ResponseBody
	public List<Map> searchIdAjaxHandle(@RequestParam Map map) {
		map.put("other", (String)map.get("other")+"%");
		return fdao.friendList(map);
	}
}
