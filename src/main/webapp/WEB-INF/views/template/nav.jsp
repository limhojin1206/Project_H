<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">	<ul class="nav navbar-nav">
		<li class="active"><a href="/">Home</a></li>

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#">일정 <span
				class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="/#">월간</a></li>
				<li><a href="/#">주간</a></li>
			</ul></li>

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#">분석 <span
				class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="/#">운동별</a></li>
				<li><a href="/#">성별별</a></li>
				<li><a href="/#">내분석</a></li>
				
			</ul></li>
		<li><a href="/#">계획설계</a></li>
		
		<li><a href="/board/list?page=1">공지사항</a></li>
		
		<li><a href="/memo/memobox">MEMO</a></li>
	</ul>

	<ul class="nav navbar-nav navbar-right">
		<c:choose>
			<c:when test="${empty auth}">
				<li><a href="/member/join"><span
						class="glyphicon glyphicon-user"></span> Join</a></li>
				<li><a href="/member/login"><span
						class="glyphicon glyphicon-log-in"></span> Login</a>
			</c:when>
			<c:otherwise>
				<li><a href="/my/info"><span
						class="glyphicon glyphicon-user"></span> INFO</a></li>
				<li><a href="/login/logout"><span
						class="glyphicon glyphicon-log-out"></span> LOGOUT</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>