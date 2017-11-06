<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/resource/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

<!-- action : 에디터에 입력한 html 코드를 전달받을 Controller페이지 URL -->
<form action="/board/add?bgno=${param.bgno }" method="post" id="frm">
	<b>제목 </b><input type="text" name="title" style="margin: 8px;"/>
	<b>작성자 </b><input type="text" name="id" style="margin: 8px;" />
    <textarea name="content" id="editor" rows="10" cols="100" style="width:766px; height:412px;">${boardVO.content}</textarea>
    <input type="button" id="savebutton" value="글쓰기" style="margin-top: 8px;" />
</form>

<script>
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
	        //폼 submit
	        $("#frm").submit();
	    })
	})
</script>
