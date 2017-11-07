package com.qiyue.bluecareer.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Qiyue on 2017/11/7
 */
public class LogInFilter implements Filter {
    //不过滤的请求列表
    private List<String> excludePageList;
    private static final String MSG = "NOT_LOGIN";

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludePages = filterConfig.getInitParameter("excludedPages");
        excludePageList = Arrays.asList(excludePages.split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        logger.info("Request URL: " + httpReq.getRequestURL());
        if (isExcludePath(httpReq)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (httpReq.getHeader("Authorization") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new ServletException(MSG + ":" + "/login.html");
        }
    }

    @Override
    public void destroy() {
        /* do nothing */
    }

    private boolean isExcludePath(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        return excludePageList.contains(requestPath);
    }
}
