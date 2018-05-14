package com.zylear.internalcontrol.admin.filter;

import com.zylear.internalcontrol.admin.enums.Authority;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiezongyu on 2018/4/28.
 */
public class AdminFilter implements Filter {

    private Set<String> excludePages = Collections.EMPTY_SET;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String string = filterConfig.getInitParameter("excludePages");
        if (string != null) {
            excludePages = new HashSet(Arrays.asList(string.split(",")));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (!excludePages.contains(request.getServletPath())) {
            try {
                Integer authority = Integer.parseInt(request.getSession().getAttribute("authority").toString());
                if (!Authority.admin.getValue().equals(authority)) {
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    return;
                }
            } catch (Exception e) {
                //e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
