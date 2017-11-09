<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div align="center">
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
						| 추천수 : <fmt:formatNumber value="${view.RECOMMEND }" pattern="#,###" />
					</small>
					<c:choose>
						<c:when test="${empty param.mode }">
							<c:if test="${!empty pnPage.PREV }">
								<a href="/board/view/${pnPage.PREV }?bgno=${param.bgno }&page=${param.page}"><button>이전글</button></a>
							</c:if>
							<c:if test="${!empty pnPage.NEXT }">
								<a href="/board/view/${pnPage.NEXT }?bgno=${param.bgno }&page=${param.page}"><button>다음글</button></a>
							</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${!empty pnPage.PREV }">
								<a href="/board/view/${pnPage.PREV }?bgno=${param.bgno }&page=${param.page}&mode=free"><button>이전글</button></a>
							</c:if>
							<c:if test="${!empty pnPage.NEXT }">
								<a href="/board/view/${pnPage.NEXT }?bgno=${param.bgno }&page=${param.page}&mode=free"><button>다음글</button></a>
							</c:if>
						</c:otherwise>
					</c:choose>
				</p>
				<pre style="font-family: 맑은 고딕; font-size: 10pt; min-height: 250px;">${view.CONTENT }</pre>
			</div>
		</c:otherwise>
	</c:choose>
	<hr />
	<div>
	<c:if test="${!empty exview or param.bgno eq 2 }">
	<h3><span class="label label-info">운동법</span></h3>
	<input type="hidden" name="exno" value="${exview.NO }">
	<div class="row" style="width: 85%;" align="left">
	<div class="col-sm-2">
		<br />
		<div class="radio">
			<label><input type="radio" name="optradio" id="type01"
				class="form" checked>유산소</label>
		</div>
		<div class="radio">
			<label><input type="radio" name="optradio" id="type02"
				class="form">근력</label>
		</div>
	</div>
	<div class="col-sm-6">
		<br />
		<div class="type" id="type01ctg">
			5대 유산소 운동 <br /> <select class="form-control" id="sel1"
				name="oxygen">
				<option selected="selected">걷기</option>
				<option>달리기</option>
				<option>줄넘기</option>
				<option>수영</option>
				<option>자전거</option>
			</select>
		</div>
		<div class="type" style="display: none" id="type02ctg">
			10개 근력 운동<br /> <select class="form-control" id="sel2" name="muscle">
				<option selected="selected">가슴</option>
				<option>어깨</option>
				<option>등</option>
				<option>허리</option>
				<option>위팔 앞</option>
				<option>위팔 뒤</option>
				<option>아래팔</option>
				<option>복부</option>
				<option>허벅지 앞</option>
				<option>허벅지 뒤</option>
			</select>
		</div>
	</div>
	<div class="col-sm-4">
		<div>
			시간(분) <select class="form-control" id="time">
				<option selected="selected">0</option>
				<option>30</option>
				<option>60</option>
				<option>90</option>
				<option>120</option>
				<option>150</option>
				<option>180</option>
			</select>
		</div>
		<div>
			횟수 <select class="form-control" id="cnt">
				<option selected="selected">0</option>
				<option>5</option>
				<option>10</option>
				<option>15</option>
				<option>20</option>
			</select>
		</div>
		<div>
			세트수 <select class="form-control" id="set">
				<option selected="selected">0</option>
				<option>3</option>
				<option>5</option>
				<option>7</option>
			</select>
		</div>
	</div>
	</div>
	<c:if test="${view.ID ne auth.ID and empty cList }">
		<div>
			<button id="exsave" type="button">운동법 추가하기</button>
		</div>
	</c:if>
	<br/>
	<div id="ext" style="display: none">
		TITLE : <input type="text" id="extitle">
		<button id="okbt" type="button">확인</button>
	</div>
	<hr/>
	</c:if>
	</div>			
	<c:if test="${empty check and view.ID ne auth.ID }">
		<a><button id="rbt" type="submit">추천하기</button></a>
	</c:if>
	<c:choose>
		<c:when test="${empty param.mode }">
			<a href="/board/list?bgno=${param.bgno }&page=${param.page}"><button>목록으로</button></a>
		</c:when>
		<c:otherwise>
			<a href="/board/list?bgno=1&page=${param.page}"><button>목록으로</button></a>
		</c:otherwise>
	</c:choose>
	<c:if test="${auth.ID eq view.ID }">
		<a href="/board/edit/${view.NO }?bgno=${param.bgno}&page=${param.page}"><button>수정</button></a>
		<a><button id="del">삭제</button></a>
	</c:if>
	<hr/>
	<div id="view" style="width: 80%" align="left">
	</div>
	<div align="center" style="width: 100%; line-height: 30px;">
		<p>
			<input id="writer" type="hidden" value="${auth.ID }" placeholder="작성자" style="width: 40%;" />
		</p>
		<p>
			<textarea id="content" rows="3" placeholder="댓글내용" style="width: 50%; resize: none;" required></textarea>
			
		</p>
		<p>
			<button id="sbt" type="submit">댓글등록</button>
		</p>
	</div>
	<hr/>
