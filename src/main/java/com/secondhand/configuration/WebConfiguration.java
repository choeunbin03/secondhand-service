package com.secondhand.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfiguration implements WebMvcConfigurer{
	
	@Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://secondhand.com", "http://secondhand.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(3000);
    }

}
