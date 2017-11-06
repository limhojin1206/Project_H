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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {'packages':['bar']});
	google.charts.setOnLoadCallback(drawStuff);

	function drawStuff() {
		var str;
	  	$.ajax({
			"url" : "/chart/bardata",
			"async" : false
		}).done(function(obj){
		str = obj;
		});
	  	console.log(str);
		var data = new google.visualization.arrayToDataTable(str);
  		var options = {
    	width: 550,
    	legend: { position: 'none' },
    	chart: {
      	title: '',
      	subtitle: '' },
    	axes: {
      	x: {
      	0: { side: 'down', label: ''} // Top x-axis.
      	
      }
    },
    bar: { groupWidth: "90%" }
  };
  var chart = new google.charts.Bar(document.getElementById('chartV'));
  chart.draw(data, google.charts.Bar.convertOptions(options));
};
</script>