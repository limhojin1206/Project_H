package org.project.health.controller.model;


import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	@Autowired
	SqlSessionTemplate sql;

	
	public List<Map> searchById(String id) {
		return sql.selectList("member.searchById", id);
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

	
	public Integer countAllMembers() {
		return sql.selectOne("member.countAllMembers");
	}
}




