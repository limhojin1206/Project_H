<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse">
	<ul class="nav navbar-nav">
		<div class="container-fluid" align="center">
			<li><a style="color: silver;" id="addbt">일정 추가하기</a></li>
			<br />
		<!-- 	<li><a style="color: silver;" id="readbt">저장된 운동법</a></li>
			<br />
			<li><a href="/#" style="color: silver;">추천 운동법</a></li>
			<br />	 -->
			<li><a href="/calendar/guide" style="color: silver;">캘린더 사용법 보기</a></li>
			<br/>
		</div>
	</ul>
</nav>
	
<!-- Modal -->
	<div id="readModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="rmodalTitle" class="modal-title"></h4>
				</div>
				<div id="modalBody" class="modal-body">
					<c:forEach var="ex" items="${exList }">
					  <div class="alert alert-info">
					    <strong>${ex.EXMU }</strong><small><a href="" class="alert-link dl" style="color: red;"> | 삭제</a></small> 
					    <small><a href="" class="alert-link al" style="color: #0100FF;"> | 일정 추가</a></small>
					  </div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
<script>
	$(".dl").click(function(){
		alert('adsf');
	})

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
	
	$("#readbt").click(function(){
		var dt = new Date();
		var m = dt.getMonth()+1;
		var d = dt.getDate();
		var ds = dt.getFullYear()+"-"+( m <10 ? "0"+m:m) +"-"+(d<10? "0"+d:d);
		url = "";
		$.ajax({
			"type" : "post",
			"async" : true,
			"url" : "/calendar/exList"
			
		}).done(function(obj) {
			console.log(obj);
			for(i in obj){
				$('#rtitle').html(obj[i].TITLE);
				console.log(obj[i].TITLE);
				console.log(obj[i].EXMU);
				console.log(obj[i].EXPART);
				console.log(obj[i].EXTIME);
				console.log(obj[i].EXCNT);
				console.log(obj[i].EXSET);
			}
			$('#rmodalTitle').html("저장된 운동법");
			$('#readModal').modal();
		//	$('#start').val(ds);
		//	$('#end').val(ds);
		//	$("#cbt").click(function(){
		//		location.reload();
		//	})
		});
		
	});
</script>