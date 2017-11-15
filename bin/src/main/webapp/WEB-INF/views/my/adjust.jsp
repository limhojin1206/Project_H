<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div align="center">
	<h1>회원정보수정</h1>

	<div class="row">
		<div class="col-sm-6" align="center">
			<div class="panel panel-primary">
				<h2>내정보 변경</h2>
				<form class="form-group" action="adjust" method="post">
					<h5>사용자아이디</h5>
					<input type="text" id="id" name="id" value="${myinfo.ID}" class="form-control" readonly required /><br />
	
					<h5>비밀번호변경</h5>
					<input type="password" class="form-control" id="p" name="password" id="password" value="${myinfo.PASSWORD}" required placeholder="변경할 비밀번호를 입력" /><br />
					<input type="password" class="form-control" id="pck" name="passcheck"1 value="${myinfo.PASSWORD}" required placeholder="변경 비밀번호 확인	" /><br />
					<span id="passck"></span><br />
	
					<script>
						document.getElementById("p").onkeyup = function() {
							var p = document.getElementById("p");
							var pck = document.getElementById("pck");
							if (p.value.length != 0 && pck.value.length != 0) {
								if (p.value == pck.value) {
									flag1 = true;
									valid();
									document.getElementById("passck").innerHTML = "<b style=\"color:blue\">일치합니다</b>";
								} else {
									flag1 = false;
									valid();
									document.getElementById("passck").innerHTML = "<b style=\"color:red\">불일치합니다</b>";
								}
							} else {
								flag1 = false;
								valid();
								document.getElementById("passck").innerHTML = "<b>입력이 필요합니다.</b>";
							}
						}
	
						document.getElementById("pck").onkeyup = function() {
							var p = document.getElementById("p");
							var pck = document.getElementById("pck");
							if (p.value.length != 0 && pck.value.length != 0) {
								if (p.value == pck.value) {
									flag1 = true;
									valid();
									document.getElementById("passck").innerHTML = "<b style=\"color:blue\">일치합니다</b>";
								} else {
									flag1 = false;
									valid();
									document.getElementById("passck").innerHTML = "<b style=\"color:red\">불일치합니다</b>";
								}
							} else {
								flag1 = false;
								valid();
								document.getElementById("passck").innerHTML = "<b>입력이 필요합니다.</b>";
							}
						}
					</script>
					<h5>사용자 이메일</h5>
					<input type="email" class="form-control" name="email" value="${myinfo.EMAIL}" readonly required /><br /> <br />
					<h5>성별</h5>
					<c:choose>
						<c:when test="${myinfo.GENDER eq '남자'}">
							<label class="radio-inline"><input type="radio" name="gender" value="남자" checked="checked" required />남</label>
							<label class="radio-inline"><input type="radio" name="gender" value="여자" required />여</label><br />
						</c:when>
						<c:when test="${myinfo.GENDER eq '여자'}">
							<label class="radio-inline"><input type="radio" name="gender" value="남자" required />남</label>
							<label class="radio-inline"><input type="radio" name="gender" value="여자" checked="checked" required />여</label><br />
						</c:when>
					</c:choose>
					<h5>생년 ${myinfo.AGE}</h5>
					<select name="age" class="form-control">
						<c:forEach begin="10" end="100" step="10" var="y">
							<option ${ fn:substring(myinfo.AGE, 0 ,2 ) == y ? 'selected' : ''}>${y}대</option>
						</c:forEach>
					</select>
					<button type="submit" id="change" style="display: none;" >정보 변경</button>
				</form>
			</div>
		</div>
		
		<div class="col-sm-6" align="center">
			<div class="panel panel-primary" style="margin: 0;" >
				<h2>사진 관리</h2>
				<c:choose>
					<c:when test="${! empty myinfo.URL }">
						<img id="pic" src="${myinfo.URL}" style="width: 200; height: 200" />
					</c:when>
					<c:otherwise>
						<img id="pic" src="/profiles/default.png" alt="기본이미지"
							style="width: 200; height: 200" />
					</c:otherwise>
				</c:choose>
				<br/><br/>
				<form action="/my/changepic" method="post" style="display: block;" id="pform">
					<input id="changpic" name="changpic" type="file" style="display: none" />
					<button type="button" id="changepicbt" class="btn btn-default">사진 변경</button>
					<button type="button" onclick="javascript:location.reload()" class="btn btn-default">적용 취소</button>
				</form>
			</div>
			<br/><br/>
			<div >
				<div align=center>
					<button id="changeinfo" class="btn btn-primary btn-block">수정완료</button><br /> 
					<a href="/my/info"><button type="button" class="btn btn-danger btn-block">수정취소</button></a>
				</div>
			</div>
			
			
		</div>
		<script>
		/*	
		document.getElementById("changpic").onchange = function() {
				var reader = new FileReader();
				reader.onload = function(e) {
					document.getElementById("pic").src = e.target.result;
				}
				reader.readAsDataURL(this.files[0]);
			}
			*/
		</script>
		<script>
		
		$("#pic").click(function(){
			$("#changpic").trigger('click');
		});
		
		$("#changpic").change(function(){
			var reader = new FileReader();
			reader.onload = function(e) {
				$("#pic").attr("src", e.target.result);
			}
			reader.readAsDataURL(this.files[0]);
		});
		
		$("#changepicbt").click(function(){
			
			var form = $('#pform')[0];
			console.log(form);
	        var formData = new FormData(form);
			
	        $.ajax({
				 "url": '/my/changepic',
	             "processData": false,
	             "contentType": false,
	             "data": formData,
	             "type": 'POST',
            }).done(function(r){
            	if(r==1){
            		window.alert("변경 성공");
            	}else{
            		window.alert("변경 실패");
            	}
            });
         });


		$("#changeinfo").click(function(){
			$("#change").trigger('click');
		});
			
			
			
		</script>
	</div>
</div>

<script>
	var flag1= true;
	
	var valid = function() { 
		console.log("flag1 : "+flag1);
		if(flag1){
			document.getElementById("changeinfo").disabled=false;
		}else{
			document.getElementById("changeinfo").disabled=true;
		}
	}
</script>