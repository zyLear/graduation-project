package com.zylear.internalcontrol.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zylear.internalcontrol.admin.*")
public class InternalcontrolAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternalcontrolAdminApplication.class, args);
	}
}
