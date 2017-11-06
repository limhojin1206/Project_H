<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<style type="text/css">
#details0, #details1, #details2, #details3 {
	visibility: hidden;
	background: #FFFF7F;
	border: solid 1px;
	width: 350px;
	padding: 5px;
	font-size: smaller;
	position: absolute;
	top: 250px;
}
</style>

<!-- load Google AJAX API -->
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript">
	//load thee Google Visualization API and the chart
	google.load('visualization', '1', {'packages' : [ 'columnchart' ]});

	//set callback
	google.setOnLoadCallback(createChart);

	//callback function
	function createChart() {
		var str;
	  	$.ajax({
			"url" : "/chart/chart",
			"async" : false
		}).done(function(obj){
		str = obj;
		});
		//create data table object
		var dataTable = new google.visualization.DataTable(str);

		//define options for visualization
		var options = {
			width : 400,
			height : 240,
			is3D : true,
			title : 'EXPORT Ratio'
		};

		//instantiate our chart objects
		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart'));


		//draw our chart
		chart.draw(dataTable, options);

		//register callbacks
		google.visualization.events.addListener(chart, 'onmouseover',
				showDetails);
		google.visualization.events.addListener(chart, 'onmouseout',
				hideDetails);
	};

</script>
</head>


<body>
	<!--Div for our chart -->
	<div align="center">
		<h3>나의 운동 비율 분석 차트표</h3>
		<c:forEach var="t" items="${list }" varStatus="vs">
		${t.EXPART } : ${t.CNT } <c:if test="${!vs.last }">|</c:if>
		</c:forEach>

		<div id="chart" style="width: 700px; height: 500px"></div>
		<br /> <small>my exercise ratio chart</small>
	</div>

	<!--Divs for our messages -->
	<div id="details0">These are the details for Q1...</div>
	<div id="details1">Here you have the numbers for Q2...</div>
	<div id="details2">Explanations for the third quarter...</div>
	<div id="details3">Q4 was as expected...</div>

</body>

</html>