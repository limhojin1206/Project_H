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
	
	public List<Map> TotalRatioExMu(){
		return sql.selectList("exercise.TotalRatioExMu");
	}
	
	public List<Map> TotalRatioExPart01(){
		return sql.selectList("exercise.TotalRatioExPart01");
	}
	
	public List<Map> TotalRatioExPart02(){
		return sql.selectList("exercise.TotalRatioExPart02");
	}
	
	public List<Map> MyRatioExMu(){
		return sql.selectList("exercise.MyRatioExMu");
	}
	
	public List<Map> MyOxeygenExPart(){
		return sql.selectList("exercise.MyOxeygenExPart");
	}
	
	public List<Map> MyMucsleExPart(){
		return sql.selectList("exercise.MyMucsleExPart");
	}

}
