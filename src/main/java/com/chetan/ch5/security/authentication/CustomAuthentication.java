package com.chetan.ch5.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthentication extends UsernamePasswordAuthenticationToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4678555493528629352L;

	public CustomAuthentication(Object principal, Object credentials) {
		super(principal, credentials);
		// TODO Auto-generated constructor stub
	}

}
