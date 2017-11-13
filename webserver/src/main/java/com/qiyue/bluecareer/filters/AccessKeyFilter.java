package com.qiyue.bluecareer.filters;

import com.qiyue.bluecareer.dao.UserDao;
import com.qiyue.bluecareer.exception.HibernateException;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Create by Qiyue on 2017/11/10
 */
public class AccessKeyFilter implements Filter{
    private List<String> excludePageList;
    private Logger logger = Logger.getLogger(AccessKeyFilter.class);

    private UserDao userDao;

    public AccessKeyFilter() {
        /* Required empty public constructor */
    }

    public void init(FilterConfig filterConfig) {
        String excludePages = filterConfig.getInitParameter("excludedPages");
        excludePageList = Arrays.asList(excludePages.split(","));
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bluecareer-context.xml");
        userDao =(UserDao) ctx.getBean("userDao");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        logger.info("Request URL: " + httpReq.getRequestURL());
        if (isExcludePath(httpReq)) {
            chain.doFilter(request, response);
            return;
        } else if (verifyAccessKey(httpReq)){
            try {
                String newKey = updateAccessKey(httpReq);
                httpRes.setHeader("accessKey" , newKey);
                chain.doFilter(request, response);
                return;
            } catch (HibernateException e) {
                throw new ServletException(e);
            }
        } else {
            logger.info("access_key  wrong.");
            throw new ServletException("access_key  wrong.");
        }
    }

    public void destroy() {
        /**/
    }

    private boolean isExcludePath(HttpServletRequest request) {
        String requestPath = request.getRequestURI();
        return excludePageList.contains(requestPath);
    }

    private boolean verifyAccessKey(HttpServletRequest httpReq) {
        String accessKey = httpReq.getHeader("accessKey");
        String email = httpReq.getHeader("email");
        return accessKey != null && !accessKey.isEmpty() && email != null && !email.isEmpty() && userDao.verifyAccessKey(email, accessKey);
    }

    private String updateAccessKey(HttpServletRequest httpReq) throws HibernateException {
        String email = httpReq.getHeader("email");
        return userDao.updateAccessKey(email);
    }

}
