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
	public List<Map> nullExPart(){
		return sql.selectList("exercise.nullExPart");
	}
	
	public List<Map> nulllExPart(){
		return sql.selectList("exercise.nulllExPart");
	}
	
	public List<Map> TotalRatioExPart02(){
		return sql.selectList("exercise.TotalRatioExPart02");
	}
	
	public List<Map> MyRatioExMu(String id){
		return sql.selectList("exercise.MyRatioExMu", id);
	}
	
	public List<Map> MyOxeygenExPart(String id){
		return sql.selectList("exercise.MyOxeygenExPart", id);
	}
	
	public List<Map> MyMucsleExPart(String id){
		return sql.selectList("exercise.MyMucsleExPart", id);
	}

}
