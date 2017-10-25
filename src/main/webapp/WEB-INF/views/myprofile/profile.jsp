<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	br{
		padding: 3pt;
		width: 50px;
	}
</style>
<div align="center">
	<h2>사진 관리</h2>
		<div>
			<c:choose>
				<c:when test="${! empty auth.URI }">
					<img id="pre" src="${auth.URI}" style="width:200; height: 200" />
				</c:when>
				<c:otherwise>
					<img id="pre" src="사진경로" alt="emage" style="width:200; height: 200" />
				</c:otherwise>
			</c:choose>
			
			<form action="/member/profile" method="post" enctype="multipart/form-data" style="display:block;">
				<input id = "profile" type="file" name="profile" style="display:none"/>
				<button type="submit" class="bt">적용</button>
				<button type="button" class="bt" onclick="javascript:location.reload()">취소</button>
			</form>
		</div>
</div>
<script>
	document.getElementById("pre").onclick= function(){
		document.getElementById("profile").click();
	}
	
	document.getElementById("profile").onchange = function(){
		var reader = new FileReader();
		reader.onload = function(e){
			document.getElementById("pre").src = e.target.result;
		}
		reader.readAsDataURL(this.files[0]);
	}

</script>




    