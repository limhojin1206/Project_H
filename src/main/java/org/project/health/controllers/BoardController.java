package org.project.health.controllers;

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
	public ModelAndView listHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		List<Map> list = dao.readAll();
		mav.addObject("section", "board/list");
		mav.addObject("title", "게시판");
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
