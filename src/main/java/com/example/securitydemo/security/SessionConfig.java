package com.example.securitydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SessionConfig extends WebSecurityConfigurerAdapter {

	
	// For testing purpose, but to avoid 
	//
	// "Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception ".
	// java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
	// 
	// NOTE: Do not use org.springframework.security.authentication.encoding.PasswordEncoder
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	    return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
	}
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
   
}

