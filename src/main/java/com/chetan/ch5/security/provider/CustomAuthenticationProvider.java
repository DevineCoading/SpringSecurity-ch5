package com.chetan.ch5.security.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.chetan.ch5.security.authentication.CustomAuthentication;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Value("${key}")
	private String key;

	@Override
	public Authentication authenticate(Authentication authenticationType) throws AuthenticationException {

		String requestKey = authenticationType.getName();
		if (requestKey.equals(key)) {
			var a = new CustomAuthentication(null, null);
			return a;
		} else {
			throw new BadCredentialsException("BOOO!");
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {

		return CustomAuthentication.class.equals(authenticationType);
	}

}
