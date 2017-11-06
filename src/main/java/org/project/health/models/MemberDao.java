package org.project.health.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

	@Autowired
	SqlSessionTemplate sql;

	// 회원가입
	public int join (Map map) {
		return sql.insert("member.join", map);
	}
	
	public int idcheck(String map) {
		return sql.selectOne("member.idcheck", map);
	}
	public int emailcheck(String map) {
		return sql.selectOne("member.emailcheck", map);
	}	

	// 로그인
	public int login (Map map) {
		return sql.selectOne("member.login", map);
	} 
	
	public Map authsetting (Map map ) {
		return sql.selectOne("member.authsetting", map);
	}
	
	public List<Map> countByGender(){
		return sql.selectList("member.countByGender");
	}
	
	public List<Map> countByAge(){
		return sql.selectList("member.countByAge");
	}
}
