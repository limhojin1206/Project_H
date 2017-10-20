<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
		
<div class="row">
  <div class="col-sm-12"><h2>친구 요청</h2></div>
</div>
<div class="row">
	<!-- ---------------------------------------------- -->
	<div class="col-sm-6">
		<h4>친구 추가</h4>
		<p>
			<input type="text" id="srch" style="padding:2px;width:100%; font-size:16pt;" placeholder="아이디 입력"/> 
		</p>
		<div align="center" id="list" ></div>		
	
	</div>
	
	<!-- ---------------------------------------------- -->
	<div class="col-sm-6">
		<div>
		<h4>받은 요청</h4>
			<button id="aabt">선택 수락</button>
			<button id="arbt">선택 거절</button>
			<table class="table table-hover">
				<thead>
					<tr style="background-color: gray;">
						<th style="width: 10%;"><input type="checkbox" id="all" /></th>
						<th style="width: 30%">보낸사람</th>
						<th style="width: 40%">수락/거절</th>
						<th style="width: 20%">날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(totreceiveList) == 0 }">
							<tr>
								<td colspan="4" align="center"><h4>친구 신청 없음</h4>
							</tr>
				</tbody>
				</c:when>
				<c:otherwise>
					<c:forEach var="t" items="${pagereceiveList}" varStatus="tn">
						<tr>
							<td><input type="checkbox" class="item" name="no"
								value="${t.NO}" /></td>

							<td><a href="/memo/send?target=${t.SENDER }">${t.SENDER }</a></td>

							<td>${t.CONTENT }</td>
							<td><fmt:formatDate value="${t.FMDATE}"
									pattern="yy.MM.dd HH:mm" /></td>
						</tr>
					</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div align="center">
									<c:set var="p" value="${empty param.page ? 1 : param.page}"></c:set>
									<a href="/friend/makefriend?page=1"><c:if test="${page > 1 }">&lt;&lt;</c:if></a>
									<a href="/friend/makefriend?page=${page-1}"><c:if
											test="${page > 1 }">◀</c:if></a>
									<c:forEach var="ch" begin="${pb }" end="${pe }" >
										<c:choose>
											<c:when test="${ch == page}">
												<b>[${ch}]</b>
											</c:when>
											<c:otherwise>
												<a href="/friend/makefriend?page=${ch}">[${ch}]</a>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<a href="/friend/makefriend?page=${page+1}"><c:if
											test="${page < (fn:length(totreceiveList) / 10) }">▶</c:if></a> <a
										href="/friend/makefriend?page=${last }"><c:if
											test="${page < (fn:length(totreceiveList) / 10)  }">&gt;&gt;</c:if></a>
								</div>
							</td>
						</tr>
					</tfoot>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</div>
<span ></span>

<script>
	$("#all").change(function() {
		$(".item").prop("checked", $(this).prop("checked"));
	});

	$(".item").change(function() {
		var cnt = 0;
		for (var i = 0; i < $(".item").length; i++) {
			if ($(".item").eq(i).prop("checked")) {
				cnt++;
			}
		}
		if (cnt == $(".item").length) {
			$("#all").prop("checked", $(this).prop("checked"));
		} else {
			$("#all").prop("checked", false);
		}
	});

	$("#aabt").click(function() {
		if (window.confirm("수락하시겠습니까?")) {
			var no = "";
			for (var i = 0; i < $(".item").length; i++) {
				if ($(".item").eq(i).prop("checked")) {
					no += $($(".item").eq(i)).val() + ",";
				}
			}
			no = no.slice(0, -1);
			$.ajax({
				"type" : "post",
				"async" : false,
				"url" : "/friend/수락",
				"data" : {
					"no" : no
				}
			}).done(function(r) {
				console.log("r:" + r)
				if (r == 1) {
					window.alert("수락 성공");
				} else {
					window.alert("수락 실패");
				}
			});
			location.reload();
		}
	});
	
	$("#arbt").click(function() {
		if (window.confirm("거절하시겠습니까?")) {
			var no = "";
			for (var i = 0; i < $(".item").length; i++) {
				if ($(".item").eq(i).prop("checked")) {
					no += $($(".item").eq(i)).val() + ",";
				}
			}
			no = no.slice(0, -1);
			$.ajax({
				"type" : "post",
				"async" : false,
				"url" : "/friend/거절",
				"data" : {
					"no" : no
				}
			}).done(function(r) {
				if (r == 1) {
					window.alert("거절 성공");
				} else {
					window.alert("거절 실패");
				}
			});
			location.reload();
		}
	});
	
	$(".abt").click(function(){
		//window.alert($(this).attr("role") + "님 요청을 수락하였습니다.");
		//console.log("수락쪽 : " + $(this).attr("role"));
		$.ajax({
			"type" : "post",
			"async" : false,
			"url" : "/friend/agreefriend",
			"data" : {
				"one" : $(this).attr("role"),
				"other" : "asd",
				"sender" : $(this).attr("role"),
				"other" : "asd"
			}
		}).done(function(r) {
			if (r == 2) {
				window.alert("수락 성공");
			} else {
				if(r == -1){
					window.alert("이미 친구 입니다.");	
				}else{
				window.alert("수락 실패");
				}
				
				
			}
		});
		location.reload();
		
	});
	
	$(".rbt").click(function(){
		window.alert($(this).attr("role") + "님 요청을 거절하였습니다.");
		//console.log("거절쪽 : " + $(this).attr("role"));
		$.ajax({
			"type" : "post",
			"async" : false,
			"url" : "/friend/rejectfriend",
			"data" : {
				"one" : $(this).attr("role"),
				"other" : "asd",
				"sender" : $(this).attr("role"),
				"other" : "asd"
				}
		}).done(function(r) {
			if (r == 2) {
				window.alert("거절 성공");
			} else {
				if(r == -1){
					window.alert("이미 친구 입니다.");	
				}else{
				window.alert("수락 실패");
				}
				
				
			}
		});
		location.reload();
	});
	
	
	
</script>

<script>
	$("#srch").keyup(function(){
		$("#list").html("")
		var a = $("#srch").val();
		//console.log(a);
		if(a.length==0){
			$("#list").html("")
			return;
		}
		
		$.ajax({
			"url" : "../member/searchAjax",
			"data" : {
				"id" : $("#srch").val()
			}
		}).done(function(obj){
			for (var i = 0; i < obj.length; i++) {
				var t =  "<span class=\"friend\" role=\""+ obj[i].ID+"\"><b>"+obj[i].ID +"</b> ("+ obj[i].EMAIL + ")</span><br/>";
				$("#list").append(t);	
			}
			$(".friend").click(function(){
				if(window.confirm("친구 요청하시겠습니까?")){
					console.log($(this).attr("role"));
					$.ajax({
						"type" : "post",
						"async" : false,
						"url" : "/friend/makefriend",
						"data" : {
							"sender": "qwe",
							"receiver": $(this).attr("role"),
							"content" : "<button class=\"abt\" role=\""+ "qwe"+"\">수락</button><button class=\"rbt\" role=\""+ "qwe"+"\">거절</button>",
							"readck":"n"
							
						}
					}).done(function(r) {
						
						
						if (r == 1) {
							window.alert("요청 전송 성공");
						} else {
							if(r == -1){
								window.alert("이미 친구 입니다.");	
							}else{
							window.alert("요청 전송 실패");
							}
							
						}
					});
					location.reload();
					
					
				}
			});
		});
	});
	
	
</script>