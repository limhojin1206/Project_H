<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div align="center">
		<h3>친구 목록</h3>
		<hr/>
		<table class="table table-hover" style="width: 50%;">
			<thead>
				<tr>
					<th>아이디</th>
					<th>친구정보</th>
					<th>친구취소</th>
					<th>친구수락일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(viewlist) == 0 }">
						<tr>
							<td colspan="4" align="center"><h4>등록된 친구가 없습니다.</h4>
						</tr>
					</tbody>
					</c:when>
					<c:otherwise>
						<c:forEach var="t" items="${viewlist}" varStatus="tn">
							<tr>
								<td style="padding: 0;"><a href="/memo/send?target=${t.OTHER }">${t.OTHER }</a></td>
								<td style="padding: 0;"><button class="pbt btn btn-primary" role="${t.OTHER }" data-toggle="modal" data-target="#myModal">친구정보</button></td>
								<td style="padding: 0;"><button class="ebt btn btn-danger" role="${t.OTHER }">친구취소</button></td>
								<td style="padding: 0;"><fmt:formatDate value="${t.FDATE}" pattern="yy.MM.dd HH:mm" /></td>
							</tr>
						</c:forEach>
						</tbody>
<!-- 페이징 부분 -->
	<tr>
	<td colspan="4" align="center">
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
					</c:otherwise>
			</c:choose>
		</table>
		<hr/>
		
		<div align="center" id="list" ></div>		
	</div>
<!-- 친구 정보 모달-->
<div id="myModal" class="modal fade" role="dialog" >
  <div class="modal-dialog" align="center">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><h1><b>친구 정보</b></h1></h4>
      </div>
      <div class="modal-body" >
        <div id="friendinfoview"></div>
        <!-- 사진 -->
		<div id="view">
				<img id="furl" style="width: 200; height: 200" class="img-circle"/>
		</div><br/>
		<!-- 개인정보 -->
		<table class="table">
			<tbody>
				<tr>
					<td><b>ID</b></td>
					<td id="fid"></td>
				</tr>
				<tr>
					<td><b>Email</b></td>
					<td id="femail"></td>
				</tr>
				<tr>
					<td><b>GENDER</b></td>
					<td id="fgender"></td>
				</tr>
				<tr>
					<td><b>AGE</b></td>
					<td id="fage"></td>
				</tr>
			</tbody>
		</table>
      </div>
    </div>
  </div>
</div>

<script>
	$(".pbt").click(function(){
		//window.alert($(this).attr("role") + " 프로필 보기");
		$.ajax({
			"type" : "post",
			"async" : false,
			"url" : "/friend/info",
			"data" : {
				"ID" : $(this).attr("role")
			}
		}).done(function(map) {
			if(map.URL != null){
				$("#furl").attr("src",map.URL);
			}else{
				$("#furl").attr("src", "/profiles/default.png");
			}
			$("#fid").html(map.ID);
			$("#femail").html(map.EMAIL);
			$("#fgender").html(map.GENDER);
			$("#fage").html(map.AGE);
		});
	});
</script>

<!-- 친구 취소 -->
<script>
	$(".ebt").click(function(){
		if(!window.confirm("친구 취소 하시겠습니까?")){
			return;
		}
		$.ajax({
			"type" : "post",
			"async" : false,
			"url" : "/friend/endfriend",
			"data" : {
				"one" : "${auth.ID}",
				"other" : $(this).attr("role"),
				"sender" : "${auth.ID}",
				"receiver" : $(this).attr("role")
				
			}
		}).done(function(r) {
			if (r == 2) {
				//window.alert("친구 취소");
			} else {
				//window.alert("친구취소 실패");
			}
		});
		location.reload();
	});
</script>