package com.zylear.internalcontrol.admin.filter;

import com.zylear.internalcontrol.admin.enums.Authority;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiezongyu on 2018/4/28.
 */
public class BidderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            Integer authority = Integer.parseInt(request.getSession().getAttribute("authority").toString());
            if (!Authority.admin.getValue().equals(authority) ||
                    !Authority.bidder.getValue().equals(authority)) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
