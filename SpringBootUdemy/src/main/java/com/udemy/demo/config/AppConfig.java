package com.udemy.demo.config;

import java.util.Locale;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class AppConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public org.springframework.web.servlet.LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver locale=new AcceptHeaderLocaleResolver();
		locale.setDefaultLocale(Locale.US);
		return locale;
	}
	
	@Bean
	public ResourceBundleMessageSource resourceBundleMessageSource() {
		ResourceBundleMessageSource message=new ResourceBundleMessageSource();
		message.setBasename("message");
		return message;
	}
}
