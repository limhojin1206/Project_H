<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='/fullcalendar/locale-all.js'></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/fullcalendar/gcal.js"></script>
<link href="/fullcalendar/fullcalendar.css" rel="stylesheet" />


<script>
	$(document).ready(function ajaxtest() {
	/*	var exmu;
		$.ajax({
			url: '/calendar/view',
	        type: 'POST',
	        data: {
	            custom_param1: 'something',
	            custom_param2: 'somethingelse'
	        },
	        error: function() {
	            alert('there was an error while fetching events!');
	        }
	        
		  }).done(function(response,event,view) {
			    // Parse our events into an event object for FullCalendar
			  // console.log(exmu);
			    var events = [];
			    $.each(response, function(idx, e) {
			      events.push({
			        start: e.start,
			        end: e.end,
			        title: e.title,
			        id: e.id
			      });
		  });	*/
			    $('#calendar').fullCalendar({
					header : {
						left : 'prev,next today',
						center : 'title',
						right : 'month,basicWeek,basicDay'
					},
					locale : 'ko',
					navLinks : true, // can click day/week names to navigate views
					editable : true,
					googleCalendarApiKey : "AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE",
					
					eventLimit : true, // allow "more" link when too many events
					//eventClick : function(event, element) {

					//	//event.title = "CLICKED!";
					//	open('/calendar/add', 'add', 'width=500, height=500');

					//	$('#calendar').fullCalendar('updateEvent', event);

					//},
					eventSources : [
		                // 대한민국의 공휴일
		                {
		                      googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com"
		                    , className : "koHolidays"
		                    , color : "#FF0000"
		                    , textColor : "#FFFFFF"
		                }
				],
				   loading:function(bool) {
		              jQuery("#loading").toggle(bool);
		          },
					eventClick:  function(event, jsEvent, view) {
						var no = event.id;
						var title = event.title;
						var start = event.start;
						var end = event.end;
						//console.log(end);
						$.ajax({
							url: '/calendar/view/'+no,
					        type: 'POST',
					        error: function() {
					            alert('there was an error while fetching events!');
					        }
					        
						  }).done(function(response,event,view) {
				              $('#modalTitle').html(response.EXNAME);
				              $('#type').html(response.EXMU);
				              $('#sel1').html(response.EXPART);
				              $('#time').html(response.EXTIME);
				              $('#cnt').html(response.EXCNT);
				              $('#set').html(response.EXSET);
				              $('#start').val(response.EXST);
				              $('#end').val(response.EXED);
				              
				              $('#eventUrl').attr('href',event.url);
				              $('#viewModal').modal();
				              //console.log(event);
					          		
						  });
				              $("#ebt").click(function(){
				            	  $.ajax({
										url: '/calendar/view/'+no,
								        type: 'POST',
								        error: function() {
								            alert('there was an error while fetching events!');
								        }
								        
									  }).done(function(response,event,view) {
						            	  $("#viewModal .close").click();
						            	  alert(response.EXMU);
						            	  $('#type01').html(response.EXMU);
							              $('#sel1').html(response.EXPART);
							              $('#time').html(response.EXTIME);
							              $('#cnt').val(response.EXCNT);
							              $('#set').val(response.EXSET);
							              $('#start').val(response.EXST);
							              $('#end').val(response.EXED);
						            	  $('#addModal').modal();
							             								          		
									  });
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
				          				$("#viewModal .close").click();
				          				location.reload();
				          			}
				          		});
							 }else{
								 return;
							 }
				          	});
						 
						
		          },
					dayClick : function(date, jsEvent, view) {
						$('#modalTitle').html(event.title);
						$('#modalBody').html(event.description);
			            $('#eventUrl').attr('href',event.url);
						$('#start').attr('value', date.format());
						$('#end').attr('value', date.format());
			            $('#addModal').modal();
			            
			            alert('Clicked on: ' + date.format());

						//alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);

						//alert('Current view: ' + view.name);

						// change the day's background color just for fun
						//$(this).css('background-color', 'blue');

						//open('/calendar/add', 'add', 'width=500, height=500');

					},
					events: { url: '/calendar/view',
						type: 'post',
					color: 'yellow',   // a non-ajax option
			        textColor: 'black'}, // a non-ajax option
				
					eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) { 
						alert( event.title + " was moved " + dayDelta + " days and " + minuteDelta + " minutes." ); 
						if (allDay) {
							alert("Event is now all-day"); 
						}else{
							alert("Event has a time-of-day"); 
						} if (!confirm("Are you sure about this change?")) {
							revertFunc(); 
						} 
					}
				});
		  });

		
	//});
	
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
	<div id="viewModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="modalTitle" class="modal-title"></h4>
				</div>
				<div id="modalBody" class="modal-body">
					<div class="col-sm-1">
						<b>Exercise:</b>
					</div>
					<div class="col-sm-2">
						<br />
						<div class="radio">
							<span id="type"></span>
						</div>
					</div>
					<div class="col-sm-6">
						<br />
						<div class="type" id="type01ctg">5대 유산소 운동 <br /> 
							<span class="form-control" id="sel1"></span>
						</div>
					</div>
					<div class="col-sm-3">
						<div>
							시간 <span class="form-control" id="time">
							</span>
						</div>
						<div>
							횟수 <span class="form-control" id="cnt">
							</span>
						</div>
						<div>
							세트수 <span class="form-control" id="set">
							</span>
						</div>
					</div>

					<p>
						<b>startDate :</b> <input type="date" id="start" readonly="readonly">
					</p>
					<p>
						<b>endDate :</b> <input type="date" id="end" readonly="readonly">
					</p>
					<p>
						<b>Content :</b><br />
						<textarea id="content" cols="60" rows="7" readonly="readonly"></textarea>
					</p>
				</div>
				<div class="modal-footer">
					<button id="ebt" type="button" class="btn btn-default">edit</button>
					<button id="dbt" type="button" class="btn btn-default">delete</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div id="addModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="modalTitle" class="modal-title">일정 추가</h4>
				</div>
				<div id="modalBody" class="modal-body">
										<p>
						<b>Title :</b> <input id="title" type="text">
					</p>
					<div class="col-sm-1">
						<b>Exercise:</b>
					</div>
					<div class="col-sm-2">
						<br />
						<div class="radio">
							<label><input type="radio" name="optradio" id="type01" checked>유산소</label>
						</div>
						<div class="radio">
							<label><input type="radio" name="optradio" id="type02">근력</label>
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
							시간 <select class="form-control" id="time">
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
						<b>startDate :</b> <input type="date" id="start">
					</p>
					<p>
						<b>endDate :</b> <input type="date" id="end">
					</p>
					<p>
						<b>Content :</b><br />
						<textarea id="content" cols="60" rows="7"></textarea>
					</p>

			
				</div>
				<div class="modal-footer">
					<button id="bt" type="button" class="btn btn-default">Save</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	$("#bt").click(function() {
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
				"set" : $("#set").val() 
			};
		console.log(data);
		$.ajax({
			"type" : "post",
			"async" : true,
			"url" : "/calendar/add",
			"data" : data
		}).done(function(r) {
			if (r == "YYYY") {
				alert("일정 추가 완료~");
				$("#addModal .close").click();
				location.reload();
			}
		});
	});
	var list = function() {
		$.ajax({
			"type" : "post",
			"async" : true,
			"url" : "/calendar/view",
		}).done(function(r) {
			var obj = JSON.stringify(r);
			alert(obj);
		});
	};
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
