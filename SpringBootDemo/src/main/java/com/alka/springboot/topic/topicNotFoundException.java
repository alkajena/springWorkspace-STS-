package com.alka.springboot.topic;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class topicNotFoundException extends Exception{
	
	public topicNotFoundException(String e) {
		super(e);
	}
}
