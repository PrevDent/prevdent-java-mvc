package com.prevdent.syst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SystApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystApplication.class, args);
	}

}
