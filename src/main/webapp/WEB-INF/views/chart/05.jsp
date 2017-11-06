<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
	<h3>나의 운동 비율 분석 차트표 </h3>
	<c:forEach var="t" items="${list }" varStatus="vs">
		${t.EXPART } : ${t.CNT } <c:if test="${!vs.last }">|</c:if>
	</c:forEach>
	
	<div id="chartV" style="width:700px; height:500px">
	</div><br/>
	<small>my exercise ratio chart</small>
</div>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript">

	google.load('visualization', '1', {'packages': ['columnchart']});
	google.setOnLoadCallback (createChart);

	function createChart() {
		var str;
	  	$.ajax({
			"url" : "/chart/bardata2",
			"async" : false
		}).done(function(obj){
		str = obj;
		});
	  	console.log(str);
	  	var dataTable = new google.visualization.DataTable(str);
  		var chart = new google.visualization.ColumnChart(document.getElementById('chartV'));
	  	var options = {
	  	width: 400, 
	  	height: 350, 
	  	is3D: true, 
	  	title: '부위별 운동 분석'};
	  	chart.draw(dataTable, options);
};
</script>