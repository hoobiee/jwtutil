package com.xiaoliu.filter;


import com.xiaoliu.utils.JWTUtils;
import com.xiaoliu.utils.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * xiaoliu
 */
@Component
public class JWTFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
        httpResponse.setHeader("Access-Control-Expose-Headers", "*");
        String auth = httpRequest.getHeader("Authorization");
        ArrayList<String> list = new ArrayList();
        list.add("/login");
        //白名单放行
        if(list.contains(httpRequest.getRequestURI())){
            filterChain.doFilter(httpRequest,httpResponse);
            return ;
        }
        //拦截没有带token的请求
        if (!StringUtils.hasText(auth)) {
            PrintWriter print = httpResponse.getWriter();
            print.write(JsonUtils.toJson("非法请求【缺少Authorization信息】"));
            return;
        }
        JWTUtils.JWTResult jwtResult = JWTUtils.checkToken(auth);
        if(jwtResult.isStatus()){
            filterChain.doFilter(httpRequest,httpResponse);
            return ;
        }
        return ;
    }

    @Override
    public void destroy() {

    }
}
