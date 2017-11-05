package org.project.health.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.project.health.models.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardDao dao;
	
	
	@RequestMapping("/test")
	public ModelAndView testHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("section", "/test");
		mav.addObject("title", "calender");
		
		return mav;
	}
	
	@RequestMapping("/edit/{no}")
	public ModelAndView editGetHandle(@PathVariable String no) {
		ModelAndView mav = new ModelAndView("t_expr");
		Map view = dao.readOne(no);
		mav.addObject("title", "�� ����");
		mav.addObject("section", "board/edit");
		mav.addObject("view", view);
		
		return mav;
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public String editPostHandle(@RequestParam Map map) {
		int erst = dao.edit(map);
		if(erst == 1) {
			return "YYYY";
		}else {
			return "NNNN";
		}
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
	
	@RequestMapping(path="/recommend", produces="text/plain;charset=utf-8") // application/json;charset=utf-8
	@ResponseBody
	public String recommendHandle(@RequestParam Map map) {
		System.out.println(map);
		int r = dao.addRecommend(map);
		if(r == 1) {
			return "YYYY";
		}else {
			return "NNNN";
		}
	}
	
	@RequestMapping("/search")
	public ModelAndView searchHandle(@RequestParam(name="page", defaultValue="1") int page, @RequestParam Map map) {
		ModelAndView mav = new ModelAndView("t_expr");
		//System.out.println(map);
		
		String search = (String) map.get("search");
		String[] searchs = search.split("\\s");
		String sql = "";
		if(searchs.length > 1) {
			for(int i=1; i<=searchs.length; i++) {
			sql += map.get("type")+" like \'%"+searchs[i-1]+"%\'";
				if(i < searchs.length) {
					sql += " or ";
				}
			}
		}else {
			sql = map.get("type")+" like \'%"+search+"%\'";
		}
		map.put("sql", sql);
		
		//System.out.println(sql);
		
		int tot = dao.countSearch(map);
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
		
		int blockSize = 10;
		int totBlock = (int)Math.ceil(size / blockSize);
        int curBlock = (int)Math.ceil((page-1) / blockSize)+1;
        int blockBegin = (curBlock-1)*blockSize+1;
        int blockEnd = blockBegin+blockSize-1;
        if(blockEnd > tot) blockEnd = tot;
        int prevPage = (page == 1)? 1:(curBlock-1)*blockSize;
        int nextPage = curBlock > totBlock ? (curBlock*blockSize) : (curBlock*blockSize)+1;
        if(nextPage >= tot) nextPage = size;
		mav.addObject("totBlock",totBlock);
		mav.addObject("curBlock",curBlock);
		mav.addObject("blockBegin",blockBegin);
		mav.addObject("blockEnd",blockEnd);
		mav.addObject("prevPage",prevPage);
		mav.addObject("nextPage",nextPage);
		
		//Map map = new HashMap();
		map.put("start", (page - 1) * pageSize + 1 );
		map.put("end", page * pageSize);
		
		//map.put("searchs", searchs);
		List<Map> list = dao.search(map);
		mav.addObject("title", "�˻����");
		mav.addObject("section", "board/list");
		mav.addObject("list", list);
		mav.addObject("searchs", searchs);
		mav.addObject("search", search);
		mav.addObject("mode", map);
				
		return mav;
	}
	
	@RequestMapping("/list")
	public ModelAndView listHandle(@RequestParam(name="page", defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		List<Map> recommendList = dao.readRecommend();
		
		int	tot = dao.countAll();
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
		
		int blockSize = 10;
		int totBlock = (int)Math.ceil(size / blockSize);
		 // *���� �������� ���° ������ ��Ͽ� ���ϴ��� ���
        // (����������-1)/������ ��ϴ���+1
        // 1������ => 1��� (1-1)/10 + 1 => 1
        // 9������ =>     1��� (9-1)/10 + 1 => 1
        // 11������ => 2��� (11-1)/10 + 1 => 2
        // 57������ => 6��� (57-1)/10 + 1 => 6 
        int curBlock = (int)Math.ceil((page-1) / blockSize)+1;
        // *���� ������ ����� ����, �� ��ȣ ���
        // ������ ����� ���۹�ȣ
        // (������-1)*��ϴ���+1
        // 1��� => (1-1)*10 + 1 => 1
        // 2��� => (2-1)*10 + 1 => 11
        // 6��� => (6-1)*10 + 1 => 51
        int blockBegin = (curBlock-1)*blockSize+1;
        // ������ ����� ����ȣ
        // ��Ͻ��۹�ȣ+��ϴ���-1;
        // 1��� => 1+10-1 => 10
        // 2��� => 11+10-1 => 20
        // 6��� => 51+10-1 => 60     
        int blockEnd = blockBegin+blockSize-1;
        // *������ ����� ������ �ʰ����� �ʵ��� ���
        // [����] 61 62 => �̷��� ��� 70������ ������ �ʵ����ϱ� ���ؼ�
        if(blockEnd > tot) blockEnd = tot;
        // *������ ������ �� �̵��� ������ ��ȣ
        int prevPage = (page == 1)? 1:(curBlock-1)*blockSize;
        // *������ ������ �� �̵��� ������ ��ȣ
        int nextPage = curBlock > totBlock ? (curBlock*blockSize) : (curBlock*blockSize)+1;
        // ������ �������� ������ �ʰ����� �ʵ��� ó��
        if(nextPage >= tot) nextPage = size;
		mav.addObject("totBlock",totBlock);
		mav.addObject("curBlock",curBlock);
		mav.addObject("blockBegin",blockBegin);
		mav.addObject("blockEnd",blockEnd);
		mav.addObject("prevPage",prevPage);
		mav.addObject("nextPage",nextPage);
		
        
		Map map = new HashMap();
		map.put("start", (page - 1) * pageSize + 1 );
		map.put("end", page * pageSize);
		
		List<Map> list = dao.readAll(map);
		mav.addObject("recommendList", recommendList);
		mav.addObject("list", list);
		
		mav.addObject("title", "BOARD");
		mav.addObject("nav", "board/boardnav");
		mav.addObject("section", "board/list");
		return mav;
	}
	
	@RequestMapping("/view/{no}")
	public ModelAndView viewHandel(@PathVariable String no) {
		ModelAndView mav = new ModelAndView("t_expr");
		Map view = dao.readOne(no);
		Map pnPage = dao.prevAndNext(no);
		
		Map map = new HashMap();
		map.put("bno", no);
		map.put("id", "qwe");
		List<Map> check = dao.checkRecommend(map);
		mav.addObject("view", view);	
		mav.addObject("title", "�󼼺���");
		mav.addObject("section", "board/view");
		mav.addObject("pnPage", pnPage);
		mav.addObject("check", check);
		return mav;
	}
	
	@PostMapping("/add")
	public ModelAndView addPostHandle(@RequestParam Map map) {
		ModelAndView mav = new ModelAndView("redirect:/board/list?page=1");
		int rst = dao.addOne(map);
		//mav.addObject("rst", rst);
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView addGetHandle() {
		ModelAndView mav = new ModelAndView("t_expr");
		mav.addObject("title", "�۾���");
		mav.addObject("section", "board/add");
		return mav;
	}
	
	@RequestMapping("/submit")
	public void submit(@RequestParam Map param){
		dao.addOne(param);
	    System.out.println("������ ��������:"+param);
	}
}
