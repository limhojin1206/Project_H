<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse">
	<ul class="nav navbar-nav">
		<div class="container-fluid" align="center">
			<li><a style="color: silver;" id="addbt">일정 추가하기</a></li>
			<br />
			<li><a href="/#" style="color: silver;">저장된 운동법</a></li>
			<br />
			<li><a href="/#" style="color: silver;">추천 운동법</a></li>
			<br />
		</div>
	</ul>
</nav>
<script>
	$("#addbt").click(function(){
		var dt = new Date();
		var m = dt.getMonth()+1;
		var d = dt.getDate();
		var ds = dt.getFullYear()+"-"+( m <10 ? "0"+m:m) +"-"+(d<10? "0"+d:d);
		url = "/calendar/add";
		$('#modalTitle').html("일정 추가");
		$('#start').val(ds);
		$('#end').val(ds);
		$('#addModal').modal();
		$("#cbt").click(function(){
			location.reload();
		})
	});
</script>