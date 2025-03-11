//package com.dev.config;
//
//import org.apache.catalina.User;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SpringSecurityConfig {
//
//	@SuppressWarnings("removal")
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> {
//			authorize.anyRequest().authenticated();
//		}).httpBasic(Customizer.withDefaults());
//		return http.build();
//
//	}
//
//	public	UserDetailsService userDetailsService() {
//		Use
//	}
//}
