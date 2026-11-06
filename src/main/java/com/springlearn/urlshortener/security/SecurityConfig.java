package com.springlearn.urlshortener.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springlearn.urlshortener.service.UrlService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	  private static final String SHORT_CODE_PATTERN = "/{shortCode:[A-Za-z0-9]{8}}";
	  
	 @Bean
	    public AuthenticationProvider authenticationProvider(
	            UrlService urlService,
	            PasswordEncoder passwordEncoder
	    ) {
	        DaoAuthenticationProvider provider =
	                new DaoAuthenticationProvider(urlService);

	        provider.setPasswordEncoder(passwordEncoder);

	        return provider;
	    }
	 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity
				//.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(register -> {
					register.requestMatchers(HttpMethod.GET, SHORT_CODE_PATTERN).permitAll();
					register.requestMatchers("/login", "/signup", "/req/signup", "/css/**", "/js/**").permitAll();
					register.requestMatchers("/error").permitAll();
					register.anyRequest().authenticated();
				})
				
				.formLogin(httpForm -> {
					httpForm
					.loginPage("/login")
					.defaultSuccessUrl("/home", true)
					.permitAll();
				})
				.logout(logout -> logout.logoutSuccessUrl("/login?logout"))
				.build();
	}
}
