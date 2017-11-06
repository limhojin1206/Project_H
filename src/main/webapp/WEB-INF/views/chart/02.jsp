<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
	<h3>연령별 비율</h3>
	<c:forEach var="t" items="${list }" varStatus="vs">
		${t.AGE } : ${t.CNT } <c:if test="${!vs.last }">|</c:if>
	</c:forEach>
	
	<div id="chartV" style="width:400px; height:400px">
	</div>
	<small>total age ratio</small>
</div>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	google.charts.load("current", {"packages":["corechart"]});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {

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
			 
			    "title" : "전체 연령 비율",
			    "sliceVisibilityThreshold": .2,
				"is3D" : true

			  };
	  var chart = new google.visualization.PieChart(document.getElementById('chartV'));
  	  chart.draw(data, options);
}
</script>