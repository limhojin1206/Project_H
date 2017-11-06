<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script>
	var url;
	var start;
	var end;
	$(document).ready(function() {
		$('#calendar').fullCalendar({
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,basicWeek,basicDay'
			},
			locale : 'ko',
			navLinks : true,
			editable : true,
			googleCalendarApiKey : "AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE",
			eventLimit : true,
			eventSources : [{
			// 대한민국의 공휴일
				googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com"
				, className : "koHolidays"
				, color : "#FF0000"
				, textColor : "#FFFFFF"
			}],
			loading: function(bool) {
				jQuery("#loading").toggle(bool);
			},
			eventClick: function(event, jsEvent, view) {
				var no = event.id;
				$.ajax({
					url: '/calendar/view/'+no,
					type: 'POST',
					error: function() {
						alert('there was an error while fetching events!');
					}
				}).done(function(r) {
					if(r.EXCMPLT == "n"){
						$("#obt").css("display","");
					}else{
						$("#success").css("display","");
					}
					$("#bt").css("display","none");
					$("#dbt").css("display","");
					$("#ebt").css("display","");
					$(".form-control").attr("disabled",true);
					$('.form').attr("disabled", true);
					$('#modalTitle').html("상세보기");
					$('#title').val(r.EXNAME);
					type = r.EXMU;
					if(r.EXMU == "유산소"){
						$('#type01').attr("checked", true);
						$("#type02ctg").hide();
						$("#type01ctg").show();
						$('#sel1').val(part = r.EXPART);
					}else{
						$('#type02').attr("checked", true);
						$("#type01ctg").hide();
						$("#type02ctg").show();
						$('#sel2').val(part = r.EXPART);
					}
					$('#time').val(time = r.EXTIME);
					$('#cnt').val(cnt = r.EXCNT);
					$('#set').val(set = r.EXSET);
					$('#start').val(start = r.EXST);
					$('#end').val(end = r.EXED);
					$('#content').val(content = r.CONTENT);
					$('#addModal').modal();
					$("#cbt").click(function(){
						location.reload();
					});
					$("#ebt").click(function(){
						$("#bt").css("display","none");
						$("#dbt").css("display","none");
						$("#ebt").css("display","none");
						$("#obt").css("display","none");
						$("#sbt").css("display","");
						$(".form-control").attr("disabled",false);
						$('.form').attr("disabled", false);
						$('#modalTitle').html("수정");
						url = '/calendar/edit/'+no;
						console.log(url);
					});
				});
				$("#sbt").click(function(){
					if(confirm("정말 일정을 수정 하시겠습니까?")){
						$("#bt").click();
					}
				});
				$("#dbt").click(function(){
					if(confirm("정말 일정을 삭제 하시겠습니까?")){
						$.ajax({
						"type" : "post",
						"async" : true,
						"url" : "/calendar/delete/"+no,
						}).done(function(r) {
							if (r == "YYYY") {
								alert("일정 삭제 완료~");
								$("#addModal .close").click();
								location.reload();
							}else{
								alert("일정 삭제 실패~");
							}
						});
					}
				});
				$("#obt").click(function(){
					$.ajax({
						"type" : "post",
						"async" : true,
						"url" : "/calendar/success/"+no,
					}).done(function(r) {
						if (r == "YYYY") {
							alert("달성 완료~");
							$("#addModal .close").click();
							location.reload();
						}else{
							alert("달성 실패~");
						}
					});
				});
			},
			dayClick : function(date, jsEvent, view) {
				url = "/calendar/add";
				$('#modalTitle').html("일정 추가");
				$('#start').attr('value', date.format());
				$('#end').attr('value', date.format());
				start = date.format();
				end = date.format();
				$('#addModal').modal();
				$("#cbt").click(function(){
					location.reload();
				})
			},
			events: {
				url: '/calendar/view',
				type: 'post',
				color: 'yellow',   
				textColor: 'black'
			}, 
		});
	});
</script>
<style type="text/css">
.fc-sun {
	color: red !important;
}

.fc-sat {
	color: blue !important;
}

body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}

.fc-day-number.fc-sat.fc-past {
	color: #0000FF;
} /* 토요일 */
.fc-day-number.fc-sun.fc-past {
	color: #FF0000;
} /* 일요일 */
</style>
</head>
<body>
	<div id="loading">loading...</div>

	<div id='calendar'></div>

	<!-- Modal -->
	<div id="addModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="modalTitle" class="modal-title"></h4><span id="success" class="label label-success" style="display: none">달성!</span>
				</div>
				<div id="modalBody" class="modal-body">
										<p>
						<b>Title :</b> <input id="title" type="text" class="form-control">
					</p>
					<div class="col-sm-1">
						<b>Exercise:</b>
					</div>
					<div class="col-sm-2">
						<br />
						<div class="radio">
							<label><input type="radio" name="optradio" id="type01" class="form" checked>유산소</label>
						</div>
						<div class="radio">
							<label><input type="radio" name="optradio" id="type02" class="form">근력</label>
						</div>
					</div>

					<div class="col-sm-6">
						<br />
						<div class="type" id="type01ctg">5대 유산소 운동 <br /> 
							<select class="form-control" id="sel1" name="oxygen">
								<option selected="selected">걷기</option>
								<option>달리기</option>
								<option>줄넘기</option>
								<option>수영</option>
								<option>자전거</option>
							</select>
						</div>
						<div class="type" style="display: none" id="type02ctg">10개 근력 운동<br /> 
							<select class="form-control" id="sel2" name="muscle">
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
					<div class="col-sm-3">
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
					<p>
						<b>startDate :</b> <input type="date" id="start" class="form-control">
					</p>
					<p>
						<b>endDate :</b> <input type="date" id="end" class="form-control">
					</p>
					<p>
						<b>Content :</b><br />
						<textarea id="content" cols="60" rows="7" class="form-control" style="resize: none"></textarea>
					</p>

			
				</div>
				<div class="modal-footer">
					<button id="obt" type="button" class="btn btn-default" style="display: none;">달성</button>
					<button id="ebt" type="button" class="btn btn-default" style="display: none;">edit</button>
					<button id="dbt" type="button" class="btn btn-default" style="display: none;">delete</button>
					<button id="bt" type="button" class="btn btn-default">Save</button>
					<button id="sbt" type="button" class="btn btn-default" style="display: none;">Save</button>
					<button id="cbt" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
<script>
	$("#bt").click(function () {
		var type;
		var part;
		if($("#type01").prop("checked")) {
			type="유산소";
			part=$("#sel1").val();
		}else {
			type="근력";
			part=$("#sel2").val();
		}
		var data = {
				"title" : $("#title").val(),
				"type" : type,
				"part" : part,
				"cnt" : $("#cnt").val(),
				"time" : $("#time").val(),
				"start" : $("#start").val(),
				"end" : $("#end").val(),
				"set" : $("#set").val(),
				"content" : $("#content").val()
			};
		//console.log(data);
		$.ajax({
			"type" : "post",
			"async" : true,
			"url" : url,
			"data" : data
		}).done(function(r) {
			if (r == "YYYY") {
				alert("일정 추가 완료~");
				$("#addModal .close").click();
				location.reload();
			}
			if (r == "YYYYY") {
				alert("수정 완료~");
				$("#addModal .close").click();
				location.reload();
			}
		});
	});
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
</script>
</body>
</html>
