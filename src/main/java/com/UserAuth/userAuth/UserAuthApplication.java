package com.UserAuth.userAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${userAuth.dir}/.env")
public class UserAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);
	}

}
