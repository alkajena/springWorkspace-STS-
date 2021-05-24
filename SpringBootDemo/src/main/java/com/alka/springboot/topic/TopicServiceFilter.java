package com.alka.springboot.topic;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TopicServiceFilter implements Filter{
	
	public static Logger log=LoggerFactory.getLogger(TopicServiceFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("Inside dofilter method...");
		chain.doFilter(request, response);;
	}

}
