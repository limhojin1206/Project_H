package org.project.health.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.project.health.model.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardDao dao;
	
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
	
	@RequestMapping("/add")
	public ModelAndView addHandle(@RequestParam Map map) {
		ModelAndView mav = new ModelAndView("t_expr");
		int rst = dao.addOne(map);
		mav.addObject("rst", rst);
		return mav;
	}
}
