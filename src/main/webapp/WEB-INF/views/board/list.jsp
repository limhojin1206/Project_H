<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div align="center">
	<table class="table">
		<thead>
			<tr>
				<th style="width: 10%">번호</th>
				<th style="width: 40%">제목</th>
				<th style="width: 10%">작성자</th>
				<th style="width: 20%">작성일</th>
				<th style="width: 10%">조회수</th>
				<th style="width: 10%">추천수</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${!empty noticeList and param.page eq 1 and param.bgno eq 1}">
			<c:forEach var="obj" items="${noticeList }">
				<tr class="danger">
					<td><span class="label label-danger">공지</span></td>
					<c:if test="${fn:length(obj.TITLE) ge 17 }">
						<c:set target="${obj }" property="TITLE" value="${fn:substring(obj.TITLE, 0, 17) }.." />
					</c:if>
					<td><a href="/board/view/${obj.NO}?bgno=0&page=${param.page}&mode=free"><b style="color: red;">${obj.TITLE }</b></a> <c:if test="${obj.C ne null }">[${obj.C }]</c:if></td>
					<td>
						<c:choose>
							<c:when test="${auth.ID ne obj.ID }">
								<a href="/memo/send?target=${obj.ID }">${obj.ID }</a>
							</c:when>
							<c:otherwise>
								${obj.ID }
							</c:otherwise>
						</c:choose>
					</td>
					<td><fmt:formatDate value="${obj.WDATE }" pattern="yyyy.MM.dd HH:mm"/></td>
					<td><fmt:formatNumber value="${obj.CNT }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${obj.RECOMMEND }" pattern="#,###" /></td>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${!empty recommendList and param.page eq 1 and param.bgno eq 1}">
			<c:forEach var="obj" items="${recommendList }">
				<tr class="active">
					<td><span class="label label-default">추천</span></td>
					<c:if test="${fn:length(obj.TITLE) ge 17 }">
						<c:set target="${obj }" property="TITLE" value="${fn:substring(obj.TITLE, 0, 17) }.." />
					</c:if>
					<td><a href="/board/view/${obj.NO}?bgno=${param.bgno }&page=${param.page}"><b style="color: gray;">${obj.TITLE }</b></a> <c:if test="${obj.C ne null }">[${obj.C }]</c:if></td>
					<td>
						<c:choose>
							<c:when test="${auth.ID ne obj.ID }">
								<a href="/memo/send?target=${obj.ID }">${obj.ID }</a>
							</c:when>
							<c:otherwise>
								${obj.ID }
							</c:otherwise>
						</c:choose>
					</td>
					<td><fmt:formatDate value="${obj.WDATE }" pattern="yyyy.MM.dd HH:mm"/></td>
					<td><fmt:formatNumber value="${obj.CNT }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${obj.RECOMMEND }" pattern="#,###" /></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:forEach var="obj" items="${list }">
			<c:choose>
				<c:when test="${param.bgno eq 0}">
					<tr class="danger">
					<td><span class="label label-danger">공지</span></td>
					<c:if test="${fn:length(obj.TITLE) ge 17 }">
						<c:set target="${obj }" property="TITLE" value="${fn:substring(obj.TITLE, 0, 17) }.." />
					</c:if>
					<c:forEach var="w" items="${searchs }">
						<c:set var="ch" value="<b>${w }</b>"/>
						<c:set target="${obj }" property="TITLE" value="${fn:replace(obj.TITLE, w, ch) }" />
					</c:forEach>
					<td><a href="/board/view/${obj.NO}?bgno=${param.bgno }&page=${param.page}"><b style="color: red;">${obj.TITLE }</b></a> <c:if test="${obj.C ne null }">[${obj.C }]</c:if></td>
										<!-- 
					<td><a href="/board/view/${obj.NO}">${fn:substring(obj.TITLE, 0, 12) }</a> <c:if test="${obj.C ne null }">[${obj.C }]</c:if></td>
					 -->
					<td>
						<c:choose>
							<c:when test="${auth.ID ne obj.ID }">
								<a href="/memo/send?target=${obj.ID }">${obj.ID }</a>
							</c:when>
							<c:otherwise>
								${obj.ID }
							</c:otherwise>
						</c:choose>
					</td>
					<td><fmt:formatDate value="${obj.WDATE }" pattern="yyyy.MM.dd HH:mm"/></td>
					<td><fmt:formatNumber value="${obj.CNT }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${obj.RECOMMEND }" pattern="#,###" /></td>
				</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td>${obj.NO }</td>
					<c:if test="${fn:length(obj.TITLE) ge 17 }">
						<c:set target="${obj }" property="TITLE" value="${fn:substring(obj.TITLE, 0, 17) }.." />
					</c:if>
					<c:forEach var="w" items="${searchs }">
						<c:set var="ch" value="<b>${w }</b>"/>
						<c:set target="${obj }" property="TITLE" value="${fn:replace(obj.TITLE, w, ch) }" />
					</c:forEach>
					<td><a href="/board/view/${obj.NO}?bgno=${param.bgno }&page=${param.page}">${obj.TITLE }</a> <c:if test="${obj.C ne null }">[${obj.C }]</c:if></td>
					<!-- 
					<td><a href="/board/view/${obj.NO}">${fn:substring(obj.TITLE, 0, 12) }</a> <c:if test="${obj.C ne null }">[${obj.C }]</c:if></td>
					 -->
					<td>
						<c:choose>
							<c:when test="${auth.ID ne obj.ID }">
								<a href="/memo/send?target=${obj.ID }">${obj.ID }</a>
							</c:when>
							<c:otherwise>
								${obj.ID }
							</c:otherwise>
						</c:choose>
					</td>
					<td><fmt:formatDate value="${obj.WDATE }" pattern="yyyy.MM.dd HH:mm"/></td>
					<td><fmt:formatNumber value="${obj.CNT }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${obj.RECOMMEND }" pattern="#,###" /></td>
				</tr>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${param.bgno eq 1 or (auth.ID eq 'asd' and param.bgno eq 0)}">
		<p align="right" style="margin-right: 30px;">
			<a href="/board/add?bgno=${param.bgno }&page=${param.page}"><button type="button">글작성</button></a>
		</p>
	</c:if>
	<div>
		<%-- 
		<!-- 현재 페이지 블럭이 1보다 크면 처음으로 이동 -->
                <c:if test="${curBlock > 1}">
                    <a href="/board/list?page=1">[처음]</a>
                </c:if>
                <!-- 현재 페이지 블럭이 1보다 크면 이전 페이지 블럭으로 이동 -->
                <c:if test="${curBlock > 1}">
                    <a href="/board/list?page=${prevPage}">[이전]</a>
                </c:if>
                <!-- 페이지 블럭 처음부터 마지막 블럭까지 1씩 증가하는 페이지 출력 -->
                <c:forEach var="num" begin="${pb}" end="${pe}">
                    <c:choose>
                        <c:when test="${num == param.page}">
                            ${num}&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="/board/list?page=${num}">${num}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <!-- 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 다음페이지로 이동 -->
                <c:if test="${curBlock <= totBlock}">
                    <a href="/board/list?page=${nextPage}">[다음]</a>
                </c:if>
                <!-- 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 끝으로 이동 -->
                <c:if test="${curBlock <= totBlock}">
                    <a href="/board/list?page=${last}">[끝]</a>
                </c:if>
             --%>   
 
		<c:if test="${curBlock > 1}">
			<c:choose>
				<c:when test="${empty mode }">
					<a href="/board/list?bgno=${param.bgno }&page=1" style="text-decoration: none"><b>[처음]</b></a>
					<a href="/board/list?bgno=${param.bgno }&page=${prevPage}" style="text-decoration: none">
						<b>◀ 이전</b></a>
				</c:when>
				<c:otherwise>
					<a href="/board/search?bgno=${param.bgno }&page=1&type=${mode.type}&search=${mode.search}" style="text-decoration: none"><b>[처음]</b></a>
					<a href="/board/search?bgno=${param.bgno }&page=${prevPage}&type=${mode.type}&search=${mode.search}" style="text-decoration: none">
						<b>◀ 이전</b></a>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:forEach var="i" begin="${pb }" end="${pe }" varStatus="vs">
			<c:choose>
				<c:when test="${i eq param.page }">
					<b>${i }</b>
				</c:when>
				<c:when test="${!empty mode }">
					<a href="/board/search?bgno=${param.bgno }&page=${i }&type=${mode.type}&search=${mode.search}" style="text-decoration: none">${i }</a>
				</c:when>
				<c:otherwise>
					<a href="/board/list?bgno=${param.bgno }&page=${i }" style="text-decoration: none">${i }</a>
				</c:otherwise>
			</c:choose>
			<c:if test="${!vs.last }">|</c:if>
		</c:forEach>
		<c:if test="${curBlock <= totBlock}">
			<c:choose>
				<c:when test="${empty mode }">
					<a href="/board/list?bgno=${param.bgno }&page=${nextPage}" style="text-decoration: none">
						<b>다음 ▶</b></a>	
					<a href="/board/list?bgno=${param.bgno }&page=${last}" style="text-decoration: none"><b>[끝]</b></a>
				</c:when>
				<c:otherwise>
					<a href="/board/search?bgno=${param.bgno }&page=${nextPage}&type=${mode.type}&search=${mode.search}" style="text-decoration: none">
						<b>다음 ▶</b></a>
					<a href="/board/search?bgno=${param.bgno }&page=${last}&type=${mode.type}&search=${mode.search}" style="text-decoration: none"><b>[끝]</b></a>
				</c:otherwise>
			</c:choose>
		</c:if>
 
	</div>
	<div>
		<form action="/board/search?bgno=${param.bgno }&page=1" method="post">
			<select name="type" style="size: 10px;">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="id">ID</option>
			</select>
			<input id="search" name="search" type="text" value="${search }">
			<button type="submit">검색</button>
		</form>
	</div>
</div>