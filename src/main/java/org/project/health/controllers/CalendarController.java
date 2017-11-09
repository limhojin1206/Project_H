package org.project.health.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.project.health.models.CalendarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
	@Autowired
	CalendarDao dao;
	
	@Autowired
	ObjectMapper mapper;
	
	@PostMapping("exList")
	@ResponseBody
	public List<Map> exListHandle(HttpSession session) {
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		List<Map> exList = dao.exList(id);		
		
		return exList;
	}
	
	@PostMapping("/addList")
	@ResponseBody
	public String addListHandle(@RequestParam Map map) {
		System.out.println(map);
		Map cList = dao.checkList(map);
		if(cList == null) {
			int rst = dao.addList(map);
			if(rst == 1) {
				return "YYYYY";
			}else {
				return "NNNNN";
			}
		}else {
			return "NNNN";
		}
	}
		
	@PostMapping("/add")
	@ResponseBody
	public String addHandle(@RequestParam Map map, HttpSession session) {
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		map.put("id", id);
		//System.out.println(map);
		int rst = dao.addOne(map);
		if(rst == 1) {
			return "YYYY";
		}else {
			return "NNNN";
		}
	}
	
	@PostMapping(path="/view", produces="application/json;charset=utf-8")
	@ResponseBody
	public String viewHandle(HttpSession session) {
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		List<Map> list = dao.readAll(id);
		String json = "";
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		json = json.replaceAll("EXST", "start");
		json = json.replaceAll("EXED", "end");
		json = json.replaceAll("TITLE", "title");
		json = json.replaceAll("NO", "id");
		json = json.replaceAll("COLOR", "color");
		//System.out.println(json);
		return json;
	}
	
	@PostMapping(path="/view/{no}", produces="application/json;charset=utf-8")
	@ResponseBody
	public String viewDetailHandle(@PathVariable String no) {
		System.out.println(no);
		Map detail = dao.readOne(no);
		String json = "";
		try {
			json = mapper.writeValueAsString(detail);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(json);
		return json;
	}
	
	@PostMapping("/delete/{no}")
	@ResponseBody
	public String deleteHandle(@PathVariable String no) {
		System.out.println(no);
		int rst = dao.deleteOne(no);
		if(rst == 1) {
			return "YYYY";
		}else {
			return "NNNN";
		}
	}
	
	@PostMapping("/edit/{no}")
	@ResponseBody
	public String editHandle(@PathVariable String no, @RequestParam Map map) {
		map.put("no",no);
		System.out.println(map);
		int rst = dao.editOne(map);
		if(rst == 1) {
			return "YYYYY";
		}else {
			return "NNNNN";
		}
	}
	
	@PostMapping("/success/{no}")
	@ResponseBody
	public String successHandle(@PathVariable String no) {
		int rst = dao.success(no);
		if(rst == 1) {
			return "YYYY";
		}else {
			return "NNNN";
		}
	}
	
	@GetMapping("/view")
	public ModelAndView viewgetHandle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		String id = (String)((Map)session.getAttribute("auth")).get("ID");
		List<Map> exList = dao.exList(id);
		mav.addObject("title", "캘린더");
		mav.addObject("section", "calendar/view");
		mav.addObject("nav", "calendar/calendarnav");
		mav.addObject("exList", exList);
		
		return mav;
	}
}
