<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container" align="center">
	<h2>게시판</h2>
	<table class="table">
		<thead>
			<tr>
				<th style="width: 10%">번호</th>
				<th style="width: 50%">제목</th>
				<th style="width: 10%">작성자</th>
				<th style="width: 20%">작성일</th>
				<th style="width: 10%">조회수</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="obj" items="${list }">
			<tr>
				<td>${obj.NO }</td>
				<td><a href="/board/view/${obj.NO}">${fn:substring(obj.TITLE, 0, 12) }</a> <c:if test="${obj.C ne null }">[${obj.C }]</c:if></td>
				<td>${obj.ID }</td>
				<td><fmt:formatDate value="${obj.WDATE }" pattern="yyyy.MM.dd HH:mm"/></td>
				<td><fmt:formatNumber value="${obj.CNT }" pattern="#,###" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<c:if test="${param.page gt 1 }">
			<a href="/board/list?page=${param.page -1 }" style="text-decoration: none">
				<b>◀ 이전</b></a>	
		</c:if>
		<c:forEach var="i" begin="${pb }" end="${pe }" varStatus="vs">
			<c:choose>
				<c:when test="${i eq param.page }">
					<b>${i }</b>
				</c:when>
				<c:otherwise>
					<a href="/board/list?page=${i }" style="text-decoration: none">${i }</a>
				</c:otherwise>
			</c:choose>
			<c:if test="${!vs.last }">|</c:if>
		</c:forEach>
		<c:if test="${param.page lt last }">
			<a href="/board/list?page=${param.page +1 }" style="text-decoration: none">
				<b>다음▶</b></a>
		</c:if>
	</div>
	<div>
		<form action="/board/search" method="post">
			<select name="type" style="size: 10px;">
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input id="search" name="search" type="text">
			<button type="submit">검색</button>
		</form>
	</div>
</div>