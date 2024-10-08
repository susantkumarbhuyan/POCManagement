package com.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	public static final String ADMIN = "admin";
	public static final String STUDENT = "student";

	private static final String[] NON_SECURE_API = { "/actuator/**", "/studentcontroller/**", "/public/**",
			"/h2-console/**" ,"/api-docs/**"};

    @Bean
    UserDetailsService userDetailsService() {
		UserDetails normalUser = User.withUsername("susant").password(passwordEncoder().encode("susant"))
				.roles("NORMAL").build();
		UserDetails adminUser = User.withUsername("admin").password(passwordEncoder().encode("susant"))
				.roles(ADMIN, STUDENT).build();
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(normalUser, adminUser);
		return inMemoryUserDetailsManager;
	}

	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(request -> request.requestMatchers("/secure/**").authenticated().requestMatchers(NON_SECURE_API).permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable()) // Disable CSRF protection for the H2
																					// console
				.headers(header -> header.frameOptions(form -> form.disable())) // Allow the H2 console to be rendered in a frame
				.build();
	}

	@Bean
	 AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}
