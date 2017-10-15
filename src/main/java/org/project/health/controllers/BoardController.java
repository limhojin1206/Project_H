package org.project.health.controllers;

import java.util.List;
import java.util.Map;

import org.project.health.model.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardDao dao;
	
	@RequestMapping("/list")
	public ModelAndView listHandle() {
		ModelAndView mav = new ModelAndView("board/list");
		List<Map> list = dao.readAll();
		mav.addObject("list", list);
		return mav;
	}
}
