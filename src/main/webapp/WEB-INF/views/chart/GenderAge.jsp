<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br/>
<div class="col-sm-6">
	<div align="center"><h2><b>· 남 / 녀 비율</b></h2>
	<div id="chartGender" style="width:500px; height:500px"></div>
	</div>
</div>
<div class="col-sm-6">	
	<div align="center"><h2><b>· 연령별 비율</b></h2>
	<div id="chartAge" style="width:500px; height:500px"></div>
	</div>
</div>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	google.charts.load("current", {"packages":["corechart"]});
	google.charts.setOnLoadCallback(drawChart01);

	function drawChart01() {

		var data = new google.visualization.DataTable();
		data.addColumn('string', 'gender');
		data.addColumn('number', 'count');
		$.ajax({
			"url" : "/chart/piedata01",
			"async" : false
		}).done(function(obj){
			data.addRows(obj);
			
		});
	 
	 var options = {
			 
			    "title" : "회원 남/녀 성비율",
			    "sliceVisibilityThreshold": .2,
				"is3D" : true
				
			  };
	  var chart = new google.visualization.PieChart(document.getElementById('chartGender'));
  	  chart.draw(data, options);
}
	
	google.charts.load("current", {"packages":["corechart"]});
	google.charts.setOnLoadCallback(drawChart02);

	function drawChart02() {

		var data = new google.visualization.DataTable();
		data.addColumn('string', 'age');
		data.addColumn('number', 'count');
		$.ajax({
			"url" : "/chart/piedata02",
			"async" : false
		}).done(function(obj){
			data.addRows(obj);
			
		});
	 
	 var options = {
			 
			    "title" : "회원전체 연령대 비율",
			    "sliceVisibilityThreshold": .2,
				"is3D" : true
				
			  };
	  var chart = new google.visualization.PieChart(document.getElementById('chartAge'));
  	  chart.draw(data, options);
}
</script>