package com.eventi.left.member.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO implements UserDetails {
	private String userId;
	private String userPassword;
	private String userEmail;
	private String name;
	private String userPhone;
	private String userMessaging;
	private String auth;
	private int rprt;
	private String depotr;
	private String bank;
	private String accnt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date userRegDate;
	private String userState;
	private boolean locked; //계정 제한 구현
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date lockedDt;
	
	private List<CrtfVO> crtfs;
	private BusiVO busi;
	
	private String busiNum;
	private String busiTitle;
	private String busiType;
	private String busiArea;
	private String busiStyle;
	
	private String busiAreaCode;
	private String busiStyleCode;
	
	

	Integer first; //첫페이지
	Integer last; //마지막페이지
	

	@Override
	public String getUsername() {
		return getUserId();
	}

	@Override
	public String getPassword() {
		return userPassword;
	}


	@Override public Collection<? extends GrantedAuthority> getAuthorities() {
	  ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
	  authList.add(new SimpleGrantedAuthority(auth));
	  return authList; 
	  }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
