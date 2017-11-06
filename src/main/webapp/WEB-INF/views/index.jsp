<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<html>
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
<style>
  header.carousel{
  	height: 80%;
  }
  </style>
<div id="myCarousel" class="carousel slide" data-ride="carousel"
	align="center">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner">
		<div class="item active">
			<img src="/img/index1.jpg" alt="index1" style="height: auto;">
			<div class="carousel-caption">
			<p class="right-caption text-right">
				<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
					data-target="#myModal" style="width: 20%">GO</button>
					</p>
			</div>
		</div>

		<div class="item">
			<img src="/img/index2.jpg" alt="index2" style="height: auto;">
			<div class="carousel-caption">
				<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
					data-target="#myModal" style="width: 20%">GO</button>
			</div>
		</div>

		<div class="item">

			<img src="/img/index3.jpg" alt="index3" style="height: auto;">

			<div class="carousel-caption text-right">
				<p>
					<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
						data-target="#myModal" style="width: 20%">GO</button>
				</p>
			</div>
		</div>
	</div>

	<!-- Left and right controls -->
	<a class="left carousel-control" href="#myCarousel" data-slide="prev">
		<span class="glyphicon glyphicon-chevron-left"></span> <span
		class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#myCarousel" data-slide="next">
		<span class="glyphicon glyphicon-chevron-right"></span> <span
		class="sr-only">Next</span>
	</a>
</div>


<!-- LOGIN Modal -->
<div class="modal fade" id="myModal" role="dialog" style="margin: 10%;">
	<div class="modal-dialog modal-lg" align="center" >
		<div class="modal-content" style="width: 50%;">
			<div class="modal-body" >
				<h4 class="modal-title">타이틀</h4>
				<!-- 오류시 -->
					<b style="color: red;display: none;"  id="temp">로그인에 실패하였습니다.</b>
				<!-- 입력칸 -->
				<form action="/member/login" method="post" autocomplete="off" id="loginform">
					<c:if test="${param.redirect != null }">
						<input type="hidden" name="redirect" value="${param.redirect}" />
					</c:if>
					<p>
						<br /> <input type="text" name="idmail" required id="idmail"
							placeholder="USER ID" /><br /> <span id="chk_rst"></span>
					</p>
					<p>
						<br /> <input type="password" name="password" required id="password"
							placeholder="PASSWORD" />
					</p>

					<!-- 자동로그인  -->
					<p>
						<input type='checkbox' name='auto' value="auto"
							style="width: auto;" /> 자동로그인
					</p>
				</form>
					<button id="cbt" type="button">확 인</button>
<script>
	$(".btn").click(function(){
		$("#temp").hide();
		$("#idmail").val("");
		$("#password").val("");
	});
	
	$("#idmail").keyup(function(r){
		if(r.keyCode  == 13 ){
			$("#password").focus();
		}
	});
	
	$("#password").keyup(function(r){
		if(r.keyCode  == 13 && $("#idmail").val() != ''){
			$("#cbt").trigger('click');
		}
	});
	
	$("#cbt").click(function() {
		var idmail = $("#idmail").val();
		console.log(idmail);
		var password = $("#password").val();
		console.log(password);
		$.ajax({
			"type" : "post",
			"async" : false,
			"url" : "member/loginck",
			"data" : {
				"idmail" :idmail,
				"password" : password
			}
		}).done(function(r) {
			if (r == 1) {
				$("#loginform").submit();
			} else {
				$("#temp").show();
			}
		});
	});
</script>

				<div style="width: 340px; margin-top: 20px;" align="center">
					Create JOIN? 
					<a data-toggle="modal" data-target="#myModal2" id="joinpage">회원가입하기</a><br /> 
					<script>
					$("#joinpage").click(function(){
						$("#idck").html("");
						$("#passck").html("");
						$("#eckv").html("");
						$("#reset").trigger('click');
						
					})
					</script>
					<a data-toggle="modal" data-target="#myModal3" id="findpass">비밀번호
						찾기</a><br />
<script>
$("#findpass").click(function(){
	$("#inputemail").val("");
})

</script>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 회원가입 Modal -->
<!-- Modal -->
<div id="myModal2" class="modal fade" role="dialog" >
  <div class="modal-dialog" align="center">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title"><h1><b>회 원 가 입</b></h1></h4>
      </div>
      <div class="modal-body" >
        <jsp:include page="member/join.jsp" flush="false" />
      </div>
    </div>

  </div>
</div>

