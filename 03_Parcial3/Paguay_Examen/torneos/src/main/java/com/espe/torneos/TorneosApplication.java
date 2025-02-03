package com.espe.torneos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TorneosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TorneosApplication.class, args);
	}

}
