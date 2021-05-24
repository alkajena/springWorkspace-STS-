package com.udemy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.demo.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUserName(String name);
	
}
