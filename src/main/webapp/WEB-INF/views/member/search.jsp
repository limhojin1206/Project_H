<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div align="center">
	<h3>친구찾기</h3>
	<small>찾고싶은 친구의 ID를 검색해보세요.</small>
	<p>
		<input type="text" id="srch"
			style="padding: 3px; width: 50%; font-size: 12pt;" />
	</p>
	<div id="srchlist"></div>
</div>
<%-- 
	select * from (select * from member where id like #{id} )  where rownum <= 3 
	// word+"%"; --%>
<script>
	// keypress , keydown, keyup
	$("#srch").keyup(function() {
		$("#srchlist").html("<h4>검색결과</h4>");
		if ($("#srch").val().length == 0)
			return;
		$.ajax({
			"url" : "/member/searchAjaxx",
			"data" : {
				"id" : $("#srch").val()
			}
		}).done(function(obj) {
			$("#srchlist").append(obj);
			/*
			for (var i = 0; i < obj.length; i++) {
				var t = "<b>" + obj[i].ID + "</b> ("
						+ obj[i].EMAIL
						+ ")<button>친구추가</button><br/>";
				$("#srchlist").append(t);
			}
			 */
		});
	});
</script>