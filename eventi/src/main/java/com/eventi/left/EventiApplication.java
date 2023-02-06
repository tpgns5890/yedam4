package com.eventi.left;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.eventi.left.**.mapper")
public class EventiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventiApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() { return new
	BCryptPasswordEncoder(); }
	
	
	
}
