package com.meritbank.v1.basic.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyPassEncoder {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println(encoder.encode("123"));
	}
}
