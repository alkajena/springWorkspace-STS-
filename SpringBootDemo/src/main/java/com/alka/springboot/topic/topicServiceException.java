package com.alka.springboot.topic;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class topicServiceException {
	
	@ExceptionHandler({topicNotFoundException.class})
	public ResponseEntity<Object> handle(topicNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body("{\r\n"
				+ "       \r\n"
				+ "    \"error\": \"No Data found\"\r\n"
				+"    \"status\": \"404\"\r\n"
				+ "    }");
	}

}
