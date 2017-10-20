<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<h2>받은 쪽지함</h2>
	<div>
		<button id="removebt">선택 삭제</button>
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
	
							<td><a href="/memo/send?target=${t.SENDER }">${t.SENDER }</a></td>
	
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
	});
</script>