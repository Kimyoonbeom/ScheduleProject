package com.example.scheduleproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ScheduleFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        // Filter에서 수행할 Logic
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        log.info("Request URI: {}", requestURI);

        // 회원가입, 로그인
        if (requestURI.equals("/users/signup") || requestURI.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }

        // 세션 검증
        // 세션에 userId 속성이 없으면 401 Unauthorized를 반환.
        if (httpRequest.getSession(false) == null || httpRequest.getSession(false).getAttribute("userId") == null) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            httpResponse.getWriter().write("Unauthorized: Please log in first.");
            return;
        }
        chain.doFilter(request, response);
    }
}
