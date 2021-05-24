package com.udemy.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.demo.Entity.Order;
import com.udemy.demo.Entity.User;
import com.udemy.demo.Exception.UserNotFoundException;
import com.udemy.demo.repository.OrderRepository;
import com.udemy.demo.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
public class OrderController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderRepository orderRepo;
	
	
	
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException{
		
		User user=userService.getUserById(userId).get();
		if(user==null)
			throw new UserNotFoundException("user does not exist");
		return user.getOrder();
		
	}
	
	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable Long userId,@RequestBody Order order) throws UserNotFoundException{
		User user=userService.getUserById(userId).get();
		if(user==null)
			throw new UserNotFoundException("user does not exist");
		order.setUser(user);
		return orderRepo.save(order);
		
	}

}
