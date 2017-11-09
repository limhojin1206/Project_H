<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="/resource/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

<!-- action : 에디터에 입력한 html 코드를 전달받을 Controller페이지 URL -->
<form action="/board/add?bgno=${param.bgno }" method="post" id="frm">
	<b>제목 </b><input type="text" name="title" style="margin: 8px;" required="required" /> <input
		type="hidden" name="id" value="${auth.ID }" style="margin: 8px;" />
	<textarea name="content" id="editor" rows="10" cols="100"
		style="width: 766px; height: 412px;">${boardVO.content}</textarea>
	<c:if test="${!empty data or param.bgno eq 2 }">
	<hr />
	<h3><span class="label label-info">운동법</span></h3>
	<input type="hidden" name="exno" value="${data.NO }">
	<div class="row" style="width: 95%;">
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
	<hr/>
	</c:if>
	<div>
		<input type="button" id="savebutton" value="글쓰기"
			style="margin-top: 8px;" />
		<c:choose>
			<c:when test="${!empty data or param.bgno eq 2 }">
				<a href="/calendar/view"><button type="button">취소</button></a>
			</c:when>
			<c:otherwise>
				<a href="/board/list?bgno=${param.bgno }&page=${param.page}"><button type="button">취소</button></a>
			</c:otherwise>
		</c:choose>
	</div>
	
</form>

<script>
	$(function(){
		$("#type01").change(function() {
			if ($("#type01").prop("checked")) {
				$("#type01ctg").show();
				$("#type02ctg").hide();
			} else {
				$("#type01ctg").hide();
			}
		});
		$("#type02").change(function() {
			if ($("#type02").prop("checked")) {
				$("#type02ctg").show();
				$("#type01ctg").hide();
			} else {
				$("#type02ctg").hide();
			}
		});
		if(!"${data.EXMU}" == ""){
			if("${data.EXMU}" == "유산소"){
				$('#type01').attr("checked", true);
				$("#type02ctg").hide();
				$("#type01ctg").show();
				$('#sel1').val("${data.EXPART}");
			}else{
				$('#type02').attr("checked", true);
				$("#type01ctg").hide();
				$("#type02ctg").show();
				$('#sel2').val("${data.EXPART}");
			}
			$("#time").val("${data.EXTIME}");
			$("#cnt").val("${data.EXCNT}");
			$("#set").val("${data.EXSET}");
			$(".form-control").attr("disabled",true);
			$('.form').attr("disabled", true);
		}
	});

	$(function() {
		//전역변수
		var obj = [];
		//스마트에디터 프레임생성
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : obj,
			elPlaceHolder : "editor",
			sSkinURI : "/resource/editor/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,
			}
		});
		//전송버튼
		$("#savebutton").click(function() {
			//id가 smarteditor인 textarea에 에디터에서 대입
			obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
			//폼 submit
			$("#frm").submit();
		})
	})
</script>
