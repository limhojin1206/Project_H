package org.project.health.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public class ReplyDao {
	@Autowired
	SqlSessionTemplate template;
	
	public int addOne(Map map) {
		return template.insert("reply.addOne", map);
	}
	
	public List<Map> readAll(String bno){
		return template.selectList("reply.readAll", bno);
	}
	
	public int delete(String no) {
		return template.delete("reply.delete", no);
	}
}
