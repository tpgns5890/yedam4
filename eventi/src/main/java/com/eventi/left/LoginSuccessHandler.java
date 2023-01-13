package com.eventi.left;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.eventi.left.member.service.MemberService;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Autowired
    private MemberService service;
     
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	HttpSession session = request.getSession();
        session.setAttribute("member", service.getMember(authentication.getName())); //로그인한 사용자VO 세션에 저장
        response.sendRedirect("/"); // 인증이 성공한 후에는 root로 이동
    }
    
}