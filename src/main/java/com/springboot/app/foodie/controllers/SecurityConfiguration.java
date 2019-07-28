package com.springboot.app.foodie.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
//		http
//	    .anonymous()
//	    .and()
//	    .authorizeRequests()
//	    .antMatchers("/admin/**").authenticated()
//	    .and()
//        .formLogin()
//            .loginPage("/login")
//            .permitAll();
		
		http
        .authorizeRequests()
        .antMatchers("/index.html").permitAll()
        .antMatchers("/admin/**").hasRole("admin")
        .and()
        .formLogin()
        .loginPage("/login").permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}
}
