package com.HireAtE.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        
        // Allow the frontend domain
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedMethod("*");  // Allow all methods (GET, POST, PUT, DELETE, etc.)
        corsConfiguration.addAllowedHeader("*");  // Allow all headers
        corsConfiguration.setAllowCredentials(true); // Allow credentials (cookies, authorization headers)

        // Apply the CORS configuration globally
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);  // Apply to all endpoints
        
        return new CorsFilter(source);
    }
}
