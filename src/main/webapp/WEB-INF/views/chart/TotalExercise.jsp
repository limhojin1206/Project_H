<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center" style="width: 100%; height:100%">
<div>
	<h2>전체운동 비율 분석 차트표</h2>
	<div id="total01" style="width: 600px; height: 400px"></div>
</div><hr/>

<div class="col-sm-4">
		<h3>유산소운동 비율 분석</h3>
		<div id="total02" style="height:500px"></div>
		<small>oxeygen exercise ratio chart</small>
</div>
<div class="col-sm-8">
		<h3>근력운동 비율 분석</h3>
		<div id="total03" style="height:500px"></div>
		<small>total exercise ratio chart</small>
</div>
</div>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load("current", {"packages":["corechart"]});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {

		var data = new google.visualization.DataTable();
		data.addColumn('string', 'exmu');
		data.addColumn('number', 'count');
		$.ajax({
			"url" : "/chart/piedata03",
			"async" : false
		}).done(function(obj){
			data.addRows(obj);
			
		});
	 
	 var options = {
			 
			    "title" : "전체인원 운동비율",
			    "sliceVisibilityThreshold": .2,
				"is3D" : true
			  };
	 
	  var chart = new google.visualization.PieChart(document.getElementById('total01'));
  	  chart.draw(data, options);
	}
	
	google.charts.load('current', {'packages':['bar']});
	google.charts.setOnLoadCallback(drawStuff01);

	function drawStuff01() {
		var str;
	  	$.ajax({
			"url" : "/chart/totalbardata1",
			"async" : false
		}).done(function(obj){
		str = obj;
		});
		var data = new google.visualization.arrayToDataTable(str);
  		var options = {
    	
    	legend: { position: 'none' },
    	chart: {
      	title: '',
      	subtitle: '' },
    	axes: {
      	x: {
      	0: { side: 'down', label: ''} // Top x-axis.
      	
      }
    },
    bar: { groupWidth: "80%" }
  };
  var chart = new google.charts.Bar(document.getElementById('total02'));
  chart.draw(data, google.charts.Bar.convertOptions(options));
}
	
	google.charts.load('current', {'packages':['bar']});
	google.charts.setOnLoadCallback(drawStuff02);

	function drawStuff02() {
		var str;
	  	$.ajax({
			"url" : "/chart/totalbardata2",
			"async" : false
		}).done(function(obj){
		str = obj;
		});
		var data = new google.visualization.arrayToDataTable(str);
  		var options = {
    	
    	legend: { position: 'none' },
    	chart: {
      	title: '',
      	subtitle: '' },
    	axes: {
      	x: {
      	0: { side: 'down', label: ''} // Top x-axis.
      	
      }
    },
    bar: { groupWidth: "80%" }
  };
  var chart = new google.charts.Bar(document.getElementById('total03'));
  chart.draw(data, google.charts.Bar.convertOptions(options));
}
</script>

