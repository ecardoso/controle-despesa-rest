/*
package br.com.controledespesa.config;


@SuppressWarnings("deprecation")
@Configuration
public class CorsConfig {

	private static final String[] ALLOWED_ORIGINS = { "http://localhost:9090", "http://localhost:8080" };

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins(ALLOWED_ORIGINS);
			}
		};
	}
}
*/