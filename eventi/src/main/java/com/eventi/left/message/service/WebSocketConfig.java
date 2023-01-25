package com.eventi.left.message.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
	    //구독
		config.enableSimpleBroker("/topic");
		//채팅방에서 메시지를 보낼때 링크이름으로 보내야지 무조건 받아옴
	    config.setApplicationDestinationPrefixes("/app");
	  }
	
	@Override
	  public void registerStompEndpoints(StompEndpointRegistry registry) {
	    registry.addEndpoint("/gs-guide-websocket").withSockJS();
	  }
}
