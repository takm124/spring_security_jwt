package com.cos.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 토큰 : cos , id pw 정삭적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 그걸 응답을 해준다.
        // 요청할 때 마다 header Authorization에 value값으로 token을 가지고 올 것
        // 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증하면 됨 (RSA, HS256)
        if (req.getMethod().equals("POST")) {
            String headerAuth = req.getHeader("Authorization"); // 초기엔 null
            System.out.println(headerAuth);
            System.out.println("필터 3");

            if (headerAuth.equals("cos")) {
                chain.doFilter(request, response); // 프로세스가 계속 진행하기 위한 설정
            } else {
                PrintWriter out = res.getWriter();
                out.println("인증 안 됨");
            }
        }
    }
}
