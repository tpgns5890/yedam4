package com.eventi.left.common;

import java.io.IOException;
import java.io.PrintWriter;

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
		System.out.println(request.getHeaderNames());
		session.setAttribute("member", service.getMember(authentication.getName())); // 로그인한 사용자VO 세션에 저장
		Back(response, -1);
	}

	public static void Back(HttpServletResponse response, int num) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter w = response.getWriter();
			w.write("<script>history.go('" + num + "');</script>");
			w.flush();
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}