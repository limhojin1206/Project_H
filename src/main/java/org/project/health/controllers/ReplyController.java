package org.project.health.controllers;

import java.util.List;
import java.util.Map;

import org.project.health.models.ReplyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	ReplyDao dao;
	
	@Autowired
	ObjectMapper mapper;
	
	@PostMapping("/add")
	@ResponseBody
	public String replyAddHandle(@RequestBody String data) {
		try {
			Map map = mapper.readValue(data, Map.class);
			int rst = dao.addOne(map);
			return "true";			
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}		
	}
	
	@PostMapping("/list/{bno}")
	@ResponseBody
	public List<Map> replyListHandle(@PathVariable String bno) {
		ModelAndView mav = new ModelAndView();
		List<Map> list = dao.readAll(bno);
		
		return list;
	}
	
	@RequestMapping("/delete/{no}")
	@ResponseBody
	public String deleteHandle(@PathVariable String no) {
		int drst = dao.delete(no);
		if(drst == 1) {
			return "YYYY";
		}else {
			return "NNNN";
		}
	}
}
