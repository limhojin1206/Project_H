package org.project.health.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

	// 전체 성별, 연령대 분표 차트
	@RequestMapping("/GenderAge")
	public ModelAndView chart01Handle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			List<Map> list01 = mdao.countByGender();
			System.out.println(list01);
			List<Map> list02 = mdao.countByAge();
			System.out.println(list02);
			mav.addObject("list01",list01);
			mav.addObject("list02",list02);
			mav.addObject("title","남/녀 성 비율 및 연령대 비율 차트");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/GenderAge");
		return mav;
	}

	@RequestMapping(path="/piedata01", produces="application/json;charset=utf-8")
	@ResponseBody
	public String piedataJSONHandle(HttpSession session) throws JsonProcessingException {
		List<Map> clist01 = mdao.countByGender();
		session.setAttribute("clist01", clist01);
		List json = new ArrayList<>();
		for(Map m : clist01) {
			Object[] ar = new Object[] {m.get("GENDER"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	@RequestMapping(path="/piedata02", produces="application/json;charset=utf-8")
	@ResponseBody
	public String piedataJSONHandle2(HttpSession session) throws JsonProcessingException {
		List<Map> clist02 = mdao.countByAge();
		session.setAttribute("clist02", clist02);
		List json = new ArrayList<>();
		for(Map m : clist02) {
			Object[] ar = new Object[] {m.get("AGE"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	

	@RequestMapping("/TotalExercise")
	public ModelAndView chart02Handle() {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			List<Map> tlist01 = cdao.TotalRatioExMu();
			System.out.println(tlist01);
			List<Map> tlist02 = cdao.TotalRatioExPart01();
			System.out.println(tlist02);
			List<Map> tlist03 = cdao.TotalRatioExPart02();
			System.out.println(tlist03);
			mav.addObject("tlist",tlist01);
			mav.addObject("tlist",tlist02);
			mav.addObject("tlist",tlist03);
			mav.addObject("title","TotalExercise");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/TotalExercise");
		return mav;
	}
	
	@RequestMapping(path="/piedata03", produces="application/json;charset=utf-8")
	@ResponseBody
	public String piedataJSONHandle03(HttpSession session) throws JsonProcessingException {
		List<Map> tlist = cdao.TotalRatioExMu();
		session.setAttribute("tlist", tlist);
		List json = new ArrayList<>();
		for(Map m : tlist) {
			Object[] ar = new Object[] {m.get("EXMU"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	@RequestMapping(path="/nullbardata1", produces="application/json;charset=utf-8")
	@ResponseBody
	public String nullbardataJSONHandle01(HttpSession session) throws JsonProcessingException {
		List<Map> nlist01 = cdao.nullExPart();
		session.setAttribute("nlist01", nlist01);
		List json = new ArrayList<>();
		List<String> ctg =new ArrayList<>(Arrays.asList("걷기","달리기","줄넘기","수영","자전거"));
		json.add(new Object[] {"유산소 운동 종류", "운동 횟수"});
		for(Map m : nlist01) {
			ctg.remove(m.get("EXPART"));
			Object[] ar = new Object[] {m.get("EXPART"),m.get("CNT")};
			json.add(ar);
		}
		for(String m : ctg) {
			Object[] ar = new Object[] {m , 0};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	@RequestMapping(path="/nullbardata2", produces="application/json;charset=utf-8")
	@ResponseBody
	public String testbardataJSONHandle02(HttpSession session) throws JsonProcessingException {
		List<Map> testlist02 = cdao.nulllExPart();
		session.setAttribute("testlist02", testlist02);
		List json = new ArrayList<>();
		List<String> ctg =new ArrayList<>(Arrays.asList("가슴","어깨","등","허리","위팔 앞", "위팔 뒤","아래팔","복부","허벅지 앞","허벅지 뒤"));
		json.add(new Object[] {"근력 운동 종류", "운동 횟수"});
		for(Map m : testlist02) {
			ctg.remove(m.get("EXPART"));
			Object[] ar = new Object[] {m.get("EXPART"),m.get("CNT")};
			json.add(ar);
		}
		for(String m : ctg) {
			Object[] ar = new Object[] {m , 0};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	@RequestMapping(path="/totalbardata1", produces="application/json;charset=utf-8")
	@ResponseBody
	public String totalbardataJSONHandle01(HttpSession session) throws JsonProcessingException {
		List<Map> tlist01 = cdao.TotalRatioExPart01();
		session.setAttribute("tlist01", tlist01);
		List json = new ArrayList<>();
		List<String> ctg =new ArrayList<>(Arrays.asList("걷기","달리기","줄넘기","수영","자전거"));
		json.add(new Object[] {"유산소 운동 종류", "운동 횟수"});
		for(Map m : tlist01) {
			ctg.remove(m.get("EXPART"));
			Object[] ar = new Object[] {m.get("EXPART"),m.get("CNT")};
			json.add(ar);
		}
		for(String m : ctg) {
			Object[] ar = new Object[] {m , 0};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	@RequestMapping(path="/totalbardata2", produces="application/json;charset=utf-8")
	@ResponseBody
	public String totaldataJSONHandle02(HttpSession session) throws JsonProcessingException {
		List<Map> tlist02 = cdao.TotalRatioExPart02();
		session.setAttribute("tlist02", tlist02);
		List json = new ArrayList<>();
		List<String> ctg =new ArrayList<>(Arrays.asList("가슴","어깨","등","허리","위팔 앞", "위팔 뒤","아래팔","복부","허벅지 앞","허벅지 뒤"));
		json.add(new Object[] {"근력 운동 종류", "운동 횟수"});
		for(Map m : tlist02) {
			ctg.remove(m.get("EXPART"));
			Object[] ar = new Object[] {m.get("EXPART"),m.get("CNT")};
			json.add(ar);
		}
		for(String m : ctg) {
			Object[] ar = new Object[] {m , 0};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	//chart main
	@RequestMapping("/mychart")
	public ModelAndView chart06Handle(HttpSession session) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
			String id = (String)(((Map)session.getAttribute("auth")).get("ID"));
			List<Map> list01 = cdao.MyRatioExMu(id);
			System.out.println(list01);
			List<Map> list02 = cdao.MyOxeygenExPart(id);
			System.out.println(list02);
			List<Map> list03 = cdao.MyMucsleExPart(id);
			System.out.println(list03);
			mav.addObject("list01",list01);
			mav.addObject("list02",list02);
			mav.addObject("list03",list03);
			mav.addObject("title","내 운동 분석 차트");
			mav.addObject("nav", "chart/chartnav");
			mav.addObject("section","chart/mychart");
		return mav;
	}
	
	@RequestMapping(path="/mypiedata", produces="application/json;charset=utf-8")
	@ResponseBody
	public String MypiedataJSONHandle(HttpSession session) throws JsonProcessingException {
		String id = (String)(((Map)session.getAttribute("auth")).get("ID"));
		List<Map> mlist01 = cdao.MyRatioExMu(id);
		session.setAttribute("mlist01", mlist01);
		List json = new ArrayList<>();
		for(Map m : mlist01) {
			Object[] ar = new Object[] {m.get("EXMU"),m.get("CNT")};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	@RequestMapping(path="/bardata1", produces="application/json;charset=utf-8")
	@ResponseBody
	public String bardataJSONHandle01(HttpSession session) throws JsonProcessingException {
		String id = (String)(((Map)session.getAttribute("auth")).get("ID"));
		List<Map> mylist02 = cdao.MyOxeygenExPart(id);
		session.setAttribute("mylist02", mylist02);
		List json = new ArrayList<>();
		List<String> ctg =new ArrayList<>(Arrays.asList("걷기","달리기","줄넘기","수영","자전거"));
		json.add(new Object[] {"유산소 운동 종류", "운동 횟수"});
		for(Map m : mylist02) {
			ctg.remove(m.get("EXPART"));
			Object[] ar = new Object[] {m.get("EXPART"),m.get("CNT")};
			json.add(ar);
		}
		for(String m : ctg) {
			Object[] ar = new Object[] {m , 0};
			json.add(ar);
		}
		return mapper.writeValueAsString(json);
	}
	
	@RequestMapping(path="/bardata2", produces="application/json;charset=utf-8")
	@ResponseBody
	public String bardataJSONHandle02(HttpSession session) throws JsonProcessingException {
		String id = (String)(((Map)session.getAttribute("auth")).get("ID"));
		List<Map> mylist03 = cdao.MyMucsleExPart(id);
		session.setAttribute("mylist03", mylist03);
		List json = new ArrayList<>();
		List<String> ctg =new ArrayList<>(Arrays.asList("가슴","어깨","등","허리","위팔 앞", "위팔 뒤","아래팔","복부","허벅지 앞","허벅지 뒤"));
		json.add(new Object[] {"근력 운동 종류", "운동 횟수"});
	
		for(Map m : mylist03) {
			ctg.remove(m.get("EXPART"));
			Object[] ar = new Object[] {m.get("EXPART"),m.get("CNT")};
			json.add(ar);
		}
		for(String m : ctg) {
			Object[] ar = new Object[] {m , 0};
			json.add(ar);
		}
		
		return mapper.writeValueAsString(json);
	}
}

