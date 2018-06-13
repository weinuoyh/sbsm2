package com.sxt.servlet;

import com.sxt.cfg.BaseContextHandler;
import com.sxt.pojo.WebConfig;
import com.sxt.utils.IpHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class CorsFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("^/v(\\d+)/");

    @Autowired
    private WebConfig config;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

//跨域过滤器
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");

        String mobile = request.getHeader("access-mobile");
        String uid = request.getHeader("access-uid");

        String name = request.getHeader("access-name");
        String userName = request.getHeader("access-userName");

        BaseContextHandler.setMobile(mobile);
        BaseContextHandler.setUserID(uid);
        BaseContextHandler.setName(name);
        BaseContextHandler.setUserName(userName);
        String ip = IpHelper.getIpAddr(request);
        BaseContextHandler.setIp(ip);

        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {

    }

    public void destroy() {
    }

}