<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="row">
	<div class="col-sm-12">
		<h2>친구 요청</h2>
	</div>
</div>
<div class="row">
<!-- 친구 추가 검색---------------------------------------------- -->
	<div class="col-sm-6">
		<h4>친구 추가</h4>
		<p>
			<input type="text" id="srch" style="padding: 2px; width: 100%; font-size: 16pt;" placeholder="아이디 입력" />
		</p>
		<div align="center" id="list"></div>

	</div>
<!-- 회원 검색 -->
<script>
	$("#srch").keyup(function() {
		//console.log($("#srch").val());
		$("#list").html("")
		if ($("#srch").val().length == 0) {
			$("#list").html("")
			return;
		}
		$.ajax({
			"url" : "/friend/searchAjax",
			"async" : false,
			"data" : {
				"id" : $("#srch").val()
			}
		}).done(function(obj) {
			var title = "<h3><b>검색결과</b></h3><br/>";
			$("#list").append(title);
			if(obj.length == 0){
				var t = "검색결과가 없습니다.";
				$("#list").append(t);
			}else{
				for (var i = 0; i < obj.length; i++) {
					var t = "<span class=\"friend\" role=\""+ obj[i].ID+"\"><b>"+ obj[i].ID+ "</b> ("+ obj[i].EMAIL+ ")</span><br/>";
					$("#list").append(t);
					}
				$(".friend").click(function() {
					if (window.confirm("친구 요청하시겠습니까?")) {
						if ("${auth.ID}" == $(this).attr("role")) {
							window.alert("자신을 친구 초대할 수 없습니다.");
						} else {
							$.ajax({
								"type" : "post",
								"async" : false,
								"url" : "/friend/makefriend",
								"data" : {
									"one" : "${auth.ID}",
									"other" : $(this).attr("role"),
									"sender" : "${auth.ID}",
									"receiver" : $(this).attr("role"),
									"content" : "<button class=\"abt\" role=${auth.ID} class=\"btn btn-primary\">수락</button><button class=\"rbt\" role=${auth.ID} class=\"btn btn-danger\">거절</button>",
									"readck" : "n"
								}
							}).done(function(r) {
								if (r == 1) {
									window.alert("요청 전송 성공");
								} else {
									if (r == -1) {
										window.alert("이미 친구 입니다.");
									} else if(r == -2){
										window.alert("이미 요청 전송 하였습니다.");
									} else {
										window.alert("요청 전송 실패");
									}
								}
							});
							location.reload();
						}
					}
				});
			}
		});
	});
</script>
	<!-- 요청 받은 리스트---------------------------------------------- -->
	<div class="col-sm-6">
		<div>
			<h4>받은 요청</h4>
			<table class="table table-hover">
				<thead>
<!-- 페이징 부분 -->
	<tr>
	<td colspan="3" align="center">
	<!-- 시작 페이지 -->
		<c:if test="${viewpage.startPage > 1}">
			<a href="?page=1">처음</a>
		</c:if>
	<!-- 이전 페이지 -->
		<c:if test="${viewpage.page > 1}">
			<a href="?page=${viewpage.page -1 }">◀</a>
		</c:if>
	<!-- 현재 페이지 -->
		<c:forEach var="t" begin="${viewpage.startPage }" end="${viewpage.endPage }" >
			<c:choose>
			<c:when test="${t eq viewpage.page }">
				<b>${t }</b>
			</c:when>
			<c:otherwise>
				<a href="?page=${t }">${t }</a>
			</c:otherwise>
			</c:choose>
		</c:forEach>
	<!-- 다음 페이지 -->
		<c:if test="${viewpage.page < viewpage.totalPage}">
			<a href="?page=${viewpage.page + 1 }">▶</a>
		</c:if>
	<!-- 끝 페이지 -->
		<c:if test="${viewpage.endPage < viewpage.totalPage}">
			<a href="?page=${viewpage.totalPage}">끝</a>
		</c:if>
	</td>	
	</tr>
<!-- 페이징 부분 -->
					<tr style="background-color: gray;">
						<th style="width: 30%">보낸사람</th>
						<th style="width: 40%">수락/거절</th>
						<th style="width: 20%">날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${fn:length(viewlist) == 0 }">
							<tr>
								<td colspan="4" align="center"><h4>친구 신청 없음</h4>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="t" items="${viewlist}" varStatus="tn">
							<tr>
								<td><a href="/memo/send?target=${t.SENDER }" class="sender" value="${t.SENDER }">${t.SENDER }</a></td>
								<td>${t.CONTENT }</td>
								<td><fmt:formatDate value="${t.FMDATE}" pattern="yy.MM.dd HH:mm" /></td>
							</tr>
							</c:forEach>

						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- 요청 수락 -->
<script>
	$(".abt").click(function() {
		$.ajax({
			"type" : "post",
			"async" : false,
			"url" : "/friend/agreefriend",
			"data" : {
				"one" : "${auth.ID}",
				"other" : $(this).attr("role"),
				"sender" : "${auth.ID}",
				"receiver" : $(this).attr("role")
			}
		}).done(function(r) {
			if (r == 2) {
				window.alert("수락 성공");
			} else {
				if (r == -1) {
					window.alert("이미 친구 입니다.");
				} else {
					window.alert("수락 실패");
				}

			}
		});
		location.reload();

	});
</script>

<!-- 요청 거절 -->
<script>
	$(".rbt").click(function() {
		console.log($(this).attr("role"));
		$.ajax({
			"type" : "post",
			"async" : false,
			"url" : "/friend/rejectfriend",
			"data" : {
				"sender" : "${auth.ID}",
				"receiver" : $(this).attr("role")
			}
		}).done(function(r) {
			if (r == 1) {
				window.alert("거절 성공");
			} else {
				window.alert("거절 실패");
			}
		});
		//location.reload();
	});
</script>

