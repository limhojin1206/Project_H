package org.project.health.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyDao {
	
	@Autowired
	SqlSessionTemplate sql;
	
	// 내정보
	public Map myinfo(Object obj) {
		return sql.selectOne("my.myinfo", obj);
	}
	// 정보 수정
	public int update(Map map) { 
		return sql.update("my.update",map);
	}
	public int changepw(Map map) { 
		return sql.update("my.changepw",map);
	}
	// 회원탈퇴 
	public int drop(Object id) { 
		return sql.delete("my.drop",id);
	}
	
	//사진변경
	public int addPic(Map map) {
		return sql.insert("my.addPic", map);
	}

}
