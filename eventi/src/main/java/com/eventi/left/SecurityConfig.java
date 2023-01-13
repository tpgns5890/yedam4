package com.eventi.left;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.eventi.left.member.service.impl.MemberServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.antMatchers("/mc/*").hasRole("MC")
				.antMatchers("/designer/*").hasRole("DESIGNER")
				.antMatchers("/busi/*").hasRole("BUSI")
				.antMatchers("/admin/*").hasRole("ADMIN")
				.anyRequest().permitAll()
				);
		
		http.formLogin()
				//.loginPage("/signinPage")	// 사용자 정의 로그인 페이지
				.defaultSuccessUrl("/") 	// 로그인 성공 후 이동 페이지
			    .failureUrl("/signinPage")	    // 로그인 실패 후 이동 페이지
				.usernameParameter("userid")	// 아이디 파라미터명 설정
				.passwordParameter("password")	// 패스워드 파라미터명 설정
				//.loginProcessingUrl("/signin")	// 로그인 Form Action Url
				.successHandler(
		                new AuthenticationSuccessHandler() {
		                    @Override
		                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		                        System.out.println("authentication : " + authentication.getName());
		                        response.sendRedirect("/"); // 인증이 성공한 후에는 root로 이동
		                    }
		                }
		        )
				.failureHandler(
		                new AuthenticationFailureHandler() {
		                    @Override
		                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		                        System.out.println("exception : " + exception.getMessage());
		                        response.sendRedirect("/login");
		                    }
		                }
		        )
				.permitAll();
		
		http.logout() // 로그아웃 처리
                .logoutUrl("/logout") // 로그아웃 처리 URL
                .logoutSuccessUrl("/") // 로그아웃 성공 후 이동페이지
                .deleteCookies("JSESSIONID", "remember - me");
		
        http.rememberMe()	//자동로그인
                .rememberMeParameter("remember");
        
        http.csrf().disable(); 

		return http.build();
	}

	
}
