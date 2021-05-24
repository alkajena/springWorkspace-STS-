package com.alka.spring.security;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepo  extends CrudRepository<UserDetailPojo, Integer>{
	
	public Optional<UserDetailPojo> findByUserName(String name);
}

