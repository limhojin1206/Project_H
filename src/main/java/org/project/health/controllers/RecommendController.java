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
		
		String img01 = "/images/naver18/part010101.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/part010102.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/part010103.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/part010104.jpg";
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
		
		String img01 = "/images/naver18/part010201.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/part010202.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/part010203.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/part010204.jpg";
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
		
		String img01 = "/images/naver18/part010301.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/part010302.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/part010303.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/part010304.jpg";
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
		
		String img01 = "/images/naver18/050101.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/050102.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/050103.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/050104.jpg";
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
			
			String img01 = "/images/naver18/050201.jpg";
			request.setAttribute("img01", img01);
			String img02 = "/images/naver18/050202.jpg";
			request.setAttribute("img02", img02);
			String img03 = "/images/naver18/050203.jpg";
			request.setAttribute("img03", img03);
			String img04 = "/images/naver18/050204.jpg";
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
			
			String img01 = "/images/naver18/050301.jpg";
			request.setAttribute("img01", img01);
			String img02 = "/images/naver18/050302.jpg";
			request.setAttribute("img02", img02);
			String img03 = "/images/naver18/050303.jpg";
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
		
		String img01 = "/images/naver18/040101.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/040102.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/040103.jpg";
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
		
		String img01 = "/images/naver18/040201.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/040202.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/040203.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/040204.jpg";
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
		
		String img01 = "/images/naver18/040301.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/040302.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/040303.jpg";
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
		
		String img01 = "/images/naver18/070101.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/070102.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/070103.jpg";
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
		
		String img01 = "/images/naver18/070201.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/070202.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/070203.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/070204.jpg";
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
		
		String img01 = "/images/naver18/070301.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/070302.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/070303.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/070304.jpg";
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
		
		String img01 = "/images/naver18/080101.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/080102.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/080103.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/080104.jpg";
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
		
		String img01 = "/images/naver18/080201.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/080202.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/080203.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/080204.jpg";
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
		
		String img01 = "/images/naver18/080301.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/080302.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/080303.jpg";
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
		
		String img01 = "/images/naver18/030101.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/030102.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/030103.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/030104.jpg";
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
		
		String img01 = "/images/naver18/030201.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/030202.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/030203.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/030204.jpg";
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
		
		String img01 = "/images/naver18/030301.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/030302.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/030303.jpg";
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
		
		String img01 = "/images/naver18/030401.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/030402.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/030403.jpg";
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
		
		String img01 = "/images/naver18/030501.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/030502.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/030503.jpg";
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
		
		String img01 = "/images/naver18/030601.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/030602.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/030603.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/030604.jpg";
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
		
		String img01 = "/images/naver18/060101.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/060102.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/060103.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/060104.jpg";
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

		String img01 = "/images/naver18/060201.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/060202.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/060203.jpg";
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
		
		String img01 = "/images/naver18/060301.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/060302.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/060303.jpg";
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
		
		String img01 = "/images/naver18/part020101.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/part020102.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/part020103.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/part020104.jpg";
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
		
		String img01 = "/images/naver18/part020201.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/part020202.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/part020203.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/part020204.jpg";
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
		
		String img01 = "/images/naver18/part020301.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/part020302.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/part020303.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/part020304.jpg";
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
		
		String img01 = "/images/naver18/090101.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/090102.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/090103.jpg";
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
		
		String img01 = "/images/naver18/090201.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/090202.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/090203.jpg";
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
		
		String img01 = "/images/naver18/090301.jpg";
		request.setAttribute("img01", img01);
		String img02 = "/images/naver18/090302.jpg";
		request.setAttribute("img02", img02);
		String img03 = "/images/naver18/090303.jpg";
		request.setAttribute("img03", img03);
		String img04 = "/images/naver18/090304.jpg";
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