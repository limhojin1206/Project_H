package org.project.health.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
	@Autowired
	RecommendDao mdao;

	@GetMapping("/content")
	public void contentHandle(HttpServletRequest request) {

		Connection.Response response = null;
		try {
			response = Jsoup.connect("http://terms.naver.com/entry.nhn?docId=938878&cid=51030&categoryId=51030")
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
		Element c01 = dc.getElementsByClass("size_ct_v2").get(0);
		Elements c02 = c01.getElementsByClass("att_type");
		Elements tr01 = c02.select("tr");
		Elements th01 = tr01.select("th");
		for (int i = 0; i < tr01.size(); i++) {
			Element span01 = th01.select("span").get(i);
			String str01 = span01.text();
			System.out.println(str01);
			
			Element td01 = tr01.select("td").get(i);
			String str02 = td01.text();
			System.out.println(str02);
		}
		System.out.println("============================================================");
		
		Element c03 = dc.getElementsByClass("tmp_agenda").get(0);
		Element h01 = c03.select("h3").get(0);
		String str03 = h01.text();
		System.out.println(str03);
		System.out.println("============================================================");
		
		Elements ol01 = c03.select("ol");
		Elements li01 = ol01.select("li");
		for (int i = 0; i < li01.size(); i++) {
			Element a01 = li01.select("a").get(i);
			String str04 = a01.text();
			System.out.println(str04);
		}
		System.out.println("============================================================");
		
		Element h02 = dc.getElementsByClass("stress").get(0);
		String str05 = h02.text();
		System.out.println(str05);
		Element p01 = dc.getElementsByClass("txt").get(0);
		String str06 = p01.text();
		System.out.println(str06);
		Element h03 = dc.getElementsByClass("stress").get(1);
		String str07 = h03.text();
		System.out.println(str07);
		
		/*
		Element h04 = dc.getElementsByClass("stress").get(2);
		String str08 =h04.prepend(html).text();
		System.out.println(str08);
		
		for(int i=1;i<5;i++) {
			if(str08.equals("���� ����")) {
				Element p02 = dc.getElementsByClass("txt").get(i);
				String str09 = p02.text();
				System.out.println(str09);
			}else {
				String str09 = "";
				System.out.println(str09);
			}
		}
		System.out.println("============================================================");
		/*
		Element h04 = dc.getElementsByClass("stress").get(2);
		String str09 = h04.text();
		System.out.println(str09);
		for(int i=5;i<9;i++) {
			if(dc.getElementsByClass("txt").get(i)==null) {
				String str10 ="";
				System.out.println(str10);
			}else if(dc.getElementsByClass("txt").get(i) ){
				Element p03 = dc.getElementsByClass("txt").get(i);
				String str10 = p03.text();
				System.out.println(str10);
			}
		}
		
		Element h05 = dc.getElementsByClass("stress").get(3);
		String str11 = h05.text();
		System.out.println(str11);
		*/
		

	}

}
