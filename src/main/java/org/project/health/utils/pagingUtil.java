package org.project.health.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class pagingUtil {
	
	public Map list(int page, String id) {
		Map map = new HashMap<>();
		map.put("id", id);
		map.put("start", (page-1)*10 + 1);
		map.put("end", (page-1)*10 + 10);
		return map;
	}
	
	public Map paging(int page, int totalCount) {
		// 현재 페이지 번호
		//int page = (int)data.get("page");				// 입력 받아와야 함
		// 한 페이지에 출력될 게시물 수
		int countList = 10;
		// 한 화면에 출력될 페이지 수
		int countPage = 10;
		// 전체 글 수
		//int totalCount = (int)data.get("totalCount");	// 입력 받아와야 함
		// 전체 페이지 수
		int totalPage = totalCount / countList;
		if (totalCount % countList > 0) {
		    totalPage++;
		}
		if (totalPage < page) {
		    page = totalPage;
		}

		// 시작 페이지 번호
		int startPage = ((page - 1) / 10) * 10 + 1;
		// 끝 페이지 번호
		int endPage = startPage + countPage - 1;
		if (endPage > totalPage) {
		    endPage = totalPage;
		}

		// 전송용 Map
		Map map = new HashMap<>();
			map.put("page", page);				// 현재 페이지
			map.put("startPage", startPage);	// 페이징 첫페이지
			map.put("endPage", endPage);		// 페이징 끝페이지
			map.put("totalPage", totalPage);	// 전체 페이지
			
		/*
		 *  	뷰페이지 부분
		
		<!-- 시작 페이지 -->
		<c:if test="${viewpage.startPage > 1}">
			<a href="?page=1">처음</a>
		</c:if>
		<!-- 이전 페이지 -->
			<c:if test="${viewpage.page > 1}">
				<a href="?page=${viewpage.page -1 }">◀</a>
			</c:if>
		<!-- 현재 페이지 -->
			<c:forEach var="t" begin="${viewpage.startPage }" end="${viewpage.endPage }" >
				<c:choose>
				<c:when test="${t eq viewpage.page }">
					<b>${t }</b>
				</c:when>
				<c:otherwise>
					<a href="?page=${t }">${t }</a>
				</c:otherwise>
				</c:choose>
			</c:forEach>
		<!-- 다음 페이지 -->
			<c:if test="${viewpage.page < viewpage.totalPage}">
				<a href="?page=${viewpage.page + 1 }">▶</a>
			</c:if>
		<!-- 끝 페이지 -->
		<c:if test="${viewpage.endPage < viewpage.totalPage}">
			<a href="?page=${viewpage.totalPage}">끝</a>
		</c:if>
		 */
		
		
		return map;
	}
}
