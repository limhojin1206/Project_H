package org.project.health.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseDao {
	@Autowired
	SqlSessionTemplate template;
	
	public int addOne(Map map) {
		return template.insert("exercise.addOne", map);
	}
	
	public List<Map> readAll(String parent){
		return template.selectList("exercise.readAll", parent);
	}
}
