package com.alka.springboot.topic;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class topicServiceInterceptor implements HandlerInterceptor{
	
	public static Logger log=LoggerFactory.getLogger(topicServiceInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag=true;
		log.info("Inside pre handle method..."+request.getContentLength());
		
		if(request.getRequestURI().contains("80")) {
			
			flag=false;
		}
		
		/*if(!flag) {
			response.sendRedirect("http://localhost:8080/invalidport");
			flag=false;
		}*/
		return flag;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("Inside post handle method...");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("Inside afterCompletion method...");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
