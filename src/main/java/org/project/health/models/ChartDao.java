package org.project.health.models;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChartDao {
	@Autowired
	SqlSessionTemplate sql;
	
	public List<Map> MyRatioExMu(){
		return sql.selectList("exercise.MyRatioExMu");
	}
	
	public List<Map> MyRatioExPart(){
		return sql.selectList("exercise.MyRatioExPart");
	}
}
