<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
	<h3>나의 운동 비율 분석 차트표 </h3>
	<c:forEach var="t" items="${list }" varStatus="vs">
		${t.EXMU } : ${t.CNT } <c:if test="${!vs.last }">|</c:if>
	</c:forEach>
	
	<div id="chartV" style="width:400px; height:400px">
	</div>
	<small>my exercise ratio chart</small>
</div>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	google.charts.load("current", {"packages":["corechart"]});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {

		var data = new google.visualization.DataTable();
		data.addColumn('string', 'exmu');
		data.addColumn('number', 'count');
		$.ajax({
			"url" : "/chart/piedata1",
			"async" : false
		}).done(function(obj){
			data.addRows(obj);
			
		});
	 
	 var options = {
			 
			    "title" : "나의 운동비율",
			    "sliceVisibilityThreshold": .2,
				"is3D" : true
			  };
	 
	  var chart = new google.visualization.PieChart(document.getElementById('chartV'));
  	  chart.draw(data, options);
}
</script>