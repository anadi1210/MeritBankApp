package com.meritbank.v1.todo.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloController {

	@GetMapping(path = "/hello")
	public String hello() {
		return "Hello World !!";
	}
	
	@GetMapping(path = "/hello-bean")
	public AuthenticationBean helloBean() {
		return new AuthenticationBean("Hello World From Bean");
	}
	
	@GetMapping(path = "/hello-bean/variable/{name}")
	public AuthenticationBean helloWithVariable(@PathVariable String name) {
		
		//throw new RuntimeException("Something Went Wrong");
		return new AuthenticationBean("Hello  " +name);
	}
	
	/*
	 * @GetMapping(path = "/basicauth") public AuthenticationBean helloWorldBean() {
	 * return new AuthenticationBean("You are authenticated"); }
	 */
}
