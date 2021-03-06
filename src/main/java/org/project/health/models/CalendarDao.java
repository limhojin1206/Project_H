package org.project.health.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarDao {
	@Autowired
	SqlSessionTemplate template;
	
	public List<Map> readAll(String id){
		template.update("calendar.update");
		return template.selectList("calendar.readAll", id);
	}
	
	public int addOne(Map map) {
		return template.insert("calendar.addOne", map);
	}
	
	public Map readOne(String no) {
		return template.selectOne("calendar.readOne", no);
	}
	
	public int deleteOne(String no) {
		return template.delete("calendar.deleteOne", no);
	}
	
	public int editOne(Map map) {
		return template.update("calendar.editOne", map);
	}
	
	public int success(String no) {
		return template.update("calendar.success", no);
	}
	
	public int addList(Map map) {
		return template.insert("calendar.addList", map);
	}
	
	public Map checkList(Map map) {
		return template.selectOne("calendar.checkList", map);
	}
	
	public List<Map> exList(String id){
		return template.selectList("calendar.exList", id);
	}
	
}
