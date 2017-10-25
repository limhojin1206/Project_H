<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JQUERY -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	
<div>
	<button id="a">index</button>
	<input type="text"  id="ick" />
	<button id="b">AJAX</button>
</div>
<script>
	$("#a").click(function() {
		$.ajax({
			"type" : "get",	// default : "get"
			"async" : false,	// default : true
			"url" : "/index",
			"data" : {
				"id" : document.getElementById("ick").value
			}
		}).done(function(r){
			if(r== false){
				window.alert("사용불가");
			}else{
				window.alert("사용가능");
			}
		});
	});
	
	$("#b").click(function() {
		$.ajax({
			"url" : "/jquery/dst03",
		}).done(function(r){
			window.alert(r.name+"/"+r.gender);
		});
	});
</script>

<b>PASS</b><br/>
		<input id="p" type="password" name="pass" required="required"/><br/>
		<b>PASSCHECK</b><br/>
		<input id="pck" type="password" name="passcheck" required="required"/><br/>
		<span id="passck"></span><br/>
		<script>
			document.getElementById("pck").onblur = function() {	
				var p = document.getElementById("p");
				var pck = document.getElementById("pck");
				if(p.value.length != 0 && pck.value.length != 0){
					if(p.value==pck.value){
						document.getElementById("passck").innerHTML = "<b style=\"color:blue\">일치합니다</b>";
					}else{
						document.getElementById("passck").innerHTML = "<b style=\"color:red\">불일치합니다</b>";
					}
				}else{
					document.getElementById("passck").innerHTML = "<b>입력이 필요합니다.</b>";
				}
			}
			
			document.getElementById("p").onblur = function() {	
				var p = document.getElementById("p");
				var pck = document.getElementById("pck");
				if(p.value.length != 0 && pck.value.length != 0){
					if(p.value==pck.value){
						document.getElementById("passck").innerHTML = "<b style=\"color:blue\">일치합니다</b>";
					}else{
						document.getElementById("passck").innerHTML = "<b style=\"color:red\">불일치합니다</b>";
					}
				}else{
					document.getElementById("passck").innerHTML = "<b>입력이 필요합니다.</b>";
				}
			}
			
		</script>

