package com.eventi.left.common;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventi.left.member.mapper.MemberMapper;

@Component
public class CustomSessionListner implements HttpSessionListener {

    @Autowired
    MemberMapper mapper;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	mapper.visitCount();
    }
    
}
