<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse">
	<ul class="nav navbar-nav">
		<div class="container-fluid" align="center">
			<li><a href="/my/info" style="color: silver;">내 정보</a></li>
			<br />
			<li><a href="/#" style="color: silver;">저장한 운동법</a></li>
			<br />
			<li><a href="/friend/friendlist" style="color: silver;">내 친구</a></li>
			<br />
			<li><a href="/friend/makefriend" style="color: silver;">요청받은 친구</a></li>
			<br />
			<li><a href="/memo/receivebox" style="color: silver;">쪽지함</a></li>
			<br />
			<li></li>
			<br />
			<li id="drop" style="color: silver;">회원탈퇴</li>
			<br />
		</div>
	</ul>
</nav>
<script>
$("#drop").click(function() {
	if(window.confirm("정말 탈퇴하시겠습니까?")){
		
		
	}
});

</script>