</div>
<script>
	$("#exsave").click(function(){
		$("#ext").css("display","");
		$("#okbt").click(function(){
			$.ajax({
				"type" : "post",
				"async" : true,
				"url" : "/calendar/addList",
				"data" : {
					"title" : $("#extitle").val(), 
					"id" : "${auth.ID}",
					"exno" : "${exview.NO}",
					"exmu" : "${exview.EXMU}",
					"expart" : "${exview.EXPART}",
					"extime" : "${exview.EXTIME}",
					"excnt" : "${exview.EXCNT}",
					"exset" : "${exview.EXSET}"
				}
			}).done(function(r){
				if(r == "YYYYY"){
					window.alert("저장 완료~");
					$("#ext").css("display","none");
					$("#exsave").css("display","none");
				}else{
					window.alert("저장 실패~");
					$("#ext").css("display","none");
				};
			});
		})
	})

	$(function(){
		$(".form-control").attr("disabled",true);
		$('.form').attr("disabled", true);
		if("${exview.EXMU}" == "유산소"){
			$('#type01').attr("checked", true);
			$("#type02ctg").hide();
			$("#type01ctg").show();
			$('#sel1').val("${exview.EXPART}");
		}else{
			$('#type02').attr("checked", true);
			$("#type01ctg").hide();
			$("#type02ctg").show();
			$('#sel2').val("${exview.EXPART}");
		}
		$('#time').val("${exview.EXTIME}");
		$('#cnt').val("${exview.EXCNT}");
		$('#set').val("${exview.EXSET}");
	});
	
	$("#del").click(function(){
		if(confirm("정말 삭제 하시겠습니까?")){
			$.ajax({
				"type" : "post",
				"async" : true,
				"url" : "/board/delete/${view.NO}",
			}).done(function(r){
				if(r == "YYYY"){
					window.alert("삭제 완료~");
					$(location).attr('href', '/board/list?bgno=${param.bgno}&page=1');
				}else{
					window.alert("삭제 실패~");
				}
			});
		}
	});

	$("#rbt").click(function(){
		$.ajax({
			"type" : "post",	// default : "get"
			"async" : true,		// default : true
			"url" : "/board/recommend",
			"data" : {
				"bno" : $("#num").val(),
				"id" : "${auth.ID}"
			}
		}).done(function(r){
			if(r == "YYYY"){
				window.alert("추천 완료~");
				location.reload();
			}else{
				window.alert("이미 추천한 글입니다.");			
			};
		});
	});

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
	var rno;
	var list = function() {
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange= function(){
			if(this.readyState==4) {
				var obj = JSON.parse(this.responseText);
				var rst ="";
				for(i in obj){
					rst += "<pre><span><b>"+obj[i].ID+"</b></span> -<small>"+obj[i].CRDATE+"</small>";
					if(obj[i].ID == "${auth.ID}"){
						rst += "<a onclick=\"replyDelete("+obj[i].NO+");\"> 삭제 </a>";
					}
					rst += "<br/><span>"+obj[i].CONTENT+"</span></pre><br/>";
					rno = obj[i].NO;
				}
				document.getElementById("view").innerHTML = rst;
			}
		}
		xhr.open("post","/reply/list/${view.NO}", true);
		xhr.send();
	};
	list();
	//댓글 삭제
	function replyDelete(rno){
	    $.ajax({
	        url : "/reply/delete/"+rno,
	        type : 'post',
	        success : function(data){
	            if(data == "YYYY"){
	            	window.alert("댓글 삭제 완료~.");
	            	list(); 
	            }
	        }
	    });
	}
</script>