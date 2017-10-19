package org.project.health.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	@Autowired
	SqlSessionTemplate template;
	
	public List<Map> readAll(Map map){
		return template.selectList("board.readAll", map);
	}
	
	public Map readOne(String no) {
		template.update("board.countUp", no);
		return template.selectOne("board.readOne", no);
	}
	
	public int addOne(Map map) {
		return template.insert("board.addOne", map);
	}
	
	public int countAll() {
		return template.selectOne("board.countAll");
	}
	
	public List<Map> search(Map map){
		return template.selectList("board.search", map);
	}
	
	public int countSearch(Map map) {
		return template.selectOne("board.countSearch", map);
	}
	
	public int addRecommend(Map map) {
		try {
			template.insert("board.addRecommend", map);
			return template.update("board.recommendUp", map);
		}catch(Exception e) {
			return 0;
		}
	}
}
