package com.chetan.ch5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.chetan.ch5.security.provider.CustomAuthenticationFilter;
import com.chetan.ch5.security.provider.CustomAuthenticationProvider;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	private CustomAuthenticationProvider provider;

	private CustomAuthenticationFilter filter;

	@Autowired
	public ProjectConfig(@Lazy CustomAuthenticationProvider provider, CustomAuthenticationFilter filter) {

		this.provider = provider;
		this.filter = filter;
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilterAt(filter, BasicAuthenticationFilter.class);
		http.authorizeRequests().anyRequest().permitAll();
	}

}
