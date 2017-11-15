<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div align="center" style="color: gray">Project_H Copyright &copy; Project_H</div>
<script>
var ip = location.hostname;
var lws = new WebSocket("ws://"+ip+"/ws/login");

	lws.onmessage = function(e) {
		var obj = JSON.parse(e.data);

		switch (obj.mode) {
		case "confirm" :
			if (window.confirm(obj.content)) {
				location.href = obj.href;
			}
			break;
		case "alert" : 
			window.alert(obj.content)		
			break;
		}
	}
</script>