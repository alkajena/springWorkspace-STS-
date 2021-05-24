package com.udemy.demo.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.udemy.demo.Exception.UserNotFoundException;

//@RestControllerAdvice
public class CustomGlobalExceptionHandlerRestControllerAdvice {
	
	
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionDetail userNotFoundException(UserNotFoundException ex){
		
		ExceptionDetail exception=new ExceptionDetail(HttpStatus.NOT_FOUND, "User is not found", ex.getMessage());
		return exception;
	}
}
