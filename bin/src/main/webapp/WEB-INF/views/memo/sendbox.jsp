<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div>
	<h2>보낸 쪽지함</h2>
	<form action="/memo/remove" name="table" method="get">
		<button id="removebt">선택 삭제</button>
		<table class="table table-hover">
			<thead>
				<tr style="background-color: gray;">
					<th style="width: 10%"><input type="checkbox" id="all" /></th>
					<th style="width: 20%">받은사람</th>
					<th style="width: 52%">내용</th>
					<th style="width: 18%">날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${fn:length(sendList) == 0 }">
						<tr>
							<td colspan="4" align="center"><h4>보낸 쪽지함이 비어 있습니다.</h4>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="t" items="${sendList}" varStatus="tn">
							<tr>
								<td><input type="checkbox" class="item" name="no"
									value="${t.NO}" /></td>
								<td><a href="/memo/send?target=${t.RECEIVER }">${t.RECEIVER }</a></td>
								<td>${t.CONTENT }</td>
								<td><fmt:formatDate value="${t.MDATE}"
										pattern="yy.MM.dd HH:mm" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</form>
</div>
<script>
	$("#all").change(function() {
		$(".item").prop("checked", $(this).prop("checked"));
	});
	$("#removebt").click(function() {
		if (window.confirm("삭제하시겠습니까?")) {
			for (var i = 0; i < $(".item").length; i++) {
				if ($(".item").eq(i).prop("checked")) {
					window.alert($($(".item").get(i)).val());
				}
			}
			//location.href="/memo/receivebox";
		}
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
</script>
