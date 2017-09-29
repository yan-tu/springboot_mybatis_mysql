package com.yantu.sbmm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yantu.sbmm")
public class SbmmApplication {
	public static void main(String[] args) {
		SpringApplication.run(SbmmApplication.class, args);
	}
}
