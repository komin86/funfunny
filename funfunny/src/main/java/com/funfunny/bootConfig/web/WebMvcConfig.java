package com.funfunny.bootConfig.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.funfunny.common.fileUpload.FileUploadService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/freeBoard");
		WebMvcConfigurer.super.addViewControllers(registry);
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/freeBorad/image/**").addResourceLocations("file:"+FileUploadService.freeBoradTempStoragePath);
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
}
