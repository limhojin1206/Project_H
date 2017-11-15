<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<h2>쪽지 보내기</h2>
	<form action="/memo/send" method="post">
		<input type="hidden" name="sender" value="${auth.ID}"/>
		<table>
			<tbody>
				<tr>
					<td><b>받는 사람</b></td>
					<td align="right"><c:choose>
							<c:when test="${empty param.target }">
								<input type="text" name="receiver" placeholder="받는 사람"
									required="required" style="width: 100%;" id="receiver"/>
							</c:when>
							<c:otherwise>
								<input type="text" name="receiver" value="${param.target }" placeholder="받는 사람"
									required="required" style="width: 100%;" id="receiver"/>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="6" cols="50" name="content"
							placeholder="보내는 내용" style="resize: none" id="ta"></textarea></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td><button type="submit" style="width: 173px;display: none" id="sendbt">보내기</button></td>
					<td align="right">
						<span id="sendlength" >0</span> / 200<br/>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
		<div>
			<button type="button" style="width: 173px;" id="idck">보내기</button>
		</div>
</div>
<script>
$("#idck").click(function(){
	//window.alert($("#receiver").val());
	$.ajax({
		"type" : "post",
		"async" : false,
		"url" : "/member/signup_check/id",
		"data" : {
			"param" : $("#receiver").val()
		}
		//왜 데이터 반대쪽에 "="이 붙는가?
	}).done(function(r) {
		//window.alert("r : " +r);
		if(r=="true"){
			window.alert("전송성공");
			$("#sendbt").trigger('click');
		}else{
			window.alert("탈퇴하거나 없는 아이디 입니다.");
			$("#receiver").val("");
		}
	});
});

$("#ta").keyup(function(){
	var ta = $("#ta").val().length
	$("#sendlength").html(ta);
	if(ta > 200){
		window.alert("200자 이상 작성 불가");
		$("#ta").val($("#ta").val().substring(0,200));
		$("#sendlength").html($("#ta").val().length);
	}
});

</script>