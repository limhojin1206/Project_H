package org.project.health.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
@SuppressWarnings("serial")
public class LoginAuthFilter extends HttpFilter  {
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request.getSession().getAttribute("auth") == null) {
			String uri = request.getRequestURI();
			if(uri.startsWith("/memo") ||uri.startsWith("/board") || uri.startsWith("/friend")) {
				String red= request.getRequestURI();
				if(request.getQueryString() != null) {
					red +="?"+request.getQueryString();
					System.out.println(red);
				}
				response.sendRedirect("/index?redirect="+red);
				System.out.println("red : " + red);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}