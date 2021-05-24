package com.udemy.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.demo.Entity.Order;
import com.udemy.demo.Entity.User;
import com.udemy.demo.repository.OrderRepository;
import com.udemy.demo.service.UserService;

@RestController
@RequestMapping("/hateos/orders")
public class orderHateosController {
	
	@Autowired
	OrderRepository repo;
	
	@Autowired
	UserService service;
	
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable Long id){
		return repo.findById(id).get();
		
	}


}
