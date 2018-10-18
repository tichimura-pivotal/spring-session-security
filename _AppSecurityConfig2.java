package com.example.securitydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
public class _AppSecurityConfig2 extends WebSecurityConfigurerAdapter{
	
	
//Option 1:	
     @Bean
     public static NoOpPasswordEncoder passwordEncoder() {
         return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
     }

//	  @Bean
//	  public static PasswordEncoder passwordEncoder() {
//	      return (PasswordEncoder) PasswordEncoder.getInstance();
//	  }		
	

	@Autowired
	 public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {

//Option 2:
//      .passwordEncoder(NoOpPasswordEncoder.getInstance())
		
//Option 3:
//      .withUser("admin").password("{noop}nimda").roles("ADMIN")
		
        authentication.inMemoryAuthentication()
        .withUser("admin").password("nimda").roles("ADMIN")
        .and().withUser("user").password("password").roles("USER");	        
	 }

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
//		 http.authorizeRequests().antMatchers("/**").hasRole("ADMIN").and().httpBasic();

	  http
//	   .httpBasic().and()
	   .authorizeRequests()
//	   .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
	   .antMatchers("/").hasRole("ADMIN")
	   .anyRequest().authenticated()
	   .and()
//	   .httpBasic();
	   .formLogin();
	 }

	 
}
