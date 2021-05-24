package com.udemy.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.udemy.demo.Entity.User;
import com.udemy.demo.Entity.Views;
import com.udemy.demo.Exception.UserNotFoundException;
import com.udemy.demo.service.UserService;

@RestController
@RequestMapping("/filter/users")
@Validated
public class FiletrController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable Long id) throws UserNotFoundException{
		Optional<User> user=userService.getUserById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("User does not exist");
		return user;
	}
	
	
	@GetMapping("/mappingJacksonValue/{id}")
	public MappingJacksonValue getUserByIdmappingJacksonValue(@PathVariable Long id) throws UserNotFoundException{
		Optional<User> user=userService.getUserById(id);
		Set<String> fields=new HashSet<>();
		fields.add("roll");
		fields.add("firstName");
		fields.add("lastName");
		
		if(!user.isPresent())
			throw new UserNotFoundException("User does not exist");
		MappingJacksonValue mapper=new MappingJacksonValue(user.get());
		//SimpleBeanPropertyFilter filter =new SimpleBeanPropertyFilter().filterOutAllExcept(fields);
		FilterProvider FilterProvider= new SimpleFilterProvider()
				.addFilter("UserFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
		mapper.setFilters(FilterProvider);
		return mapper;
		
	}
	
	@GetMapping("/dynamicMappingJacksonValue/{id}")
	public MappingJacksonValue getUserByIdmappingJacksonValueDynamic(@PathVariable Long id,
			@RequestParam Set<String> fields, @RequestParam String name) throws UserNotFoundException{
		Optional<User> user=userService.getUserById(id);
		fields.add(name);
		if(!user.isPresent())
			throw new UserNotFoundException("User does not exist");
		MappingJacksonValue mapper=new MappingJacksonValue(user.get());
		//SimpleBeanPropertyFilter filter =new SimpleBeanPropertyFilter().filterOutAllExcept(fields);
		FilterProvider FilterProvider= new SimpleFilterProvider()
				.addFilter("UserFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
		mapper.setFilters(FilterProvider);
		return mapper;
		
	}
	
	
	@GetMapping("/JsonViewInternal/{id}")
	@JsonView(Views.Internal.class)
	public Optional<User> getUserByIdJsonViewInternal(@PathVariable Long id) throws UserNotFoundException{
		Optional<User> user=userService.getUserById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("User does not exist");
		return user;
	}
	
	@GetMapping("/JsonViewExternal/{id}")
	@JsonView(Views.External.class)
	public Optional<User> getUserByIdJsonViewExternal(@PathVariable Long id) throws UserNotFoundException{
		Optional<User> user=userService.getUserById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("User does not exist");
		return user;
	}
}
