package com.udemy.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.udemy.demo.Entity.User;
import com.udemy.demo.Exception.UserNotFoundException;
import com.udemy.demo.service.UserService;

@RestController
@Validated
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id){
		return service.getUserById(id);
	}

	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return service.createUser(user);
	}
	
	@PutMapping("/Users/{id}")
	public User updateUser(@Valid @RequestBody User user,@PathVariable Long id) {
		try {
			return service.updateUser(user, id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable Long id) {
		return service.deleteUser(id);
	}
	
	@GetMapping("/users/Byname/{name}")
	public User getUserByUserName(@PathVariable @NotEmpty String name) {
		try {
			return service.getUserByUserName(name);
		}
		catch(UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	
	//Global Exception
	@GetMapping("/users/Byname/GlobalException/{name}")
	public User getUserByUserNameGlobalException(@PathVariable @NotEmpty String name) throws UserNotFoundException {
		return service.getUserByUserName(name);
	}
	
	
}
