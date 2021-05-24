package com.alka.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class MyUserDetails implements UserDetails{

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails(UserDetailPojo pojo) {
		this.userName=pojo.getUserName();
		this.password=pojo.getPassword();
		this.authorities=new ArrayList<>();
		for(String authority:pojo.getAuthorities().split(",")) {
			this.authorities.add(new SimpleGrantedAuthority(authority));
		}
		}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.authorities;
	}

	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
