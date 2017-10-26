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

	public List<Map> friendList(String id){
		return sql.selectList("member.friendlist", id);
	}
	
	//
	public int join (Map map) {
		int rst = 0;
		rst += sql.insert("member.join", map);
		rst += sql.insert("member.addDetail",map);
		return rst;
	}
	
	public Map getDetail (Map map) {
		return sql.selectOne("member.getDetail", map);
	}
	
	
	public List<Map> countByGender() {
		return sql.selectList("member.countByGender");
	}
	
	public Map login (Map map) {
		return sql.selectOne("member.login", map);
	} 
	
	
	public List<Map> searchById(String id) {
		return sql.selectList("member.searchById", id);
	}
	
	
	//
	public List<Map> idcheck(String map) {
		return sql.selectList("member.idcheck", map);
	}
	//
	public List<Map> emailcheck(String map) {
		return sql.selectList("member.emailcheck", map);
	}
	
	
	public int addPic(Map map) {
		return sql.selectOne("my.addPic", map);
	}
	
	public List<Map> prePic(Map map) {
		return sql.selectList("my.prePic", map);
	}
	
	
	
	public boolean addOne(Map map) {
		sql.insert("member.addBasic", map);
		sql.insert("member.addDetail", map);
		return true;
	}

	public int existOne(Map map) {
		return sql.selectOne("member.checkByIdmailAndPass", map);
	}
	
	public HashMap readOneByIdOrEmail(String idmail) {
		return sql.selectOne("member.readOneByIdOrEmail", idmail);
	}

	
	public HashMap readOneById(String id) {
		return sql.selectOne("member.readOneById", id);
	}

	public int addProfile(Map map) {
		return sql.insert("member.addProfile", map);
	}
}
