package com.qbthon.qbthonservices.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.qbthon.qbthonservices.jwt.AuthEntryPointJwt;
import com.qbthon.qbthonservices.jwt.AuthTokenFilter;
import com.qbthon.qbthonservices.services.MongoLoginService;



@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	  @Autowired 
	  MongoLoginService userDetailsService;
	  
	  @Autowired 
	  private AuthEntryPointJwt unauthorizedHandler;
	  
	  @Bean 
	  public AuthTokenFilter authenticationJwtTokenFilter() { return new
	  AuthTokenFilter(); }
	  
	  @Override 
	  public void configure(AuthenticationManagerBuilder
	  authenticationManagerBuilder) throws Exception {
	  authenticationManagerBuilder.userDetailsService(userDetailsService).
	  passwordEncoder(passwordEncoder()); 
	  
	  }
	  
	  @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	            .userDetailsService(userDetailsService)
	            .passwordEncoder(passwordEncoder());
	    }
	  
	  @Bean
	  
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws  Exception
	  { return super.authenticationManagerBean(); 
	  }
	  
	  
	 
	  @Bean 
	  @Autowired
	  public PasswordEncoder passwordEncoder()
	  { return new   BCryptPasswordEncoder(12); 
	  }
	  
	 
	 
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
		http
        .cors().and().csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers("/login/**").permitAll()
        .anyRequest().authenticated(); 
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);      
	}
}
