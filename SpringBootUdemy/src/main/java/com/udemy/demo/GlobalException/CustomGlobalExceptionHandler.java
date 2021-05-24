package com.udemy.demo.GlobalException;

import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.udemy.demo.Exception.UserNotFoundException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionDetail exception=new ExceptionDetail(HttpStatus.BAD_REQUEST, "Argument is not valid", ex.getMessage());
		return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionDetail exception=new ExceptionDetail(HttpStatus.METHOD_NOT_ALLOWED, "Method is not allowed", ex.getMessage());
		return new ResponseEntity<Object>(exception, HttpStatus.METHOD_NOT_ALLOWED);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException ex){
		
		ExceptionDetail exception=new ExceptionDetail(HttpStatus.NOT_FOUND, "User is not found", ex.getMessage());
		return new ResponseEntity<Object>(exception, HttpStatus.NOT_FOUND);
	}
}
