package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		//パスワードにハッシュ値を利用するため
		return new BCryptPasswordEncoder();
	}

}
