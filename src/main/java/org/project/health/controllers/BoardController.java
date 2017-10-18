package org.project.health.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.project.health.model.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardDao dao;
	
	@PostMapping("/search")
	public ModelAndView searchHandle(@RequestParam Map map) {
		System.out.println(map);
		//String s = (String) map.get("search");
		//s = s.replace(" ",",");
		//System.out.println(s);
		int tot = dao.countSearch(map);
		List<Map> list = dao.search(map);
		System.out.println(list);
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("title", "검색결고");
		mav.addObject("section", "board/list");
		mav.addObject("list", list);
				
		return mav;
	}
	
	@RequestMapping("/list")
	public ModelAndView listHandle(@RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_expr");
		int tot = dao.countAll();
		int pageSize = 5;
		int size = tot / pageSize;
		if(tot % pageSize > 0) {
			size++;
		}
		
		mav.addObject("tot", tot);
		mav.addObject("last", size);
		mav.addObject("pb", (page - 1) / 10 * 10 + 1);	// 1 ~ 10 ==> 1
		mav.addObject("pe", (page - 1) / 10 * 10 + 10 < size ? (page - 1) / 10 * 10 + 10 : size);	// 11 ~ 20 ==> 11
		
		if(page > size) {
			page = size;
		}
		if(page < 0) { 
			page = 1;
		}
		
		Map map = new HashMap();
		map.put("start", (page - 1) * pageSize + 1 );
		map.put("end", page * pageSize);
		List<Map> list = dao.readAll(map);
		mav.addObject("title", "게시판");
		mav.addObject("section", "board/list");
		mav.addObject("list", list);
		return mav;
	}
	
	@RequestMapping("/view/{no}")
	public ModelAndView viewHandel(@PathVariable String no) {
		ModelAndView mav = new ModelAndView("t_expr");
		Map view = dao.readOne(no);
		mav.addObject("view", view);	
		mav.addObject("title", "상세보기");
		mav.addObject("section", "board/view");
		return mav;
	}
	
	@PostMapping("/add")
	public ModelAndView addPostHandle(@RequestParam Map map) {
		ModelAndView mav = new ModelAndView("redirect:/board/list?page=1");
		int rst = dao.addOne(map);
		mav.addObject("rst", rst);
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView addGetHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("title", "글쓰기");
		mav.addObject("section", "board/add");
		return mav;
	}
	
	@RequestMapping("/submit")
	public void submit(@RequestParam Map param){
		dao.addOne(param);
	    System.out.println("에디터 컨텐츠값:"+param);
	}
}
