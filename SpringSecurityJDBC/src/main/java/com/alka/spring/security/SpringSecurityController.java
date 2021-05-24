package com.alka.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {
	
	@GetMapping("/Users")
	public String getUsers() {
		return "Welcome to User Page";
	}
	
	@GetMapping("/Admin")
	public String getAdmin() {
		return "Welcome to Admin Page";
	}
	
	@GetMapping("/")
	public String getHome() {
		return "Welcome to Home Page";
	}

}
