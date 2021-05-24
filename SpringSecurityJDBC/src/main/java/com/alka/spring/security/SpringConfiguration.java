package com.alka.spring.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SpringConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery("select name,password,enabled from user where name = ? ")
		.authoritiesByUsernameQuery("select name, authority from authorities where name = ?");//.withDefaultSchema().
		//withUser(User.withUsername("alka").password("pass").roles("USER"));
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/Admin").hasAuthority("ROLE_ADMIN")//only in authorities we give ROLE_ but in roles we don't have to give ROLE_
								.antMatchers("/Users").hasAuthority("ROLE_USER")
								.antMatchers("/").permitAll()
								.and().formLogin();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}
}
