<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
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
									required="required" style="width: 100%;"/>
							</c:when>
							<c:otherwise>
								<input type="text" name="receiver" value="${param.target }"
									required="required" style="width: 100%;"/>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="6" cols="50" name="content"
							placeholder="보내는 내용" style="resize: none"></textarea></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2" align="right"><button type="submit" style="width: 173px;">보내기</button></td>
				</tr>
			</tfoot>
		</table>
	</form>
</div>