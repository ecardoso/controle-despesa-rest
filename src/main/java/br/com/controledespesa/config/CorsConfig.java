package br.com.controledespesa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	private static final String[] ALLOWED_ORIGINS = { "http://localhost:9090", "http://localhost:8080", "https://controle-despesa.herokuapp.com" };
	private static final String[] ALLOWED_METHODS = { "GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH" };

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/*").allowedOrigins(ALLOWED_ORIGINS).allowedMethods(ALLOWED_METHODS);
	}
}
