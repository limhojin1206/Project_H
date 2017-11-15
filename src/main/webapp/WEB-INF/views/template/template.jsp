<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>D.U.EXERCISE</title>
<!-- JQUERY -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- JQUERY - ui -->
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- fullcalendar -->
<link href='/fullcalendar/fullcalendar.min.css' rel='stylesheet' />
<link href='/fullcalendar/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='/fullcalendar/lib/moment.min.js'></script>
<script src='/fullcalendar/lib/jquery.min.js'></script>
<script src='/fullcalendar/fullcalendar.min.js'></script>

<script src='/fullcalendar/locale-all.js'></script>
<script type="text/javascript" src="/fullcalendar/gcal.js"></script>
<link href="/fullcalendar/fullcalendar.css" rel="stylesheet" />

<!-- BootStrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container" style="width:80%;">
	<header>
		<t:insertAttribute name="header"/>
	</header>
	<nav class="navbar navbar-inverse">
		<t:insertAttribute name="nav"/>
	</nav>
	<section style="min-height: 70%">
		<t:insertAttribute name="section"/>
	</section>
	<footer>
		<t:insertAttribute name="footer"/>
	</footer>
	</div>
</body>
</html>

