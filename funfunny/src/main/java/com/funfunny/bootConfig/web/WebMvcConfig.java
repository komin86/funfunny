package com.funfunny.bootConfig.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.funfunny.freeBoard.controller.FreeBoardController;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/freeBoard");
		WebMvcConfigurer.super.addViewControllers(registry);
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/freeBoard/**")
			.addResourceLocations("file:"+FreeBoardController.freeBoradStoragePath , "file:"+ FreeBoardController.tempFreeBoradStoragePath);
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
}
