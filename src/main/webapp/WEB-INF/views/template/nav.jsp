<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid" >	
	<ul class="nav navbar-nav" >
		<li class="active"><a href="/main">Home</a></li>
		<li><a href="/my/info" style="padding-top: 0;padding-bottom: 0;"><button class="btn btn-info navbar-btn">나의 정보/친구 관리 서비스</button></a></li>
		<li><a href="/calendar/view" style="padding-top: 0;padding-bottom: 0;"><button class="btn btn-info navbar-btn">내 일정 관리/추천 운동법 서비스</button></a></li>	
		<li><a href="/chart/mychart" style="padding-top: 0;padding-bottom: 0;"><button class="btn btn-info navbar-btn">내 차트/전체 차트 서비스</button></a></li>	
		<li><a href="/board/list?page=1&bgno=1" style="padding-top: 0;padding-bottom: 0;"><button class="btn btn-info navbar-btn">공지게시판/자유게시판 서비스</button></a></li>
	</ul>

	<ul class="nav navbar-nav navbar-right">
		<c:choose>
			<c:when test="${empty auth}">
				<li><a href="/member/join"><span
						class="glyphicon glyphicon-user"></span> Join</a></li>
				<li><a href="/member/login"><span
						class="glyphicon glyphicon-log-in"></span> Login</a>
				<li><a href="/my/info"><span
						class="glyphicon glyphicon-user"></span> INFO</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/member/logout"><span
						class="glyphicon glyphicon-log-out"></span> LOGOUT</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>