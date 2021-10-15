package com.example.Faculty_Management_System;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${file.upload-dir}")
	private String uploadDirectory;
	
	@Bean
	 public BCryptPasswordEncoder passwordEncoder() {
	  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	  return bCryptPasswordEncoder;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		  .addResourceHandler("/static/**")
		  .addResourceLocations("classpath:/static/");
		
		registry
		  .addResourceHandler("/superadmin/**")
		  .addResourceLocations("classpath:/static/");  
		
		registry
		  .addResourceHandler("/admin/**")
		  .addResourceLocations("classpath:/static/");  
		
		registry
		  .addResourceHandler("/faculty/**")
		  .addResourceLocations("classpath:/static/");  
		
		registry
		  .addResourceHandler("/student/**")
		  .addResourceLocations("classpath:/static/"); 
		
		registry
		  .addResourceHandler("/superadmin/viewprofile/**")
		  .addResourceLocations("classpath:/static/");
		
		registry
		  .addResourceHandler("/admin/viewprofile/**")
		  .addResourceLocations("classpath:/static/");  
		
		registry
		  .addResourceHandler("/faculty/viewprofile/**")
		  .addResourceLocations("classpath:/static/");  
		
		registry
		  .addResourceHandler("/student/**/**")
		  .addResourceLocations("classpath:/static/"); 
		
		registry
		  .addResourceHandler("/superadmin/vacancy/**")
		  .addResourceLocations("classpath:/static/");
		
		registry
		  .addResourceHandler("/superadmin/vacancyapplicantDetails/**")
		  .addResourceLocations("classpath:/static/");
		
		registry
	      .addResourceHandler("/files/**")
	      .addResourceLocations("file:/opt/files/");
		
		registry
		  .addResourceHandler("/uploads/**")
		  .addResourceLocations("file:E:/Faculty_Management_System/uploads/");
        
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/signin").setViewName("signin");
    }
	
}
