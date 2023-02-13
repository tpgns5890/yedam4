package com.eventi.left.common;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.eventi.left.member.service.MemberService;
import com.eventi.left.member.service.impl.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	MemberService service;
	
	@Autowired
	MemberServiceImpl impl;
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	private final AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	AuthenticationFailureHandler customFailureHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.headers()
			.frameOptions()
			.sameOrigin(); //security 설정 추가
		
		http.authorizeHttpRequests((requests) -> requests
				.antMatchers("/mc/**").hasRole("MC") //사회자 권한
				.antMatchers("/designer/**").hasRole("DES")  //디자이너권한
				.antMatchers("/busi/**").hasRole("BUSI")	//업체회원권한
				.antMatchers("/admin/**").hasRole("ADMIN")	//관리자권한
				.antMatchers("/jobDetail").hasAnyRole("ADMIN","MEM","MC","BUSI","DES")
				.anyRequest().permitAll()	//그외 모든 url 접근권한 없음
				);
		
		http.formLogin()
				.loginPage("/loginPage")	// 사용자 정의 로그인 페이지
				.usernameParameter("userid")	// 아이디 파라미터명 설정
				.passwordParameter("password")	// 패스워드 파라미터명 설정
				.loginProcessingUrl("/login")	// 로그인 Form Action Url
				.successHandler(authenticationSuccessHandler)	//LoginSuccessHandler 컴포넌트 호출
				.failureHandler(customFailureHandler);
		
		http.rememberMe()	//자동로그인
			.key("key")
			.rememberMeParameter("remember-me")
			.userDetailsService(impl)
			.tokenRepository(persistentTokenRepository()) //DataSource 추가
			.authenticationSuccessHandler(authenticationSuccessHandler);
		
		http.logout() // 로그아웃 처리
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true) //세션 삭제
			.deleteCookies("remember-me", "JSESSIONID"); //자동 로그인 쿠키, Tomcat이 발급한 세션 유지 쿠키 삭제

		return http.build();
	}
	
	//자동로그인 정보 토큰 저장
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
}
