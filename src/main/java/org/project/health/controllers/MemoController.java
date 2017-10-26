package org.project.health.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.project.health.controllers.ws.WSHandler;
import org.project.health.models.MemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/memo")
public class MemoController {

	@Autowired
	MemoDao mdao;
	@Autowired
	WSHandler ws;
	
	//======================================================= 쪽지함
	@RequestMapping("/memobox")
	public String memoboxHandle(Map map) {
		map.put("title", "memo");
		map.put("nav", "memo/memonav");
		map.put("section", "memo/memobox");
		return "t_sub_expr";
	}
	
	//===================================================== 쪽지보내기
	@GetMapping("/send")
	public String sendGetHandle(Map map) {
		map.put("title", "MEMO");
		map.put("nav", "memo/memonav");
		map.put("section", "memo/send");
		return "t_sub_expr";
	}
	
	@PostMapping("/send")
	public String sendPostHandle(@RequestParam Map map) {
		int rst = mdao.send(map);
		if(rst == 1) {
			System.out.println("쪽지 전송 성공");
			map.put("mode", "confirm");
			map.put("content", "새 쪽지가 왔습니다. 확인하시겠습니까?");
			map.put("href", "/memo/receivebox");
			try {
				ws.sendMessage(map);
			}catch(Exception e) {
				System.out.println(e);
			}
		}else{
			System.out.println("쪽지 전송 실패");
		}
		return "redirect:/memo/memobox";
	}
	
	//===================================================== 받은 쪽지함
	@RequestMapping("/receivebox")
	public ModelAndView receiveboxHandle(HttpSession session, @RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		List<Map> totreceiveList = mdao.totreadReceiveMemo(id);
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
		mav.addObject("pagereceiveList",mdao.pagereceiveList(pagelist));
		mav.addObject("pb", ((page-1)/10)*10+1 );
		mav.addObject("pe", (page-1)/10*10+10 <size ? (page-1)/10*10+10  : size );
		mav.addObject("last", size);
		mav.addObject("title", "RECEIVEBOX");
		mav.addObject("nav","memo/memonav");
		mav.addObject("section", "memo/receivebox");
		return mav; 
		
	}
	//===================================================== 보낸 쪽지함
	@RequestMapping("/sendbox")
	public ModelAndView sendboxHandle(HttpSession session, @RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		List<Map> sendList = mdao.readSendMemo(id);
		mav.addObject("sendList",sendList);
		mav.addObject("title", "SENDBOX");
		mav.addObject("nav","memo/memonav");
		mav.addObject("section", "memo/sendbox");
		return mav; 
		
	}
	
	//===========================================================================
	@RequestMapping("/remove")
	@ResponseBody
	public int memoRemoveHandle(@RequestParam Map param) {
		return mdao.deleteMemo(param);
	}
	
	
	//===========================================
	
	@RequestMapping("/list")
	public String listHandle(Map map, HttpSession session, HttpServletRequest request, @RequestParam(name="page", defaultValue="1") int page ) {
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		List<Map> memoAllList = mdao.readAll(id);
		request.setAttribute("memolist", memoAllList);
		
		int pagecontroll = 0;
		if(memoAllList.size()%5.0 == 0) {
			pagecontroll = memoAllList.size()/5;
		}else {
			pagecontroll = (memoAllList.size()/5)+1;
		}
		
		if(page < 1) {
			page = 1;
		}else if(page > pagecontroll) {
			
			page = pagecontroll;
		}
		
		Map bmap = new HashMap();
			bmap.put("receiver", id);
		if(page==1) {
			bmap.put("START", 1);
			bmap.put("END", 5);
		}else {
			bmap.put("START", ((page-1)*5)+1);
			bmap.put("END", ((page-1)*5)+5);
		}
		request.setAttribute("memoBlist", mdao.readBlist(bmap));
		
		map.put("title", "MEMOLIST");
		map.put("section", "memo/list");
		return "t_expr";
	}
}
