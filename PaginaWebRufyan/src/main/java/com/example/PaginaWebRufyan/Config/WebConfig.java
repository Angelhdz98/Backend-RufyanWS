package com.example.PaginaWebRufyan.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/UploadedImages/**")
	                .addResourceLocations("file:src/main/resources//static/UploadedImages/")
	                .setCachePeriod(0); 
	    }
	
}
