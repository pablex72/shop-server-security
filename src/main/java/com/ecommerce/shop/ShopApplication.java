package com.ecommerce.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Bean
//	public CommandLineRunner createPasswordCommand(){
//		return args -> {
//			System.out.println(passwordEncoder.encode("clave123"));
//			System.out.println(passwordEncoder.encode("clave456"));
//		};
//	}
}
