package org.project.health.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.project.health.models.CalendarDao;
import org.project.health.models.ChartDao;
import org.project.health.models.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/chart")
public class ChartController {

	@Autowired
	MemberDao mdao;
	@Autowired
	ChartDao cdao;
	@Autowired
	ObjectMapper mapper;

	@RequestMapping("/chart")
	public ModelAndView chartHandle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			mav.addObject("title","chart");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/chart");
		return mav;
	}
	//성별별 차트
	@RequestMapping("/01")
	public ModelAndView chart01Handle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			List<Map> list = mdao.countByGender();
			System.out.println(list);
			mav.addObject("list",list);
			mav.addObject("title","남/녀 성 비율");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/01");
		return mav;
	}

	@RequestMapping(path="/piedata", produces="application/json;charset=utf-8")
	@ResponseBody
	public String piedataJSONHandle(HttpSession session) throws JsonProcessingException {
		List<Map> list = mdao.countByGender();
		session.setAttribute("list", list);
		List json = new ArrayList<>();
		for(Map m : list) {
			Object[] ar = new Object[] {m.get("GENDER"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	//나이별 차트
	@RequestMapping("/02")
	public ModelAndView chart02Handle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			List<Map> list = mdao.countByAge();
			System.out.println(list);
			mav.addObject("list",list);
			mav.addObject("title","연령별 비율");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/02");
		return mav;
	}
	
	@RequestMapping(path="/piedata02", produces="application/json;charset=utf-8")
	@ResponseBody
	public String piedataJSONHandle02(HttpSession session) throws JsonProcessingException {
		List<Map> list = mdao.countByAge();
		session.setAttribute("list", list);
		List json = new ArrayList<>();
		for(Map m : list) {
			Object[] ar = new Object[] {m.get("AGE"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	//MU
	@RequestMapping("/03")
	public ModelAndView chart03Handle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			List<Map> list1 = cdao.MyRatioExMu();
			System.out.println(list1);
			mav.addObject("list",list1);
			mav.addObject("title","운동 대분류 1");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/03");
		return mav;
	}
	
	@RequestMapping(path="/piedata1", produces="application/json;charset=utf-8")
	@ResponseBody
	public String piedataJSONHandle03(HttpSession session) throws JsonProcessingException {
		List<Map> list = cdao.MyRatioExMu();
		session.setAttribute("list", list);
		List json = new ArrayList<>();
		for(Map m : list) {
			Object[] ar = new Object[] {m.get("EXMU"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	//PART
	@RequestMapping("/04")
	public ModelAndView chart04Handle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			List<Map> list = cdao.MyRatioExPart();
			System.out.println(list);
			mav.addObject("list",list);
			mav.addObject("title","운동 대분류 2");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/04");
		return mav;
	}

	@RequestMapping(path="/bardata", produces="application/json;charset=utf-8")
	@ResponseBody
	public String piedataJSONHandle04(HttpSession session) throws JsonProcessingException {
		List<Map> list = cdao.MyRatioExPart();
		session.setAttribute("list", list);
		List json = new ArrayList<>();
		json.add(new Object[] {"expart", "cnt"});
		for(Map m : list) {
			Object[] ar = new Object[] {m.get("EXPART"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	@RequestMapping("/05")
	public ModelAndView chart05Handle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			List<Map> list = cdao.MyRatioExPart();
			System.out.println(list);
			mav.addObject("list",list);
			mav.addObject("title","운동 대분류 2");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/05");
		return mav;
	}

	@RequestMapping(path="/bardata2", produces="application/json;charset=utf-8")
	@ResponseBody
	public String piedataJSONHandle05(HttpSession session) throws JsonProcessingException {
		List<Map> list = cdao.MyRatioExPart();
		session.setAttribute("list", list);
		List json = new ArrayList<>();
		json.add(new Object[] {"expart", "cnt"});
		for(Map m : list) {
			Object[] ar = new Object[] {m.get("EXPART"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	
	@RequestMapping("/06")
	public ModelAndView chart06Handle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			List<Map> list = cdao.MyRatioExPart();
			System.out.println(list);
			mav.addObject("list",list);
			mav.addObject("title","운동 대분류 2");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/06");
		return mav;
	}

	@RequestMapping(path="/chart", produces="application/json;charset=utf-8")
	@ResponseBody
	public String piedataJSONHandle06(HttpSession session) throws JsonProcessingException {
		List<Map> list = cdao.MyRatioExPart();
		session.setAttribute("list", list);
		List json = new ArrayList<>();
		json.add(new Object[] {"expart", "cnt"});
		for(Map m : list) {
			Object[] ar = new Object[] {m.get("EXPART"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
}

