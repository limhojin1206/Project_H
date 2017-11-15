<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 100%;
      height: 70%;
      margin: auto;
  }
  </style>
</head>
<body>

<div>
  <br>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
      <li data-target="#myCarousel" data-slide-to="4"></li>
      <li data-target="#myCarousel" data-slide-to="5"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">

      <div class="item active">
        <img src="/images/start02.png">
        <div class="carousel-caption"  style="color:black">
          <h3>스케줄 설정을 위한 D.U.Exercise 캘린더 사용법보기 (1)</h3>
          <p align="left">스케줄 설정을 하지 않은 기본 캘린더,①캘린더의 원하는 날짜의 칸을 눌렀을 경우 등록을 할 수 있으며 ②오른쪽의 일정 추가하기를 눌렀을 경우 
          	 오늘 날짜의 일정이 등록된다.</p>
        </div>
      </div>

      <div class="item">
        <img src="/images/today.PNG">
        <div class="carousel-caption"  style="color:black">
          <h3>스케줄 설정을 위한 D.U.Exercise 캘린더 사용법보기 (2)</h3>
          <p align="left">* 오늘 날짜포함 이후에 등록 된 일정은 <b style="color:#5bc0de">하늘색칸</b>으로 등록이 된다.</p>
        </div>
      </div>
    
      <div class="item">
        <img src="/images/pre.PNG">
        <div class="carousel-caption"  style="color:black">
          <h3>스케줄 설정을 위한 D.U.Exercise 캘린더 사용법보기 (3)</h3>
          <p align="left">* 오늘 날짜 이 전의 등록 된 일정은 <b style="color:#FF5252">빨간색칸</b>으로 등록이 된다.</p>
        </div>
      </div>

      <div class="item">
        <img src="/images/complete.PNG">
        <div class="carousel-caption"  style="color:black">
          <h3>스케줄 설정을 위한 D.U.Exercise 캘린더 사용법보기 (4)</h3>
          <p align="left">오늘 날짜 이 전의 등록 된 일정 중 달성 버튼을 눌렀을 경우 <b style="color:#66BB6A">녹색칸</b>으로 등록이 된다.</p>
        </div>
      </div>
      
      <div class="item">
        <img src="/images/final.PNG">
        <div class="carousel-caption"  style="color:black">
          <h3>스케줄 설정을 위한 D.U.Exercise 캘린더 사용법보기 (5)</h3>
          <p align="left">오늘 날짜 이후 일정(하늘색), 오늘 날짜 이전 일정(빨간색),</p> 
          <p align="left">오늘 날짜 이전 일정 중 달성이 된 일정(녹색)</p>
        </div>
      </div>
      
      <div class="item">
        <img src="/images/end01.PNG">
        <div class="carousel-caption"  style="color:black">
          <h3>스케줄 설정을 위한 D.U.Exercise 캘린더 사용법보기 (6)</h3>
          <p align="left">한달 스케줄을 설정하고 미달성, 달성, 앞으로의 스케줄 뷰</p>
          <p align="left">이전의 일정, 이후의 일정에 대하여 추가,삭제,달성 설정이 가능하다.</p>
          <p align="left">일, 주, 월별로 스케줄 확인 가능하다.</p>
        </div>
      </div>
  
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

</body>
</html>
