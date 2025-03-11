package com.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFailterChin(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) ->{
//		authorize.requestMatchers(HttpMethod.POST , "/api/**").hasRole("admin");
//		authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("admin");
//		authorize.requestMatchers(HttpMethod.DELETE, "api/**").hasRole("admin");
//		authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("admin","user");
//		//we also write get api for all user publiccally
//		// authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
//		authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("admin" , "user");
		
		authorize.anyRequest().authenticated();})
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}


	@Bean
	public UserDetailsService userDetailsService1() {
		UserDetails admin = User.withUsername("devendra").password(passwordEncoder().encode("password")).roles("admin")
				.build();

		UserDetails ramesh = User.withUsername("kalyani").password(passwordEncoder().encode("password")).roles("user")
				.build();

		return new InMemoryUserDetailsManager(admin, ramesh);
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

}
