package com.example.User.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class UserServiceApplication {

	//checking jenkins Build more

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
