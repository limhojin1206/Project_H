package org.project.health.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.project.health.models.BoardDao;
import org.project.health.models.CalendarDao;
import org.project.health.models.ExerciseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import aj.org.objectweb.asm.Type;


@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardDao dao;
	
	@Autowired
	ExerciseDao exdao;
	
	@Autowired
	CalendarDao cdao;
	
	@RequestMapping("/edit/{no}")
	public ModelAndView editGetHandle(@PathVariable String no) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		Map view = dao.readOne(no);
		mav.addObject("title", "수정");
		mav.addObject("section", "board/edit");
		mav.addObject("view", view);
		mav.addObject("nav", "board/boardnav");
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
		ModelAndView mav = new ModelAndView("t_sub_expr");
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
		int pageSize = 10;
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
		mav.addObject("title", "검색결과");
		mav.addObject("section", "board/list");
		mav.addObject("list", list);
		mav.addObject("searchs", searchs);
		mav.addObject("search", search);
		mav.addObject("mode", map);
		mav.addObject("nav", "board/boardnav");
				
		return mav;
	}
	
	@RequestMapping("/list")
	public ModelAndView listHandle(@RequestParam(name="page", defaultValue="1") int page, @RequestParam String bgno) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		List<Map> noticeList = dao.readNotice();
		
		List<Map> recommendList = dao.readRecommend();
		
		int	tot = dao.countAll(bgno);
		int pageSize = 10;
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
        
		Map map = new HashMap();
		map.put("start", (page - 1) * pageSize + 1 );
		map.put("end", page * pageSize);
		map.put("bgno", bgno);
		
		List<Map> list = dao.readAll(map);
		mav.addObject("title", "게시판");
		mav.addObject("section", "board/list");
		mav.addObject("list", list);
		mav.addObject("recommendList", recommendList);
		mav.addObject("noticeList", noticeList);
		mav.addObject("nav", "board/boardnav");
		return mav;
	}
	
	@RequestMapping("/view/{no}")
	public ModelAndView viewHandel(@PathVariable String no, @RequestParam String bgno, HttpSession session) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		Map map = new HashMap();
		map.put("bno", no);
		map.put("id", (String)((Map)session.getAttribute("auth")).get("ID"));
		map.put("bgno", bgno);
		Map view = dao.readOne(no);
		Map pnPage = dao.prevAndNext(map);
		List<Map> check = dao.checkRecommend(map);
		Object exno = view.get("EXNO");
		map.put("exno", exno);
		Map cList = cdao.checkList(map);
		mav.addObject("cList", cList);
		if(exno != null) {
		Map exview = exdao.readOne(exno);
		mav.addObject("exview", exview);
		System.out.println("exview = "+exview);
		}
		System.out.println("view = "+view);
		System.out.println("pnPage = "+pnPage);
		System.out.println("check = "+check);
		System.out.println("map = "+map);
		mav.addObject("view", view);
		mav.addObject("title", "상세보기");
		mav.addObject("section", "board/view");
		mav.addObject("pnPage", pnPage);
		mav.addObject("check", check);
		mav.addObject("bgno", bgno);
		mav.addObject("nav", "board/boardnav");
		return mav;
	}
	
	@PostMapping("/add")
	public ModelAndView addPostHandle(@RequestParam Map map, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/board/list?bgno="+map.get("bgno")+"&page=1");
		map.put("id", (String)((Map)session.getAttribute("auth")).get("ID"));
		System.out.println("test = "+map);
		int rst = dao.addOne(map);
		//mav.addObject("rst", rst);
		return mav;
	}
	
	@GetMapping("/add")
	public ModelAndView addGetHandle(@RequestParam String bgno) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
		mav.addObject("title", "글쓰기");
		mav.addObject("section", "board/add");
		mav.addObject("bgno", bgno);
		mav.addObject("nav", "board/boardnav");
		return mav;
	}
	
	@RequestMapping("/exAdd")
	public ModelAndView exAddGetHandle(@RequestParam String bgno, String no) {
		ModelAndView mav = new ModelAndView("t_sub_expr");
	//	System.out.println("bgno = "+bgno);
	//	System.out.println("no ="+no);
		Map map = exdao.readOne(no);
	//	System.out.println("test = "+map);
		mav.addObject("title", "운동 공유");
		mav.addObject("section", "board/add");
		mav.addObject("bgno", bgno);
		mav.addObject("nav", "board/boardnav");
		mav.addObject("data", map);
		return mav;
	}
}
