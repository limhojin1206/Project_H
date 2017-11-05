package org.project.health.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.project.health.models.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;

@WebFilter(filterName="auto")
public class LoginCookieFilter extends HttpFilter {
	@Autowired
	MemberDao mdao;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.isNew()) {
			Cookie[] ar = request.getCookies();
			ar = (ar == null) ? new Cookie[0] : ar;
			for (Cookie t : ar) {
				String name = t.getName();
				String value = t.getValue();
				if ("auto".equals(name)) {
					System.out.println("자동로그인 접근");
					System.out.println("value : " + value);
					session.setAttribute("auth_id", value);
					break;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}
