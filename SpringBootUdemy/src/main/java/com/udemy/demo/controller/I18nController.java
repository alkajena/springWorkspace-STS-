package com.udemy.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/I18n/message")
public class I18nController {
	
	@Autowired
	ResourceBundleMessageSource message;

	@GetMapping
	public String getMessagString(@RequestHeader ("Accept-Language") String language) {
		
		return message.getMessage("message.value", null, new Locale(language));
		
	}
}
