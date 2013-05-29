package com.dasbiersec.reloader.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class RestAuthenticationProvider implements AuthenticationProvider
{
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		RestToken restToken = (RestToken) authentication;

		String key = restToken.getKey();
		String credentials = restToken.getCredentials();

		if (!key.equalsIgnoreCase("test") || !credentials.equalsIgnoreCase("pass"))
		{
			throw new BadCredentialsException("Enter a username and password");
		}

		return getAuthenticatedUser(key, credentials);
	}

	@Override
	public boolean supports(Class<?> aClass)
	{
		return RestToken.class.equals(aClass);
	}

	private Authentication getAuthenticatedUser(String key, String credentials)
	{
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return new RestToken(key, credentials, authorities);
	}
}
