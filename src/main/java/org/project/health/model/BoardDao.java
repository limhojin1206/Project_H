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
	
	public List<Map> readAll(){
		return template.selectList("board.readAll");
	}
	
	public Map readOne(String no) {
		return template.selectOne("board.readOne", no);
	}
	
	public int addOne(Map map) {
		return template.insert("board.addOne", map);
	}
}
