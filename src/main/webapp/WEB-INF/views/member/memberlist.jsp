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
					<c:when test="${fn:length(totreceiveList) == 0 }">
						<tr>
							<td colspan="2" align="center"><h4>등록된 친구가 없습니다.</h4>
						</tr>
					</tbody>
					</c:when>
					<c:otherwise>
						<c:forEach var="t" items="${totreceiveList}" varStatus="tn">
							<tr>
								<td style="padding: 0;"><a href="/memo/send?target=${t.OTHER }">${t.OTHER }</a></td>
								<td style="padding: 0;"><button>친구정보</button></td>
								<td style="padding: 0;"><button>친구취소</button></td>
								<td style="padding: 0;"><fmt:formatDate value="${t.FDATE}" pattern="yy.MM.dd HH:mm" /></td>
							</tr>
						</c:forEach>
						</tbody>
					</c:otherwise>
			</c:choose>
		</table>
		<hr/>
		<h3>친구 검색</h3>
		<p>
			<input type="text" id="srch" style="padding:2px;width:40%; font-size:16pt;"/> 
		</p>
		<div align="center" id="list" ></div>		
	</div>
<script>
	$("#srch").keyup(function(){
		$("#list").html("<h4>검색결과</h4>")
		var a = $("#srch").val();
		//console.log(a);
		if(a.length==0){
			$("#list").html("")
			return;
		}
		
		$.ajax({
			"url" : "searchAjax",
			"data" : {
				"one" : "asd",
				"other" : $("#srch").val()
			}
		}).done(function(obj){
			for (var i = 0; i < obj.length; i++) {
				var t =  "<b>"+obj[i].OTHER +"<br/>";
				$("#list").append(t);	
			}
		});
	});
</script>