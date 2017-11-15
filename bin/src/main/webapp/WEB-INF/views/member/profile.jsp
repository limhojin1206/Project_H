<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="friendinfoview"></div>
<!-- 사진 -->
<div id="view">
	<img id="furl" style="width: 200; height: 200" class="img-circle" />
</div>
<br />
<!-- 개인정보 -->
<table class="table">
	<tbody>
		<tr>
			<td><b>ID</b></td>
			<td id="fid"></td>
		</tr>
		<tr>
			<td><b>Email</b></td>
			<td id="femail"></td>
		</tr>
		<tr>
			<td><b>GENDER</b></td>
			<td id="fgender"></td>
		</tr>
		<tr>
			<td><b>AGE</b></td>
			<td id="fage"></td>
		</tr>
	</tbody>
</table>
<!-- 친구 정보 모달-->
<script>
	$(".pbt").click(function(){
		//window.alert($(this).attr("role") + " 프로필 보기");
		$.ajax({
			"type" : "post",
			"async" : false,
			"url" : "/friend/info",
			"data" : {
				"ID" : $(this).attr("role")
			}
		}).done(function(map) {
			if(map.URL != null){
				$("#furl").attr("src",map.URL);
			}else{
				$("#furl").attr("src", "/profiles/default.png");
			}
			$("#fid").html(map.ID);
			$("#femail").html(map.EMAIL);
			$("#fgender").html(map.GENDER);
			$("#fage").html(map.AGE);
		});
	});
</script>
