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
	
	public List<Map> idcheck(String map) {
		return sql.selectList("member.idcheck", map);
	}
	public List<Map> emailcheck(String map) {
		return sql.selectList("member.emailcheck", map);
	}	

	// 로그인
	public int login (Map map) {
		return sql.selectOne("member.login", map);
	} 
	
	public Map authsetting (Map map ) {
		return sql.selectOne("member.authsetting", map);
	}
	
	// 친구 리스트
	public List<Map> friendList(String id){
		return sql.selectList("member.friendlist", id);
	}
	
	//프로필
	public Map getDetail (Map map) {
		return sql.selectOne("member.getDetail", map);
	}
	//프로필 사진
	public Map prePic(Map map) {
		return sql.selectOne("my.prePic", map);
	}
	//사진변경
	public int addPic(Map map) {
		return sql.insert("my.addPic", map);
	}
	
}
