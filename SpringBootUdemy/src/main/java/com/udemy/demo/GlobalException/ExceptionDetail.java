package com.udemy.demo.GlobalException;

import org.springframework.http.HttpStatus;

public class ExceptionDetail {
	
	private HttpStatus status;
	private String message;
	private String errorDetails;
	
	public ExceptionDetail(HttpStatus status, String message, String errorDetails) {
		super();
		this.status = status;
		this.message = message;
		this.errorDetails = errorDetails;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
}
