package org.project.health.controller.model;


import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	@Autowired
	SqlSessionTemplate sql;

	public List<Map> countByGender() {
		return sql.selectList("member.countByGender");
	}
	
	
	// 
	public List<Map> searchById(String id) {
		return sql.selectList("member.searchById", id);
	}
	
	
	// �����ͺ��̽� ���
	public boolean addOne(Map map) {
		sql.insert("member.addBasic", map);
		sql.insert("member.addDetail", map);
		return true;
	}

	// id�� email�� �Ӱ�, pass�� ���� �����Ͱ� �ִ��� Ȯ���Ҷ�
	public int existOne(Map map) {
		return sql.selectOne("member.checkByIdmailAndPass", map);
	}

	// id�� email�� �Ӱ�,
	public HashMap readOneByIdOrEmail(String idmail) {
		return sql.selectOne("member.readOneByIdOrEmail", idmail);
	}

	// id�� email�� �Ӱ�, pass�� ���� �����Ͱ� �ִ��� Ȯ���Ҷ�
	public HashMap readOneById(String id) {
		return sql.selectOne("member.readOneById", id);
	}

	public int addProfile(Map map) {
		return sql.insert("member.addProfile", map);
	}

	public Map readLatestProfileById(String id) {
		return sql.selectOne("member.readLatestProfileById", id);
	}

	public List<Map> readAllProfileById(String id) {
		return sql.selectList("member.readAllProfileById", id);
	}

	public List<Map> readAllMemberWithLatestProfile() {
		return sql.selectList("member.readAllMemberWithLatestProfile");
	}

	public List<Map> readSomeMemberWithLatestProfile(Map map) {
		return sql.selectList("member.readSomeMemberWithLatestProfile", map);
	}
	
	public Integer countAllMembers() {
		return sql.selectOne("member.countAllMembers");
	}
}










