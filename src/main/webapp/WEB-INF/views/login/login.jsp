<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
input, button {
	width: 100%;
	padding: 5px;
	font-family: 맑은 고딕;
}

b {
	font-size: 12pt;
}
</style>
<div align="center">
	<div style="width: 340px;" align="left">
		<h3>T I T L E </h3>
		<c:if test="${!empty temp }">
				<b style="color: red">login failed..</b>
			</c:if>
		<form action="/session" method="post" autocomplete="off">
			<p>
				<b></b><br /> <input type="text" name="login" p
					required id="login" /><br /> <span id="chk_rst"></span>
			</p>
			<p>
				<b>PASS</b><br /> <input type="password" name="pass" required />
			
		    <label><input type='checkbox' name='keeplog' checked />자동로그인</label>
			
			<button id="sbt" type="submit" style=""> 확 인 </button>
		
		
		</form>
	</div>
	<div style="width: 340px; margin-top: 20px;" align="center">
		New to HUB? <a href="/join">Create an account.</a>
	</div>
</div>