<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/resource/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

<form action="/board/edit" method="post" id="frm">
	<input type="text" id="no" name="no" value="${view.NO }" hidden="" />
	<b>제목 </b><input type="text" id="title" name="title" value="${view.TITLE }" style="margin: 8px;" /> 
	<b>작성자 </b><input type="text" id="id" name="id" value="${view.ID }" style="margin: 8px;" />
    <textarea name="content" id="editor" rows="10" cols="100" style="width:766px; height:412px;">${view.CONTENT }</textarea>
    <input type="button" id="savebutton" value="수정" style="margin-top: 8px;" />
	<button id="bt" type="button">목록</button>
</form>
<script>
	$("#bt").click(function(){
		$(location).attr('href', '/board/list?bgno=${param.bgno}&page=1');
	});
	
	$(function(){
	    //전역변수
	    var obj = [];              
	    //스마트에디터 프레임생성
	    nhn.husky.EZCreator.createInIFrame({
	        oAppRef: obj,
	        elPlaceHolder: "editor",
	        sSkinURI: "/resource/editor/SmartEditor2Skin.html",
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
	    $("#savebutton").click(function(){
	    	//id가 smarteditor인 textarea에 에디터에서 대입
	        obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
	        if(confirm("정말 수정 하시겠습니까?")){
				$.ajax({
					"type" : "post",
					"async" : true,
					"url" : "/board/edit",
					"data" : {
						"no" : $("#no").val(),
						"title" : $("#title").val(),
						"id" : $("#id").val(),
						"content" : $("#editor").val()
					}
				}).done(function(r){
					if(r == "YYYY"){
						window.alert("수정 완료~");
						$(location).attr('href', '/board/view/${view.NO}?bgno=${param.bgno}');
					}else{
						window.alert("수정 실패~");
					}
				});
			}
	    	//폼 submit
	        //$("#frm").submit();
	    })
	})
</script>