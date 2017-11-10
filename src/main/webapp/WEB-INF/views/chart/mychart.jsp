<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center" style="width: 100%; height: 100%">
	<div>
		<h2>
			<b><i>${auth.ID }</i>님의 운동 비율 분석 차트표</b>
		</h2>
		<div id="mychart01" style="width: 600px; height: 400px"></div>
	</div>
	<hr />
	<br />
	<div class="row">
		<div class="col-sm-4">
			<h3>
				<b>· 유산소운동 비율 분석</b>
			</h3>
			<br />
			<div id="mychart02" style="width: 350px; height: 500px"></div>
			<small>oxeygen exercise ratio chart</small>
		</div>
		<div class="col-sm-8">
			<h3>
				<b>· 근력운동 비율 분석</b>
			</h3>
			<br />
			<div id="mychart03" style="width: 550px; height: 500px"></div>
			<small>mucsle exercise ratio chart</small>
		</div>
	</div>
</div>
<hr />
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load("current", {
		"packages" : [ "corechart" ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {

		var data = new google.visualization.DataTable();
		data.addColumn('string', 'exmu');
		data.addColumn('number', 'count');
		$.ajax({

			"url" : "/chart/mypiedata",
			"async" : false
		}).done(function(obj) {
			data.addRows(obj);

		});

		var options = {
			colors : ['#FFFF33','#009900'],
			"title" : "나의 운동비율",
			"sliceVisibilityThreshold" : .2,
			"is3D" : true
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('mychart01'));
		chart.draw(data, options);
	}

	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawStuff11);

	function drawStuff11() {
		var str1;
		$.ajax({
			"url" : "/chart/nullbardata1",
			"async" : false
		}).done(function(obj) {
			str1 = obj;
		});
		var data = new google.visualization.arrayToDataTable(str1);
		var options = {
			animation : {
				duration : 1000,
				easing : 'out'
			},
			vAxis : {
				minValue : 0,
				maxValue : 6
			},
			colors : [ '#009900' ],
			legend : {
				position : 'none'
			},
			chart : {
				title : '',
				subtitle : ''
			},
			axes : {
				x : {
					0 : {
						side : 'down',
						label : ''
					}
				// Top x-axis.

				}
			},
			bar : {
				groupWidth : "80%"
			}
		};
		setTimeout(function() {
			var str2;
			$.ajax({
				"url" : "/chart/bardata1",
				"async" : false
			}).done(function(obj) {
				str2 = obj;
			});

			var data = new google.visualization.arrayToDataTable(str2);
			chart.draw(data, options);
		}, 1500);
		  var chart = new google.visualization.ColumnChart(document.getElementById('mychart02'));
		  chart.draw(data, options);
	}
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawStuff02);

	function drawStuff02() {
		var str3;
		$.ajax({
			"url" : "/chart/nullbardata2",
			"async" : false
		}).done(function(obj) {
			str3 = obj;
		});
		var data = new google.visualization.arrayToDataTable(str3);
		var options = {
			animation : {
				duration : 1000,
				easing : 'out'
			},

			colors : [ '#FFFF33' ],
			legend : {
				position : 'none'
			},
			chart : {
				title : '',
				subtitle : ''
			},
			axes : {
				x : {
					0 : {
						side : 'down',
						label : ''
					}
				// Top x-axis.

				}
			},
			bar : {
				groupWidth : "80%"
			}
		};
		setTimeout(function() {
			var str4;
			$.ajax({
				"url" : "/chart/bardata2",
				"async" : false
			}).done(function(obj) {
				str4 = obj;
			});
			var data = new google.visualization.arrayToDataTable(str4);
			chart.draw(data, options);
		}, 1500);

		var chart = new google.visualization.ColumnChart(document
				.getElementById('mychart03'));
		chart.draw(data, options);
	}
</script>

