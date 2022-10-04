package com.cra.aladin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cra.aladin")
public class AladinApplication {

	public static void main(String[] args) {
		SpringApplication.run(AladinApplication.class, args);
	}

}
