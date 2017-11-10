<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center" style="width: 100%; height:100%">
<div>
	<h2><b>전체운동 비율 분석 차트표</b></h2>
	<div id="total01" style="width: 600px; height: 400px"></div>
</div><hr/><br/>
<div class="row">
<div class="col-sm-4">
		<h3><b>· 유산소운동 비율 분석</b></h3><br/><br/>
		<div id="total02" style="width:350px; height:500px"></div>
		<small>total oxeygen exercise ratio chart</small>
</div>
<div class="col-sm-8">
		<h3><b>· 근력운동 비율 분석</b></h3><br/><br/>
		<div id="total03" style="width:550px; height:500px"></div>
		<small>total mucsle exercise ratio chart</small>
</div>
</div>
</div><hr/>
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
			 	colors: ['#CCFF00','33CCFF'],
			    "title" : "회원전체 인원의 운동비율",
			    "sliceVisibilityThreshold": .2,
				"is3D" : true
			  };
	 
	  var chart = new google.visualization.PieChart(document.getElementById('total01'));
  	  chart.draw(data, options);
	}
	
	google.charts.load('current', {'packages':['bar']});
	google.charts.setOnLoadCallback(drawStuff01);

	function drawStuff01() {
		var teststr1;
	  	$.ajax({
			"url" : "/chart/nullbardata1",
			"async" : false
		}).done(function(obj){
		teststr1 = obj;
		});
	  
	  var data = new google.visualization.arrayToDataTable(teststr1);
		
  		var options = {
  		animation:{duration:1000,easing:'out'},
  		vAxis:{minValue:0, maxValue:10},
    	colors: ['#CCFF00'],
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
  		
  setTimeout(function(){
		var str;
	  	$.ajax({
			"url" : "/chart/totalbardata1",
			"async" : false
		}).done(function(obj){
		str = obj;
		});
	  
	  var data = new google.visualization.arrayToDataTable(str);
	  chart.draw(data, options);
  },1500);
  		
  var chart = new google.visualization.ColumnChart(document.getElementById('total02'));
  chart.draw(data, options);
  
	}
	
	google.charts.load('current', {'packages':['bar']});
	google.charts.setOnLoadCallback(drawStuff02);

	function drawStuff02() {
		var teststr2;
	  	$.ajax({
			"url" : "/chart/nullbardata2",
			"async" : false
		}).done(function(obj){
		teststr2 = obj;
		});
	  
	  var data = new google.visualization.arrayToDataTable(teststr2);

  		var options = {
  		  		animation:{duration:1000,easing:'out'},
  		  	
  		    	colors: ['#33CCFF'],
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
  		
  	  setTimeout(function(){
  		var str2;
  	  	$.ajax({
  			"url" : "/chart/totalbardata2",
  			"async" : false
  		}).done(function(obj){
  		str2 = obj;
  		});
  	  var data = new google.visualization.arrayToDataTable(str2);
	  chart.draw(data, options);
  },1500);
  		
  var chart = new google.visualization.ColumnChart(document.getElementById('total03'));
  chart.draw(data, options);
	}
</script>

