package org.project.health.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendDao {

	@Autowired
	SqlSessionTemplate template;
	
	// 친구정보
	public Map friendinfo(Object obj) {
		return template.selectOne("friend.friendinfo", obj);
	}
	
	// 친구 요청
	public int send(Map map) {
		return template.insert("friend.send", map);
	}
	
	// 친구 요청 리스트
	public int mftotalCount(Map map) {
		return template.selectOne("friend.mftotalCount",map);
	}
	public List<Map> mfviewpage(Map map){
		return template.selectList("friend.mfviewpage",map);
	}
	
	// 친구 동의
	public int agreefriend(Map map) {
		int rst = 0;
		rst += template.insert("friend.agree1friend", map);
		rst += template.insert("friend.agree2friend", map);
		return rst;
	}
	
	// 친구 취소
	public int endfriend(Map map) {
		int rst = 0;
		System.out.println(map.toString());
		rst += template.delete("friend.end1friend", map);
		rst += template.delete("friend.end2friend", map);
		return rst;
	}
	
	// 친구 리스트
	public int ftotalCount(Map map) {
		return template.selectOne("friend.ftotalCount",map);
	}
	public List<Map> fviewpage(Map map){
		return template.selectList("friend.fviewpage",map);
	}
	
	// 내친구 친구 검색 리스트
	public List<Map> myfriendlist(Map map){
		return template.selectList("friend.myfriendlist", map);
	}
	
	// 페이지 유틸
	public int totalCount(Map map) {
		return template.selectOne("friend.totalCount",map);
	}
	public List<Map> viewpage(Map map){
		return template.selectList("friend.viewpage",map);
	}
	
	// 회원 검색
	public List<Map> searchlist(Map map){
		return template.selectList("friend.searchlist", map);
	}
	public List<Map> searchfriendlist(Map map){
		return template.selectList("friend.searchfriendlist", map);
	}
	
	
	// 요청 메세지 삭제
	public int deletemsg(Map map) {
		return template.delete("friend.deletemsg", map);
	}
	
	// 요청 메세지 답장
	public int deleteremsg(Map map) {
		return template.delete("friend.deleretemsg", map);
	}
	
	// 친구 인지 확인
	public int existfriend(Map map) {
		return template.selectOne("friend.existfriend",map);
	}
	
	// 친구 요청 보냈는지 확인
	public int existmakefriend(Map map) {
		return template.selectOne("friend.existmakefriend",map);
	}
	
}
