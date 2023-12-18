package com.pariksha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pariksha.service.impl.UserDetailsServiceImpl;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig  {
	
	
	AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	 @Autowired
	    UserDetailsServiceImpl userDetailsServiceImpl;
	 
//	 @Bean
//	 public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		 return new BCryptPasswordEncoder();
//	 }
	 
	 @Bean
	 public PasswordEncoder bCryptPasswordEncoder() {
		 return NoOpPasswordEncoder.getInstance();
	 }
	 
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		 System.out.println("Line 46 " + http);

	        http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(authorize -> authorize
	        .requestMatchers("/generate-token", "/user/").permitAll()
	        .requestMatchers(HttpMethod.OPTIONS).permitAll()
	            .anyRequest().authenticated())	         
	            .exceptionHandling(exp -> exp
	            		.authenticationEntryPoint(unauthorizedHandler))
//	            .authenticationManager(authenticationManager)
	            .sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	        
	        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	        
	        return http.build();
	    }
	 
	 @Bean
	    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		 System.out.println("Line 65 " + http);
		 
		 AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	        authenticationManagerBuilder.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
	        authenticationManager = authenticationManagerBuilder.build();
	        
	        return authenticationManager;
	    }

}
