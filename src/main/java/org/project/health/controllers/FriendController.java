package org.project.health.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.project.health.controllers.ws.WSHandler;
import org.project.health.models.FriendDao;
import org.project.health.models.MemberDao;
import org.project.health.models.MemoDao;
import org.project.health.utils.pagingUtil;
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
	@Autowired
	MemoDao mdao;
	@Autowired
	MemberDao memberdao;
	@Autowired
	WSHandler ws;
	@Autowired
	pagingUtil pu;
	
	// 친구 요청 리스트
	@GetMapping("/makefriend")
	public ModelAndView receiveboxHandle(HttpSession session, @RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		Map map = new HashMap<>();
		map.put("id", id);
		// 현재 페이지
		System.out.println("page : " + page);
		// 전체 글 수
		int totalCount = fdao.mftotalCount( map); 
		System.out.println("totalCount : " + totalCount);

		// 현재페이지 리스트
		List<Map> viewlist = fdao.mfviewpage(pu.list(page, id));
		System.out.println("viewlist : " + viewlist);
		
		// 페이징 번호
		Map viewpage = pu.paging(page, totalCount);
		System.out.println("viewpage : " + viewpage);
		
		mav.addObject("viewpage",viewpage);
		mav.addObject("viewlist",viewlist);
		mav.addObject("title", "친구 요청");
		mav.addObject("nav","my/mynav");
		mav.addObject("section", "friend/makefriend");
		return mav; 
	}
	
	// 친구 요청
	@PostMapping("/makefriend")
	@ResponseBody
	public int makefriendPostHandle(@RequestParam Map map) {
		//친구인지 확인
		if(fdao.existfriend(map) != 0) {
			return -1;
		}
		//요청을 보낸적 있는지 확인
		if(fdao.existmakefriend(map) != 0) {
			return -2;
		}
		//메세지 전송
		fdao.deletemsg(map);
		int r = fdao.send(map);
		if(r==1) {
			map.put("mode", "confirm");
			map.put("content", "친구요청을 확인하시겠습니까?");
			map.put("href", "/friend/makefriend");
			ws.sendMessage(map);
		}
		return r;
	}
	
	// 친구 수락 버튼
	@PostMapping("/agreefriend")
	@ResponseBody
	public int agreefriendHandle(@RequestParam Map map) {
		System.out.println("친구인지 확인 : " + fdao.existfriend(map));
		System.out.println(map.toString());
		if(fdao.existfriend(map) != 0) {
			fdao.deleteremsg(map);
			return -1;
		}
		fdao.deleteremsg(map);
		int r = fdao.agreefriend(map);
		if(r==2) {
			map.put("mode", "alert");
			map.put("content", map.get("sender")+"님께서 친구요청을 수락하셨습니다.");
			try {
				ws.sendMessage(map);
			}catch(Exception e) {
				System.out.println(e);
			}
			mdao.send(map);
		}
		return r;
	}
	
	// 친구 거절 버튼
	@PostMapping("/rejectfriend")
	@ResponseBody
	public int rejectfriendHandle(@RequestParam Map map) {
		System.out.println(map.toString());
		int r = fdao.deleteremsg(map);
		if(r==1) {
			map.put("mode", "alert");
			map.put("content", map.get("sender")+"님께서 친구요청을 거절하셨습니다.");
			mdao.send(map);
			try {
				ws.sendMessage(map);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return r;
	}
	
	// 친구 취소 버튼
	@PostMapping("/endfriend")
	@ResponseBody
	public int endfriendHandle(@RequestParam Map map) {
		int r = fdao.endfriend(map); 
		System.out.println("r : " +r);
		if(r==2) {
			map.put("mode", "alert");
			map.put("content", map.get("sender")+"님께서 친구를 취소하셨습니다.");
			mdao.send(map);
			try {
				ws.sendMessage(map);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return r;
	}
	
	// 친구 목록
	@RequestMapping("/friendlist")
	public ModelAndView myfriendlistHandle(HttpSession session, @RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		Map map = new HashMap<>();
		map.put("id", id);
		// 현재 페이지
		System.out.println("page : " + page);
		// 전체 글 수
		int totalCount = fdao.ftotalCount(map); 
		System.out.println("totalCount : " + totalCount);

		// 현재페이지 리스트
		List<Map> viewlist = fdao.fviewpage(pu.list(page, id));
		System.out.println("viewlist : " + viewlist);
		
		// 페이징 번호
		Map viewpage = pu.paging(page, totalCount);
		System.out.println("viewpage : " + viewpage);
		
		mav.addObject("viewpage",viewpage);
		mav.addObject("viewlist",viewlist);
		mav.addObject("title", "친구 요청");
		mav.addObject("nav","my/mynav");
		mav.addObject("section", "friend/friendlist");
		return mav; 
		
	}

	// 회원 검색
	@RequestMapping("/searchAjax")
	@ResponseBody
	public List<Map> searchIdAjaxHandle(@RequestParam Map map) {
		map.put("id", "%"+(String)map.get("id")+"%");
		List list = fdao.searchlist(map);
		return list;
	}
	
	// 친구 검색
	@RequestMapping("/searchFriend")
	@ResponseBody
	public List<Map> searchFriendHandle(@RequestParam Map map) {
		map.put("other", "%"+(String)map.get("other")+"%");
		List viewlist = fdao.searchfriendlist(map);
		
		return viewlist;
	}
}
