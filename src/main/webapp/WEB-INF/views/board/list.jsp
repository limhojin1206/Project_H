<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>게시판</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
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
        <td>${obj.TITLE }</td>
        <td>${obj.ID }</td>
        <td><fmt:formatDate value="${obj.WDATE }" pattern="yyyy.MM.dd HH:mm"/></td>
        <td><fmt:formatNumber value="${obj.CNT }" pattern="#,###" /></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>