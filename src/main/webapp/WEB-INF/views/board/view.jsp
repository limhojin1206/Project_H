<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container" align="center">
	<c:choose>
		<c:when test="${empty view }">
			이미 삭제된 글입니다.
		</c:when>
		<c:otherwise>
			<div style="width: 80%" align="left">
				<input type="hidden" id="num" value="${view.NO }"/>
				<h3>${view.TITLE } </h3>
				<p>
					<small>작성자 : ${view.ID } | 작성일 : <fmt:formatDate
							pattern="MM.dd.yyyy HH:mm:ss" value="${view.WDATE }" /> | 조회수
						: <fmt:formatNumber value="${view.CNT }" pattern="#,###" />
					</small>
				</p>
				<pre style="font-family: 맑은 고딕; font-size: 10pt; min-height: 250px;">${view.CONTENT }</pre>
			</div>
		</c:otherwise>
	</c:choose>
	<hr />
	<a href="/board/list"><button>목록으로</button></a>
	<a href="/delete.do?num=${view.NO }"><button>삭제</button></a>
	<hr/>
	<div id="view" style="width: 80%" align="left">
	</div>
	<div align="center" style="width: 100%; line-height: 30px;">
		<p>
			<input id="writer" type="text" placeholder="작성자" style="width: 40%;" />
		</p>
		<p>
			<textarea id="content" rows="3" placeholder="댓글내용" style="width: 40%;" required></textarea>
			
		</p>
		<p>
			<button id="sbt" type="submit" style="">댓글등록</button>
		</p>
	</div>
	<hr/>
</div>
<script>
	document.getElementById("sbt").onclick = function() {
		var parent = ${view.NO};
		var writer = document.getElementById("writer").value;
		var content = document.getElementById("content").value;
		if(content.length == 0){
			return;
		}			
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange= function(){
			if(this.readyState==4) {
				var obj = this.responseText;
				if (obj == "true") {
					window.alert("등록되었습니다.");
					document.getElementById("writer").value="";
					document.getElementById("content").value="";
					list();
				}else{
					window.alert("등록 실패.");
				}
			}
		}
		var data = {
			"bno" : parent,
			"id" : writer,
			"content" : content
		};
		xhr.open("post","/reply/add", true);
		xhr.send(JSON.stringify(data));
	};
	var list = function() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange= function(){
			if(this.readyState==4) {
				var obj = JSON.parse(this.responseText);
				var rst ="";
				for(i in obj){
					rst += "<pre><span><b>"+obj[i].ID+"</b></span> -<small>"+obj[i].CRDATE+"</small><br/><span>"+obj[i].CONTENT+"</span></pre><br/>";
				}
				document.getElementById("view").innerHTML = rst;
			}
		}
		xhr.open("post","/reply/list/${view.NO}", true);
		xhr.send();
	};
	list();	
</script>