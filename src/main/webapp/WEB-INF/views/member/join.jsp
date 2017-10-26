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
	<div style="width: 380px;" align="left">
		<form action="/member/join" method="post" autocomplete="off">
		<h1>회 원 가 입 </h1>
		<p>
				<b>ID</b>
				<br /> <input type="text"  id="ick"  name=id required id="id" autocomplete="off"/><br />
			    <span id="idck"></span><br/>
<script>
	document.getElementById("ick").onkeyup = function() {
		var xhr = new XMLHttpRequest();
		console.log(document.getElementById("ick").value);
		xhr.open("post","/member/signup_check/id" ,true);
		xhr.send(document.getElementById("ick").value);
		xhr.onreadystatechange = function() {
			if(this.readyState==4){
				if(document.getElementById("ick").value.length==0){
					document.getElementById("idck").innerHTML="<b>아이디를 입력해주세요</b>";
				}else{
					if(this.responseText == "true"){
						flag1=true;
						console.log(flag1);
						valid();
						document.getElementById("idck").innerHTML= "<span style=\"color:blue\"><b>사용가능한 아이디입니다.</b></span>";
					}else{
						flag1=false;
						console.log(flag1);
						valid();
						document.getElementById("idck").innerHTML= "<span style=\"color:red\"><b>사용할수 없는 아이디입니다.</b></span>";
					}
				}
			}
		}
	}
</script>
		<b>PASS</b><br/>
		<input id="p" type="password" name="password" required="required"/><br/>
		<b>PASSCHECK</b><br/>
		<input id="pck" type="password" name="passcheck" required="required"/><br/>
		<span id="passck"></span><br/>

<script>
	document.getElementById("p").onkeyup = function() {	
		var p = document.getElementById("p");
		var pck = document.getElementById("pck");
		if(p.value.length != 0 && pck.value.length != 0){
			if(p.value==pck.value){
				document.getElementById("passck").innerHTML = "<b style=\"color:blue\">일치합니다</b>";
				flag2=true;
				console.log(flag2);
				valid();
			}else{
				document.getElementById("passck").innerHTML = "<b style=\"color:red\">불일치합니다</b>";
				flag2=false;
				console.log(flag2);
				valid();
				}
		}else{
			document.getElementById("passck").innerHTML = "<b>입력이 필요합니다.</b>";
		}
	}
	
	document.getElementById("pck").onkeyup = function() {	
		var p = document.getElementById("p");
		var pck = document.getElementById("pck");
		if(p.value.length != 0 && pck.value.length != 0){
			if(p.value==pck.value){
				document.getElementById("passck").innerHTML = "<b style=\"color:blue\">일치합니다</b>";
				flag2=true;
				console.log(flag2);
				valid();
			}else{
				document.getElementById("passck").innerHTML = "<b style=\"color:red\">불일치합니다</b>";
				flag2=false;
				console.log(flag2);
				valid();
			}
		}else{
			document.getElementById("passck").innerHTML = "<b>입력이 필요합니다.</b>";
		}
	}
</script>

		<p>
		<b>YEAR </b><br/>
		<select name="age" style="width: 100%;padding: 5px;font-family: 맑은 고딕; ">
			<c:forEach var="i" begin="0" end="${2017-1900}"> 
				<option value="${2017-i}" ${2017-i == 1990 ? 'selected="selected"' : '' } >${2017-i}</option>
			</c:forEach>
		</select>
        </p>
        
		<p>
		<b>GENDER </b><br/>
		남 <input type="radio" name="gender" value="남자" checked="checked" style="width: 40%" />여<input type="radio" name="gender" value="여자" style="width: 40%"/>
		</p>

		<p>
		<b>EMAIL</b><br /> 
		<input id="eck" type="email" name="email" required="required" /><br/>
		<span id="eckv" ></span><br/> 
		</p>
		
<script>
	document.getElementById("eck").onkeyup = function() {
		var xhr = new XMLHttpRequest();
			console.log(document.getElementById("eck").value);
			var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/; 
			if(!regExp.test(document.getElementById("eck").value)) {
				document.getElementById("eckv").innerHTML="<b>메일주소를 입력해주세요.</b>";
				return;
			} 
			xhr.open("post","/member/signup_check/email" ,true);
			xhr.send(document.getElementById("eck").value);
			xhr.onreadystatechange = function() {
				if(this.readyState==4){
					if(document.getElementById("eck").value.length==0){
						document.getElementById("eckv").innerHTML="<b>이메일을 입력해주세요</b>";
					}else{
						if(this.responseText == "true"){
							flag3=true;
							console.log(flag3);
							valid();
							document.getElementById("eckv").innerHTML= "<span style=\"color:blue\"><b>사용가능한 이메일주소입니다.</b></span>";
						}else{
							flag3=false;
							console.log(flag3);
							valid();
							document.getElementById("eckv").innerHTML= "<span style=\"color:red\"><b>사용할수 없는 이메일주소입니다.</b></span>";
						}
					}
				}
			}
		}
</script>
		<button type="reset">R E S E T </button><br/>
		<button id="join" type="submit" disabled="disabled">C R E A T E</button><br/>
 		<a href="/"><button id="" type="button" > C E N C E L </button></a>
		</form>
 	</div>
</div>

<script>
	var flag1= false;
	var flag2= false;
	var flag3= false;
	
	var valid = function() { 
		console.log("flag1 : "+flag1);
		console.log("flag2 : "+flag2);
		console.log("flag3 : "+flag3);
		if(flag1 && flag2 && flag3  ){
			document.getElementById("join").disabled=false;
		}else{
			document.getElementById("join").disabled=true;
		}
	}
</script>