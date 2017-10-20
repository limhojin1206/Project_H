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
	
	public int send(Map map) {
		return template.insert("friend.send", map);
	}
	
	public List<Map> totreadReceiveMemo(String id){
		return template.selectList("friend.totreadReceiveMemo",id);
	}
	
	public List<Map> pagereceiveList(Map map){
		return template.selectList("friend.pagereceiveList",map);
	}
	
	public int agreefriend(Map map) {
		int rst = 0;
		rst += template.insert("friend.agree1friend", map);
		rst += template.insert("friend.agree2friend", map);
		return rst;
	}
	
	public List<Map> myfriendlist(String id){
		return template.selectList("friend.myfriendlist", id);
	}
	
	public List<Map> friendList(Map map){
		return template.selectList("friend.friendlist", map);
	}
	
	public int existfriend(Map map) {
		return template.selectOne("friend.existfriend",map);
	}
	
	public int deletemsg(Map map) {
		return template.delete("friend.deletemsg", map);
	}
}
