package org.project.health.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.tiles.definition.pattern.PrefixedPatternDefinitionResolver;
import org.apache.tiles.request.Request;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.project.health.models.RecommendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
	@Autowired
	RecommendDao rdao;

	@GetMapping("/view")
	public ModelAndView contentHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("title", "view page");
		mav.addObject("section", "recommend/view");
		return mav;
	}

	@RequestMapping("/content/{vno}")
	public ModelAndView recommemdHandle(HttpServletRequest request, @PathVariable String vno) {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("title", "추천운동법");
		mav.addObject("section", "recommend/content");
		System.out.println(vno);
		
		if(vno.equals("938862")) {
		System.out.println("벤치프레스 - 바벨,플랫");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140231496_7KYY9TTA3.jpg/WEIGHT_022_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140231735_Q8474XZUK.jpg/WEIGHT_022_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140232467_GRX4YV3ZW.jpg/WEIGHT_022_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140232555_RYINP07IT.jpg/WEIGHT_022_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);
		
		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(7);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(8);
		String str26 = p09.text();
		request.setAttribute("str26", str26);

		return mav;

		}else if(vno.equals("938868")) {
		System.out.println("플라이 - 덤벨,플랫");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0); 
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140249083_CU6ZH267J.jpg/WEIGHT_029_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140250525_Z2ZJOURET.jpg/WEIGHT_029_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140250639_15IVRFJ2W.jpg/WEIGHT_029_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140251585_4MRSMLGB3.jpg/WEIGHT_029_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(7);
		String str26 = p09.text();
		request.setAttribute("str26", str26);
		Element p10 = dc.getElementsByClass("txt").get(8);
		String str27 = p10.text();
		request.setAttribute("str27", str27);
		Element p11 = dc.getElementsByClass("txt").get(9);
		String str28 = p11.text();
		request.setAttribute("str28", str28);
		return mav;
		
		}else if(vno.equals("938847")){
		System.out.println("벤치 프레스 - 바벨, 인클라인");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);

		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140218766_NR16EFODS.jpg/WEIGHT_024_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140219112_9XKK8JX0H.jpg/WEIGHT_024_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140220218_O3XJ8O29B.jpg/WEIGHT_024_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140221087_YK2TFGFWD.jpg/WEIGHT_024_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(7);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(8);
		String str26 = p09.text();
		request.setAttribute("str26", str26);
		Element p10 = dc.getElementsByClass("txt").get(9);
		String str27 = p10.text();
		request.setAttribute("str27", str27);
		Element p11 = dc.getElementsByClass("txt").get(10);
		String str28 = p11.text();
		request.setAttribute("str28", str28);
		return mav;
		
		}else if(vno.equals("938876")){
		System.out.println("숄더 프레스 - 덤벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);

		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140309278_9FDD7DIJ2.jpg/WEIGHT_040_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140309403_ABEE3RP51.jpg/WEIGHT_040_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140310207_Y0KZGR067.jpg/WEIGHT_040_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140310335_YXDBMA9PZ.jpg/WEIGHT_040_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(7);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(8);
		String str26 = p09.text();
		request.setAttribute("str26", str26);
		return mav;
		
		}else if(vno.equals("938875")) {
		System.out.println("비하인드 넥 프레스 - 바벨");
			Connection.Response response = null;
			try {
				response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
						.method(Connection.Method.GET).execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Document dc = null;
			try {
				dc = response.parse();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("content");
			Element c00 = dc.getElementsByClass("section_wrap").get(0);
			Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
			Element c02 = c01.getElementsByClass("att_type").get(0);
			Elements tr01 = c02.select("tr");
			Elements th01 = tr01.select("th");
			Element head00 = c00.select("h2").get(0);
			String str00 = head00.text();
			request.setAttribute("str00", str00);
			Element p00 = c00.select("p").get(1);
			String et = p00.text();
			request.setAttribute("et", et);
			Element span01 = th01.select("span").get(0);
			String str01 = span01.text();
			request.setAttribute("str01", str01);
			Element td01 = tr01.select("td").get(0);
			String str02 = td01.text();
			request.setAttribute("str02", str02);
			Element span02 = th01.select("span").get(1);
			String str03 = span02.text();
			request.setAttribute("str03", str03);
			Element td02 = tr01.select("td").get(1);
			String str04 = td02.text();
			request.setAttribute("str04", str04);
			Element span03 = th01.select("span").get(2);
			String str05 = span03.text();
			request.setAttribute("str05", str05);
			Element td03 = tr01.select("td").get(2);
			String str06 = td03.text();
			request.setAttribute("str06", str06);
			Element span04 = th01.select("span").get(3);
			String str07 = span04.text();
			request.setAttribute("str07", str07);
			Element td04 = tr01.select("td").get(3);
			String str08 = td04.text();
			request.setAttribute("str08", str08);

			Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
			Element h01 = c03.select("h3").get(0);
			String str09 = h01.text();
			request.setAttribute("str09", str09);

			Elements ol01 = c03.select("ol");
			Elements li01 = ol01.select("li");
			Element a01 = li01.select("a").get(0);
			String str10 = a01.text();
			request.setAttribute("str10", str10);
			Elements li02 = ol01.select("li");
			Element a02 = li02.select("a").get(1);
			String str11 = a02.text();
			request.setAttribute("str11", str11);
			Elements li03 = ol01.select("li");
			Element a03 = li03.select("a").get(2);
			String str12 = a03.text();
			request.setAttribute("str12", str12);
			Elements li04 = ol01.select("li");
			Element a04 = li04.select("a").get(3);
			String str13 = a04.text();
			request.setAttribute("str13", str13);

			Element h02 = dc.getElementsByClass("stress").get(0);
			String str14 = h02.text();
			request.setAttribute("str14", str14);
			Element p01 = dc.getElementsByClass("txt").get(0);
			String str15 = p01.text();
			request.setAttribute("str15", str15);
			Element h03 = dc.getElementsByClass("stress").get(1);
			String str16 = h03.text();
			request.setAttribute("str16", str16);
			
			Element p02 = dc.getElementsByClass("txt").get(1);
			String str17 = p02.text();
			request.setAttribute("str17", str17);
			Element p03 = dc.getElementsByClass("txt").get(2);
			String str18 = p03.text();
			request.setAttribute("str18", str18);
			Element p04 = dc.getElementsByClass("txt").get(3);
			String str19 = p04.text();
			request.setAttribute("str19", str19);
			Element p05 = dc.getElementsByClass("txt").get(4);
			String str20 = p05.text();
			request.setAttribute("str20", str20);
			
			String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140305076_9PTB10GQS.jpg/WEIGHT_039_pic_1.jpg?type=m4500_4500_fst&wm=N";
			request.setAttribute("img01", img01);
			String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140305570_BSXRUC036.jpg/WEIGHT_039_pic_2.jpg?type=m4500_4500_fst&wm=N";
			request.setAttribute("img02", img02);
			String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140305904_SJYFGRLV5.jpg/WEIGHT_039_pic_3.jpg?type=m4500_4500_fst&wm=N";
			request.setAttribute("img03", img03);
			String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140306573_I13T2BU0U.jpg/WEIGHT_039_pic_4.jpg?type=m4500_4500_fst&wm=N";
			request.setAttribute("img04", img04);

			Element h04 = dc.getElementsByClass("stress").get(2);
			String str21 = h04.text();
			request.setAttribute("str21", str21);
			Element p06 = dc.getElementsByClass("txt").get(5);
			String str22 = p06.text();
			request.setAttribute("str22", str22);
			Element p07 = dc.getElementsByClass("txt").get(6);
			String str23 = p07.text();
			request.setAttribute("str23", str23);
			Element p10 = dc.getElementsByClass("txt").get(7);
			String str29 = p10.text();
			request.setAttribute("str29", str29);

			Element h05 = dc.getElementsByClass("stress").get(3);
			String str24 = h05.text();
			request.setAttribute("str24", str24);
			Element p08 = dc.getElementsByClass("txt").get(8);
			String str25 = p08.text();
			request.setAttribute("str25", str25);
			Element p09 = dc.getElementsByClass("txt").get(9);
			String str26 = p09.text();
			request.setAttribute("str26", str26);
			Element p11 = dc.getElementsByClass("txt").get(10);
			String str27 = p11.text();
			request.setAttribute("str27", str27);
			return mav;
			
		}else if(vno.equals("938878")) {
		System.out.println("래터럴 레이즈 - 덤벨");
			Connection.Response response = null;
			try {
				response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
						.method(Connection.Method.GET).execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Document dc = null;
			try {
				dc = response.parse();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("content");
			Element c00 = dc.getElementsByClass("section_wrap").get(0);
			Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
			Element c02 = c01.getElementsByClass("att_type").get(0);
			Elements tr01 = c02.select("tr");
			Elements th01 = tr01.select("th");
			Element head00 = c00.select("h2").get(0);
			String str00 = head00.text();
			request.setAttribute("str00", str00);
			Element p00 = c00.select("p").get(1);
			String et = p00.text();
			request.setAttribute("et", et);
			Element span01 = th01.select("span").get(0);
			String str01 = span01.text();
			request.setAttribute("str01", str01);
			Element td01 = tr01.select("td").get(0);
			String str02 = td01.text();
			request.setAttribute("str02", str02);
			Element span02 = th01.select("span").get(1);
			String str03 = span02.text();
			request.setAttribute("str03", str03);
			Element td02 = tr01.select("td").get(1);
			String str04 = td02.text();
			request.setAttribute("str04", str04);
			Element span03 = th01.select("span").get(2);
			String str05 = span03.text();
			request.setAttribute("str05", str05);
			Element td03 = tr01.select("td").get(2);
			String str06 = td03.text();
			request.setAttribute("str06", str06);
			Element span04 = th01.select("span").get(3);
			String str07 = span04.text();
			request.setAttribute("str07", str07);
			Element td04 = tr01.select("td").get(3);
			String str08 = td04.text();
			request.setAttribute("str08", str08);

			Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
			Element h01 = c03.select("h3").get(0);
			String str09 = h01.text();
			request.setAttribute("str09", str09);

			Elements ol01 = c03.select("ol");
			Elements li01 = ol01.select("li");
			Element a01 = li01.select("a").get(0);
			String str10 = a01.text();
			request.setAttribute("str10", str10);
			Elements li02 = ol01.select("li");
			Element a02 = li02.select("a").get(1);
			String str11 = a02.text();
			request.setAttribute("str11", str11);
			Elements li03 = ol01.select("li");
			Element a03 = li03.select("a").get(2);
			String str12 = a03.text();
			request.setAttribute("str12", str12);
			Elements li04 = ol01.select("li");
			Element a04 = li04.select("a").get(3);
			String str13 = a04.text();
			request.setAttribute("str13", str13);

			Element h02 = dc.getElementsByClass("stress").get(0);
			String str14 = h02.text();
			request.setAttribute("str14", str14);
			Element p01 = dc.getElementsByClass("txt").get(0);
			String str15 = p01.text();
			request.setAttribute("str15", str15);
			Element h03 = dc.getElementsByClass("stress").get(1);
			String str16 = h03.text();
			request.setAttribute("str16", str16);

			Element p02 = dc.getElementsByClass("txt").get(1);
			String str17 = p02.text();
			request.setAttribute("str17", str17);
			Element p03 = dc.getElementsByClass("txt").get(2);
			String str18 = p03.text();
			request.setAttribute("str18", str18);
			Element p04 = dc.getElementsByClass("txt").get(3);
			String str19 = p04.text();
			request.setAttribute("str19", str19);
			
			String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140314682_ZPHJ2KRDY.jpg/WEIGHT_043_pic_1.jpg?type=m4500_4500_fst&wm=N";
			request.setAttribute("img01", img01);
			String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140314928_9B0ESJLCS.jpg/WEIGHT_043_pic_2.jpg?type=m4500_4500_fst&wm=N";
			request.setAttribute("img02", img02);
			String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140316103_87AK14RB3.jpg/WEIGHT_043_pic_3.jpg?type=m4500_4500_fst&wm=N";
			request.setAttribute("img03", img03);
			
			Element h04 = dc.getElementsByClass("stress").get(2);
			String str21 = h04.text();
			request.setAttribute("str21", str21);
			Element p06 = dc.getElementsByClass("txt").get(4);
			String str22 = p06.text();
			request.setAttribute("str22", str22);
			Element p07 = dc.getElementsByClass("txt").get(5);
			String str23 = p07.text();
			request.setAttribute("str23", str23);

			Element h05 = dc.getElementsByClass("stress").get(3);
			String str24 = h05.text();
			request.setAttribute("str24", str24);
			Element p08 = dc.getElementsByClass("txt").get(6);
			String str25 = p08.text();
			request.setAttribute("str25", str25);
			return mav;
			
		}else if(vno.equals("938886")){
		System.out.println("컬 - 덤벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140325678_OFJZJWNWS.jpg/WEIGHT_051_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140327176_5U4YL38WI.jpg/WEIGHT_051_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140328352_1KMEVBMKR.jpg/WEIGHT_051_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(4);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(5);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(7);
		String str26 = p09.text();
		request.setAttribute("str26", str26);
		
		return mav;
		
		}else if(vno.equals("938888")){
		System.out.println("프리쳐 컬 - 바벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140334516_GK97G0JHM.jpg/WEIGHT_053_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140335150_2N7P7HUWH.jpg/WEIGHT_053_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140335341_LDZYX3CX6.jpg/WEIGHT_053_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140335536_NYGVKNVK5.jpg/WEIGHT_053_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		return mav;
		
		}else if(vno.equals("938887")){
		System.out.println("해머 컬 - 덤벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140329468_9XGQBN4VA.jpg/WEIGHT_052_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140330382_NQK1J1SLH.jpg/WEIGHT_052_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140330950_FMW60NMWF.jpg/WEIGHT_052_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(4);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(5);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		return mav;
		
		}else if(vno.equals("938892")){
		System.out.println("트라이셉스 익스텐션 - 바벨, 라잉");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140340399_LKQGR4CJV.jpg/WEIGHT_057_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140340520_6B8WX9ILB.jpg/WEIGHT_057_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140344232_XX7E1QNSF.jpg/WEIGHT_057_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(4);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(5);
		String str23 = p07.text();
		request.setAttribute("str23", str23);
		Element p10 = dc.getElementsByClass("txt").get(6);
		String str29 = p07.text();
		request.setAttribute("str29", str29);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(7);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		return mav;

		}else if(vno.equals("938894")){
		System.out.println("트라이셉스 익스텐션 - 덤벨, 오버헤드");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140349307_QE2M3877E.jpg/WEIGHT_059_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140349971_JF811OHBB.jpg/WEIGHT_059_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140350087_K7YENC1GH.jpg/WEIGHT_059_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140350549_Q84ZK6N93.jpg/WEIGHT_059_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);
		Element p10 = dc.getElementsByClass("txt").get(7);
		String str29 = p07.text();
		request.setAttribute("str29", str29);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(8);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		return mav;

		}else if(vno.equals("938897")){
		System.out.println("킥 백 - 덤벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140354711_16BI76VHB.jpg/WEIGHT_062_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140354815_0O3HS26V9.jpg/WEIGHT_062_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140354946_GLEUMOGW3.jpg/WEIGHT_062_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140355036_7Q0PMO25Y.jpg/WEIGHT_062_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);
		Element p10 = dc.getElementsByClass("txt").get(7);
		String str29 = p07.text();
		request.setAttribute("str29", str29);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(8);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		return mav;
		
		}else if(vno.equals("938900")){
		System.out.println("리스트 컬 - 바벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140404383_2EXXXQLGX.jpg/WEIGHT_065_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140404497_OAVV25RFF.jpg/WEIGHT_065_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140405067_QF02M3QW3.jpg/WEIGHT_065_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140405160_KMLAB86T0.jpg/WEIGHT_065_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		return mav;

		}else if(vno.equals("938902")){
		System.out.println("리버스 리스트 컬 - 바벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140409157_PW7DWWHNR.jpg/WEIGHT_067_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140409834_EFZY4C498.jpg/WEIGHT_067_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140410152_7AI8DB36N.jpg/WEIGHT_067_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140410544_2WDHX75CF.jpg/WEIGHT_067_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		return mav;
		
		}else if(vno.equals("938903")){
		System.out.println("리버스 리스트 컬 - 덤벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=" + vno + "&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140410829_GUBA2Y2DH.jpg/WEIGHT_068_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140411163_6NJW35Z32.jpg/WEIGHT_068_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140411569_OUTEF1JXZ.jpg/WEIGHT_068_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		return mav;
		
		}else if(vno.equals("938907")){
		System.out.println("랫 풀 다운 - 머신");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140422798_DZ39YB2GA.jpg/WEIGHT_072_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140422883_1Y30Q68FG.jpg/WEIGHT_072_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140422918_GU2ERJOUC.jpg/WEIGHT_072_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140426669_RVFO77LDK.jpg/WEIGHT_072_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(7);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(8);
		String str26 = p09.text();
		request.setAttribute("str26", str26);

		return mav;
		
		}else if(vno.equals("938910")){
		System.out.println("로우 - 덤벨, 원 암");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140435424_KPNMBT0J3.jpg/WEIGHT_075_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140435615_C7B240RAR.jpg/WEIGHT_075_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140435615_NMN6DQSIJ.jpg/WEIGHT_075_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140435999_W8PX7IHFS.jpg/WEIGHT_075_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(7);
		String str25 = p08.text();
		request.setAttribute("str25", str25);

		return mav;
		
		}else if(vno.equals("938913")){
		System.out.println("풀업 - 어시스티드");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140442291_EMKV9J8JD.jpg/WEIGHT_078_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140442569_RCA5ZOCY9.jpg/WEIGHT_078_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140442710_HJ4BPGT35.jpg/WEIGHT_078_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(4);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(5);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);

		return mav;
		
		}else if(vno.equals("938914")){
		System.out.println("데드리프트 - 바벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140443171_OZJ93AA0F.jpg/WEIGHT_079_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140443173_VOQK0PBFN.jpg/weight_079_pic_2.jpg?type=m4500_4500_fst_n&wm=Y";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140443333_L0150HGRF.jpg/weight_079_pic_3.jpg?type=m4500_4500_fst_n&wm=Y";
		request.setAttribute("img03", img03);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(4);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(5);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);

		return mav;

		}else if(vno.equals("938916")){
		System.out.println("굿모닝 - 바벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140447365_7LPDRIPLP.jpg/WEIGHT_081_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140447467_ZOE6395D5.jpg/weight_081_pic_2.jpg?type=m4500_4500_fst_n&wm=Y";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140447950_UZ0UUB8QP.jpg/weight_081_pic_3.jpg?type=m4500_4500_fst_n&wm=Y";
		request.setAttribute("img03", img03);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(4);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(5);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		return mav;
		
		}else if(vno.equals("938917")){
		System.out.println("백 익스텐션");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140447991_8LZ25Q6T1.jpg/WEIGHT_082_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140448175_C3KPQ1U1R.jpg/WEIGHT_082_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140448435_7J9RRGDEW.jpg/WEIGHT_082_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140449678_JA09RBCI2.jpg/WEIGHT_082_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(7);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(8);
		String str26 = p09.text();
		request.setAttribute("str26", str26);

		return mav;
		
		}else if(vno.equals("938919")){
		System.out.println("크런치");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140453353_T173JF63W.jpg/WEIGHT_084_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140453370_B52RWE1G4.jpg/WEIGHT_084_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140453584_KZYRSINL8.jpg/WEIGHT_084_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140453609_9SM5MNGXC.jpg/WEIGHT_084_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);

		return mav;
		
		}else if(vno.equals("938922")){
		System.out.println("레그 레이즈");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);

		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140501158_7DJU74OKB.jpg/WEIGHT_087_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140501336_RYAG85YP7.jpg/WEIGHT_087_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140501768_320A212NQ.jpg/WEIGHT_087_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		
		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(4);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(5);
		String str25 = p08.text();
		request.setAttribute("str25", str25);

		return mav;
		
		}else if(vno.equals("938925")){
		System.out.println("V업");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140506969_7MBKJYONS.jpg/WEIGHT_090_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140508274_XIUWAHJAU.jpg/WEIGHT_090_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140510591_3F87VPIH0.jpg/WEIGHT_090_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(4);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(5);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(6);
		String str26 = p09.text();
		request.setAttribute("str26", str26);
		Element p10 = dc.getElementsByClass("txt").get(7);
		String str27 = p10.text();
		request.setAttribute("str27", str27);
		
		return mav;
		
		}else if(vno.equals("938933")) {
		System.out.println("스쿼트 - 바벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140528124_0Z7N8RF0D.jpg/WEIGHT_098_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140530206_VXKIU0ZAA.jpg/WEIGHT_098_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140530306_LSWTKQ026.jpg/weight_098_pic_3.jpg?type=m4500_4500_fst_n&wm=Y";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140530401_NOD9J9066.jpg/weight_098_pic_4.jpg?type=m4500_4500_fst_n&wm=Y";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);
		Element p09 = dc.getElementsByClass("txt").get(7);
		String str29 = p09.text();
		request.setAttribute("str29", str29);
		Element p10 = dc.getElementsByClass("txt").get(8);
		String str30 = p10.text();
		request.setAttribute("str30", str30);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(9);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		
		return mav;
		
		}else if(vno.equals("938936")) {
		System.out.println("레그 프레스");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140534111_D7ZJZGES5.jpg/WEIGHT_101_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140534655_F50AHAIKL.jpg/WEIGHT_101_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140535459_1P0VO436Y.jpg/WEIGHT_101_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140535570_BQ6CUZX9I.jpg/WEIGHT_101_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(6);
		String str23 = p07.text();
		request.setAttribute("str23", str23);
		Element p09 = dc.getElementsByClass("txt").get(7);
		String str29 = p09.text();
		request.setAttribute("str29", str29);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(8);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p10 = dc.getElementsByClass("txt").get(9);
		String str26 = p10.text();
		request.setAttribute("str26", str26);
		
		return mav;
		
		}else if(vno.equals("938937")) {
		System.out.println("런지 - 바벨");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str09 = h01.text();
		request.setAttribute("str09", str09);

		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		Element a01 = li01.select("a").get(0);
		String str10 = a01.text();
		request.setAttribute("str10", str10);
		Elements li02 = ol01.select("li");
		Element a02 = li02.select("a").get(1);
		String str11 = a02.text();
		request.setAttribute("str11", str11);
		Elements li03 = ol01.select("li");
		Element a03 = li03.select("a").get(2);
		String str12 = a03.text();
		request.setAttribute("str12", str12);
		Elements li04 = ol01.select("li");
		Element a04 = li04.select("a").get(3);
		String str13 = a04.text();
		request.setAttribute("str13", str13);

		Element h02 = dc.getElementsByClass("stress").get(0); 
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140535683_7J194TI7J.jpg/WEIGHT_102_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140535859_TFYYDP02V.jpg/WEIGHT_102_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140538084_7Y9XBPM5T.jpg/WEIGHT_102_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140538860_59OUGC061.jpg/WEIGHT_102_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(7);
		String str26 = p09.text();
		request.setAttribute("str26", str26);
		Element p10 = dc.getElementsByClass("txt").get(8);
		String str27 = p10.text();
		request.setAttribute("str27", str27);
		
		return mav;
		
		}else if(vno.equals("938940")) {
		System.out.println("레그 컬 - 스탠딩");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);
		
		Element h02 = dc.getElementsByClass("stress").get(0); 
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140544054_Z8CKPK2TS.jpg/WEIGHT_105_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140544097_1EW3YYXBV.jpg/WEIGHT_105_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140547274_0W9A48R6V.jpg/WEIGHT_105_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		return mav;
		
		}else if(vno.equals("938941")){
		System.out.println("레그 컬 - 라잉");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element h02 = dc.getElementsByClass("stress").get(0);
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140547423_1C5BMWROB.jpg/WEIGHT_106_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140547543_H0UYSUZ72.jpg/WEIGHT_106_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140547817_IDG9CXHML.jpg/WEIGHT_106_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(4);
		String str22 = p06.text();
		request.setAttribute("str22", str22);
		Element p07 = dc.getElementsByClass("txt").get(5);
		String str23 = p07.text();
		request.setAttribute("str23", str23);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);

		return mav;

		}else if(vno.equals("938943")) {
		System.out.println("멀티힙");
		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId="+vno+"&cid=51030&categoryId=51030")
					.method(Connection.Method.GET).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document dc = null;
		try {
			dc = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("content");
		Element c00 = dc.getElementsByClass("section_wrap").get(0);
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Element c02 = c01.getElementsByClass("att_type").get(0);
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		Element head00 = c00.select("h2").get(0);
		String str00 = head00.text();
		request.setAttribute("str00", str00);
		Element p00 = c00.select("p").get(1);
		String et = p00.text();
		request.setAttribute("et", et);
		Element span01 = th01.select("span").get(0);
		String str01 = span01.text();
		request.setAttribute("str01", str01);
		Element td01 = tr01.select("td").get(0);
		String str02 = td01.text();
		request.setAttribute("str02", str02);
		Element span02 = th01.select("span").get(1);
		String str03 = span02.text();
		request.setAttribute("str03", str03);
		Element td02 = tr01.select("td").get(1);
		String str04 = td02.text();
		request.setAttribute("str04", str04);
		Element span03 = th01.select("span").get(2);
		String str05 = span03.text();
		request.setAttribute("str05", str05);
		Element td03 = tr01.select("td").get(2);
		String str06 = td03.text();
		request.setAttribute("str06", str06);
		Element span04 = th01.select("span").get(3);
		String str07 = span04.text();
		request.setAttribute("str07", str07);
		Element td04 = tr01.select("td").get(3);
		String str08 = td04.text();
		request.setAttribute("str08", str08);

		Element h02 = dc.getElementsByClass("stress").get(0); 
		String str14 = h02.text();
		request.setAttribute("str14", str14);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str15 = p01.text();
		request.setAttribute("str15", str15);

		Element h03 = dc.getElementsByClass("stress").get(1);
		String str16 = h03.text();
		request.setAttribute("str16", str16);
		Element p02 = dc.getElementsByClass("txt").get(1);
		String str17 = p02.text();
		request.setAttribute("str17", str17);
		Element p03 = dc.getElementsByClass("txt").get(2);
		String str18 = p03.text();
		request.setAttribute("str18", str18);
		Element p04 = dc.getElementsByClass("txt").get(3);
		String str19 = p04.text();
		request.setAttribute("str19", str19);
		Element p05 = dc.getElementsByClass("txt").get(4);
		String str20 = p05.text();
		request.setAttribute("str20", str20);
		
		String img01 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140549892_6QLTA5T9X.jpg/WEIGHT_108_pic_1.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img01", img01);
		String img02 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140550090_05SERCTXQ.jpg/WEIGHT_108_pic_2.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img02", img02);
		String img03 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140550587_BH3QM6RSO.jpg/WEIGHT_108_pic_3.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img03", img03);
		String img04 = "http://dbscthumb.phinf.naver.net/2009_000_1/20120315140552720_T3RVEX314.jpg/WEIGHT_108_pic_4.jpg?type=m4500_4500_fst&wm=N";
		request.setAttribute("img04", img04);

		Element h04 = dc.getElementsByClass("stress").get(2);
		String str21 = h04.text();
		request.setAttribute("str21", str21);
		Element p06 = dc.getElementsByClass("txt").get(5);
		String str22 = p06.text();
		request.setAttribute("str22", str22);

		Element h05 = dc.getElementsByClass("stress").get(3);
		String str24 = h05.text();
		request.setAttribute("str24", str24);
		Element p08 = dc.getElementsByClass("txt").get(6);
		String str25 = p08.text();
		request.setAttribute("str25", str25);
		Element p09 = dc.getElementsByClass("txt").get(7);
		String str26 = p09.text();
		request.setAttribute("str26", str26);
		return mav;
		
		}else {
			return mav;
		}
		
	}
}