package com.udemy.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.demo.Entity.Order;
import com.udemy.demo.Entity.User;
import com.udemy.demo.service.UserService;

@RestController
@RequestMapping("/hateos/users")
@Validated
public class UserHateosController {
	
	@Autowired
	UserService service;

	@GetMapping("/{limit}/{offset}")
	public CollectionModel<User> getAllUsers(@PathVariable Integer limit,@PathVariable Integer offset){
		List<User> users=service.getAllUsers();
		for(User user:users) {
			Link selfLink=WebMvcLinkBuilder.linkTo(UserHateosController.class).slash(user.getUserId()).withSelfRel();
			user.add(selfLink);
			for(Order order:user.getOrder()) {
				Link relLink=WebMvcLinkBuilder.linkTo
						(WebMvcLinkBuilder.methodOn(orderHateosController.class).getOrderById(order.getOrderId())).withRel("allOrders");
				order.add(relLink);
			}
		}
		List<User> finalUsers=new ArrayList<>();
		int count=0;
		int l=0;
		for(User user:users) {
			if(offset<=count) {
				if(limit >= l) {
					finalUsers.add(user);
					count++;
					l++;
				}
				
			}
		}
		Link link=WebMvcLinkBuilder.linkTo(UserHateosController.class).slash("limit = "+limit).slash("offset = "+offset+limit).withSelfRel();
		CollectionModel<User> model=new CollectionModel<>(finalUsers, link);
		List<User> list=new ArrayList<>(users);
		return model;
		
	}
}
