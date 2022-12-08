package com.example.demo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//this annotation will make sure this class works with Restful service
@RestController
@RequestMapping("/users") //This annotation make sure this controller called when this resource is sent 
public class UserController {
	
	@GetMapping //depends on the type of method either GET/PUT/DELETE
	public String getUsers(@RequestParam(value = "page") int pageNum,
			@RequestParam(value = "limit") int limit) {
		
		return "http GET Request was sent for page "+pageNum+" and limit is "+limit;
	}
	
	@GetMapping(path = "/{userId}")  //Path Parameter
	public String getUser(@PathVariable String userId) {
		
		return "http GET Request was sent for UserId "+userId;
	}
	
	@PostMapping 
	public String createUser() {
		
		return "http POST Request was sent";
	}
	
	@PutMapping 
	public String updateUser() {
		
		return "http PUT Request was sent";
	}
	
	@DeleteMapping
	public String deleteUser() {
		
		return "http DELETE Request was sent";
	}
}
