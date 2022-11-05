package com.chetan.ch5.security.provider;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.chetan.ch5.security.authentication.CustomAuthentication;

@Component
public class CustomAuthenticationFilter implements Filter {
	
	@Autowired
	private AuthenticationManager authManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// [request] and [response] we get here to intercept the req/resp to meet our
		// security requirements
		// [chain] is for delegating the req/resp to next filter chain

		var http = (HttpServletRequest) request; //type cast the request
		String authorizationHeader = http.getHeader("Authorization");// used to write authentication login by transferring it to AuthenticationProvider
		
		var ca = new CustomAuthentication(authorizationHeader, null);
		Authentication result =  authManager.authenticate(ca);
		
		if (result.isAuthenticated()) {
			chain.doFilter(request, response); // helps to move the reeq/resp to next filter in chain
			SecurityContextHolder.getContext().setAuthentication(result);
		}
		
	}

}
