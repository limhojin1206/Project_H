<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<h2>받은 쪽지함</h2>
	<div>
		<button id="removebt" class="btn btn-danger">선택 삭제</button>
		<a href="send">
		<button id="sendbt" class="btn btn-primary">쪽지보내기</button>
		</a>
		<table class="table table-hover">
			<thead>
				<tr style="background-color: gray;">
					<th style="width: 10%;"><input type="checkbox" id="all" /></th>
					<th style="width: 20%">보낸사람</th>
					<th style="width: 52%">내용</th>
					<th style="width: 18%">날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(totreceiveList) == 0 }">
						<tr>
							<td colspan="4" align="center"><h4>받은 쪽지함이 비어 있습니다.</h4>
						</tr>
			</tbody>
				</c:when>
				<c:otherwise>
					<c:forEach var="t" items="${pagereceiveList}" varStatus="tn">
						<tr>
							<td><input type="checkbox" class="item" name="no"
								value="${t.NO}" /></td>
	
<td>
	<div class="dropdown">
	    <span data-toggle="dropdown">${t.SENDER }</span>
	    <ul class="dropdown-menu">
	    	<li><a href="/memo/send?target=${t.SENDER }">쪽지보내기</a></li>
	    	<li><a href="/friend/info" class="pbt" role="${t.SENDER }"data-toggle="modal" data-target="#myModal">프로필</a></li>
	    	<c:if test="${auth.ID ne t.SENDER }">
		    	<li><a href="/calendar/friendView/${t.SENDER }" data-toggle="modal" data-target="#viewModal" data-backdrop="static">일정보기</a></li>
	    	</c:if>
	    </ul>
	</div>	
</td>
							<td>${t.CONTENT }</td>
							<td><fmt:formatDate value="${t.MDATE}"
									pattern="yy.MM.dd HH:mm" /></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div align="center">
								<c:set var="p" value="${empty param.page ? 1 : param.page}"></c:set>
								<a href="/memo/receivebox?page=1"><c:if test="${page > 1 }">&lt;&lt;</c:if></a>
								<a href="/memo/receivebox?page=${page-1}"><c:if
										test="${page > 1 }">◀</c:if></a>
								<c:forEach var="ch" begin="${pb }" end="${pe }" step="1">
									<c:choose>
										<c:when test="${ch == page}">
											<b>[${ch}]</b>
										</c:when>
										<c:otherwise>
											<a href="/memo/receivebox?page=${ch}">[${ch}]</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<a href="/memo/receivebox?page=${page+1}"><c:if
										test="${page < (fn:length(totreceiveList) / 10) }">▶</c:if></a> <a
									href="/memo/receivebox?page=${last }"><c:if
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

<!-- 일정 viewModal -->
<div id="viewModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<div class="modal-content">
		</div>
	</div>
</div>

<!-- 친구정보 -->
<div id="myModal" class="modal fade" role="dialog" >
  <div class="modal-dialog" align="center">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><h1><b>친구 정보</b></h1></h4>
      </div>
      <div class="modal-body" >
        <jsp:include page="../member/profile.jsp" flush="false" />
      </div>
    </div>
  </div>
</div>

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

	$("#removebt").click(function() {
		var cnt = 0;
		for (var i = 0; i < $(".item").length; i++) {
			if ($(".item").eq(i).prop("checked")) {
				cnt++;
			}
		}
		if(cnt != 0){
			if (window.confirm("삭제하시겠습니까?")) {
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
					"url" : "/memo/remove",
					"data" : {
						"no" : no
					}
				}).done(function(r) {
					if (r == 1) {
						window.alert("삭제 성공");
					} else {
						window.alert("삭제 실패");
					}
				});
				location.reload();
			}
		}
	});
</script>