<!-- 비밀번호찾기 Modal -->
<!-- Modal -->
<div id="myModal3" class="modal fade" role="dialog" style="margin: 10%;">
  <div class="modal-dialog" align="center">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">비밀번호 찾기</h4>
      </div>
      <div class="modal-body" id="emailkeyview" >
        <!-- 1단계 -->
        <div id="step1">
	        <p>가입하실때 작성하신 이메일을 입력해주세요</p>
    	    <input id="inputemail" type="email"/><br/><br/>
    	    <button type="button" id="emailkey">인증키 전송</button>
        </div>
<script>
$("#emailkey").click(function(){
	//window.alert($("#inputemail").val());
	$.ajax({
		"type" : "post",
		"async" : false,
		"url" : "/member/key",
		"data" : {
			"email" : $("#inputemail").val()
		}
	}).done(function(r) {
		if(r=="true"){
			window.alert($("#inputemail").val() + " 인증키 전송했습니다.");
			$("#step1").hide();
			$("#step2").show();
			$("#step3").hide();
		}else{
			window.alert("사용할수 없는 이메일 주소입니다.");
			$("#inputemail").val("");
		}
	});
});
</script>
         <!-- 2단계 -->
        <div id="step2" style="display: none;">
	        <p>인증번호를 입력해주세요</p>
    	    <input type="text" id="keyinput"/><br/><br/>
    	    <button type="button" id="keycheck">인증키 확인</button>
        </div>
<script>
$("#keycheck").click(function(){
	$.ajax({
		"type" : "post",
		"async" : false,
		"url" : "/member/keyck",
		"data" : {
			"keyinput" : $("#keyinput").val(),
			"email" : $("#inputemail").val()
		}
	}).done(function(r) {
		if(r=="true"){
			window.alert("일치");
			$("#step1").hide();
			$("#step2").hide();
			$("#step3").show();
			$("#keyinput").val("");
		}else{
			window.alert("불일치");
		}
	});
	
});
</script>
         <!-- 3단계 -->
        <div id="step3" style="display: none;">
	        <p id="cpspan">새 비밀번호를 입력해주세요</p>
	        <form action="/member/changelogin" method="post">
		        <input type="hidden" name="idmail" id="cidmail"/>
	    	    <input type="password" id="changepass" name="password" required="required"/><br/><br/>
	    	    <input type="password" id="changepassck" required="required"/><br/><br/>
	    	    <button type="submit" id="changepassbt" disabled="disabled">새비밀번호로 로그인</button>
	        </form>
        </div>
<script>
	
	
	document.getElementById("changepass").onkeyup = function() {	
		var cp = document.getElementById("changepass");
		var cpck = document.getElementById("changepassck");
		console.log(cp.value);
		console.log(cpck.value);
	
		var email = $("#inputemail").val();
		console.log(email);
		$("#cidmail").val(email);
		
		if(cp.value.length != 0 && cpck.value.length != 0){
			if(cp.value==cpck.value){
				document.getElementById("cpspan").innerHTML = "<b style=\"color:blue\">일치합니다</b>";
				document.getElementById("changepassbt").disabled=false;
			}else{
				document.getElementById("cpspan").innerHTML = "<b style=\"color:red\">불일치합니다</b>";
				document.getElementById("changepassbt").disabled=true;
				}
		}else{
			document.getElementById("cpspan").innerHTML = "<b>입력이 필요합니다.</b>";
			document.getElementById("changepassbt").disabled=true;
		}
	}
	
	document.getElementById("changepassck").onkeyup = function() {	
		var cp = document.getElementById("changepass");
		var cpck = document.getElementById("changepassck");
		console.log(cp.value);
		console.log(cpck.value);
		var email = $("#inputemail").val();
		console.log(email);
		$("#cidmail").val(email);
		
		if(cp.value.length != 0 && cpck.value.length != 0){
			if(cp.value==cpck.value){
				document.getElementById("cpspan").innerHTML = "<b style=\"color:blue\">일치합니다</b>";
				document.getElementById("changepassbt").disabled=false;
			}else{
				document.getElementById("cpspan").innerHTML = "<b style=\"color:red\">불일치합니다</b>";
				document.getElementById("changepassbt").disabled=true;
			}
		}else{
			document.getElementById("cpspan").innerHTML = "<b>입력이 필요합니다.</b>";
			document.getElementById("changepassbt").disabled=true;
		}
	}
</script>
      </div>
    </div>

  </div>
</div>
<jsp:include page="template/footer.jsp" flush="false" />
</html>
