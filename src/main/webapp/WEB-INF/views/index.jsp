<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<div class="row">
	<div class="col-sm-8">
		<div id="myCarousel" class="carousel slide" data-ride="carousel"
			style="height: 80%">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="mainback1.jpg" alt="mainback1" style="width: 100%;">
				</div>

				<div class="item">
					<img src="mainback2.jpg" alt="mainback2" style="width: 100%;">
				</div>

				<div class="item">
					<img src="mainback2.jpg" alt="mainback3" style="width: 100%;">
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>

	<style>
input, button, checkbox {
	width: 100%;
	padding: 5px;
	font-family: 맑은 고딕;
}
{
font-size
 
:
12pt;
 

}
s
</style>

	<div align="center" class="col-sm-4" class="low-sm-4">

		<div style="width: 340px;" align="center">
			<h3>타이틀</h3>
		</div>
		</br> </br> </br> </br> </br> </br> </br> </br> </br> </br> </br>


		<c:if test="${!empty temp }">
			<b style="color: red">로그인 실패</b>
		</c:if>
		<form action="/login/main" method="post" autocomplete="off">
			<c:if test="${ !empty param.redirect }">
				<input type="hidden" name="redirect" value="${param.redirect }" />
			</c:if>
			<p>
				<br /> <input type="text" name="idmail" required 
					placeholder="USER ID" /><br /> <span id="chk_rst"></span>
			</p>
			<p>
				<br /> <input type="password" name="pass" required
					placeholder="PASS WORD" />
			</p>
			<p>
       
         <!-- 체크박스  -->
				<label><input type='checkbox' name='keeplog' checked="" />자동로그인</label>



				<button id="sbt" type="submit" style="">확 인</button>
				
				
			<div style="width: 340px; margin-top: 20px;" align="center">
				Create JOIN? <a href="/join">회원가입하기</a>
			</div>

		</form>
	</div>
</div>
</div>
