package com.eventi.left;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.eventi.left.**.mapper")
public class EventiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventiApplication.class, args);
	}

}
