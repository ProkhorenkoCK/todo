package com.my.filter;

import com.my.manager.UserSessionManager;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class BaseFilter implements Filter {

    protected boolean isUserInSystem(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute(UserSessionManager.USER_SESSION_ATTRIBUTE) != null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
