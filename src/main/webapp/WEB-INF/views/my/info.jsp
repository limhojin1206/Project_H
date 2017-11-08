<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div align="center">
	<div>
		<h2>회원정보 조회</h2>
	</div>
	<div style="width: 50%">
	<!-- 사진 -->
		<div >
			<c:choose>
				<c:when test="${! empty myinfo.URL }">
					<img id="infopre" src="${myinfo.URL}" style="width: 200; height: 200" class="img-circle"/>
				</c:when>
				<c:otherwise>
					<img id="infopre" src="/profiles/default.png" alt="기본이미지" 	style="width: 200; height: 200" class="img-circle"/>
				</c:otherwise>
			</c:choose>
		</div><br/>
		<!-- 개인정보 -->
		<table class="table">
			<tbody>
				<tr>
					<td><b>ID</b></td>
					<td>${myinfo.ID}</td>
				</tr>
				<tr>
					<td><b>Email</b></td>
					<td>${myinfo.EMAIL }</td>
				</tr>
				<tr>
					<td><b>GENDER</b></td>
					<td>${myinfo.GENDER}</td>
				</tr>
				<tr>
					<td><b>AGE</b></td>
					<td>${myinfo.AGE}</td>
				</tr>
			</tbody>
		</table>
		<!-- 정보수정 -->
		<div>
			<a href="/my/adjust"><button type="submit" class="btn btn-info btn-block">정보수정</button></a>
		</div>
	</div>
</div>
