package com.gzpy.login.filter;

import java.io.IOException;

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


public class LoginFilter
implements Filter
{
public LoginFilter()
{
}

public void destroy()
{
}

public void init(FilterConfig config)
    throws ServletException
{
  
}

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException
{ 
    HttpServletRequest httpReq = (HttpServletRequest)request;
    HttpServletResponse httpRsp = (HttpServletResponse)response;
    String uri = httpReq.getRequestURI();
    User user = null;
    HttpSession session = httpReq.getSession(false);
   boolean isindex=(httpReq.getContextPath()+"/").equals(uri);
    if(session != null)
        user = (User)session.getAttribute("currentUser");
    
    if( user == null&&!isindex)
    {
        httpRsp.sendRedirect(httpReq.getContextPath());
        return;
    }

        chain.doFilter(request, response);

}
}