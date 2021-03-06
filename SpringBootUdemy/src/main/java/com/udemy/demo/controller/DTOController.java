package com.udemy.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.demo.DTO.UserMMDTO;
import com.udemy.demo.Entity.User;
import com.udemy.demo.MapStruct.MapStructMapper;
import com.udemy.demo.service.UserService;

@RestController
@RequestMapping("/DTO/users")
@Validated
public class DTOController {
	
	@Autowired
	UserService service;
	
	@Autowired
	ModelMapper mapper;
	
	//@Autowired
	//MapStructMapper mapStruct;
	 
	@GetMapping("/{id}")
	public UserMMDTO getUserById(@PathVariable Long id){
		
		User user = service.getUserById(id).get();
		UserMMDTO dto = mapper.map(user, UserMMDTO.class);
		
		return dto;
	}
	
	/*@GetMapping("MapStruct/{id}")
	public UserMMDTO getUserByMapStructId(@PathVariable Long id){
		
		User user = service.getUserById(id).get();
		UserMMDTO dto =mapStruct.userToUserMMDTO(user);
		
		return dto;
	}*/
}
