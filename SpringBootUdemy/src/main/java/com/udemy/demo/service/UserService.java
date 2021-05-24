package com.udemy.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.udemy.demo.Entity.User;
import com.udemy.demo.Exception.UserNotFoundException;
import com.udemy.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repo;
	
	public List<User> getAllUsers(){
		return repo.findAll();
	}
	
	public Optional<User> getUserById(Long id){
		return repo.findById(id);
	}

	public User createUser(User user) {
		return repo.save(user);
	}
	
	public User updateUser(User user,Long id) throws UserNotFoundException {
		Optional<User> extractedUser=repo.findById(id);
		if(extractedUser.isPresent())
			return repo.save(user);
		throw new UserNotFoundException("User with ID : "+id+"does not exist in system");
	}
	
	public String deleteUser(Long id) {
		Optional<User> extractedUser=repo.findById(id);
		if(extractedUser.isPresent()) {
			repo.deleteById(id);
			return "ID : "+id+"got deleted successfully";
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID : "+id+"does not exist in system");
	}
	
	public User getUserByUserName(String name) throws UserNotFoundException {
		User user= repo.findByUserName(name);
		if(user == null) {
			throw new UserNotFoundException("User with name : "+name+"does not exist in system");
		}
		return user;
	}
}
