package org.project.health.model;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository

public class RecommendDao {
	@Autowired
	SqlSessionTemplate template;
	
	public Map findNum(String no) {
		return template.selectOne("recommend.findNum", no);
	}
}
