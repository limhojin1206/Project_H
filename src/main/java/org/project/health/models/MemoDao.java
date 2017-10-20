package org.project.health.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemoDao {

	@Autowired
	SqlSessionTemplate template;
	
	public int send(Map map) {
		return template.insert("memo.send", map);
	}
	
	public List<Map> totreadReceiveMemo(String id){
		return template.selectList("memo.totreadReceiveMemo",id);
	}
	
	public List<Map> pagereceiveList(Map map){
		return template.selectList("memo.pagereceiveList",map);
	}
	
	
	public List<Map> readSendMemo(String id){
		return template.selectList("memo.readSendMemo",id);
	}
	
	public int deleteMemo(Map map) {
		return template.delete("memo.deleteMemo",map);
	}
	
	////////////////////////////////////////////////////////
	public List<Map> readAll(String id) {
		return template.selectList("memo.readAll", id);
	}
	
	public List<Map> readBlist(Map map) {
		return template.selectList("memo.readBlist", map);
	}
}
