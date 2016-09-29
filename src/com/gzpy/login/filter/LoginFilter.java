package com.gzpy.login.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gzpy.user.entity.User;

public class LoginFilter implements Filter {
	
	public void destroy() {
	}

	public void init(FilterConfig config) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRsp = (HttpServletResponse) response;
		String uri = httpReq.getRequestURI();
		System.out.println(uri);
		User user = null;
		HttpSession session = httpReq.getSession(false);
		boolean isFilterdUrl = this.isFilterdUrls(httpReq, uri);
		if (session != null)
			user = (User) session.getAttribute("currentUser");

		if (isFilterdUrl && user == null) {
			httpRsp.sendRedirect(httpReq.getContextPath());
			return;
		} 
		
		if ((httpReq.getContextPath() + "/").equals(uri) && user != null){
			httpRsp.sendRedirect(httpReq.getContextPath() + "/goIndex.do");
			return;
		}

		chain.doFilter(request, response);

	}
	
	private boolean isFilterdUrls(HttpServletRequest httpReq,String uri){
		if((httpReq.getContextPath() + "/").equals(uri)){
			return false;
		}
		
		uri = uri.substring(httpReq.getContextPath().length());
		
		List<String> unfilteredUrls = new ArrayList<String>();
		unfilteredUrls.add("/login");
		unfilteredUrls.add("/dwz");
		unfilteredUrls.add("/upload");
		
		if ((unfilteredUrls != null) && (unfilteredUrls.size() > 0)) {
		      for (String unfilteredUrl : unfilteredUrls) {
		        if (uri.indexOf(unfilteredUrl) == 0) {
		          return false;
		        }
		      }
		}
		
		return true;
	}
}