package com.dasbiersec.reloader.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

public class RestAuthenticationProvider implements AuthenticationProvider
{
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		RestToken restToken = (RestToken) authentication;
		String key = (String) restToken.getPrincipal();
		String credentials = restToken.getCredentials();

		UserDetails userDetails = userDetailsService.loadUserByUsername(key);

		if (userDetails == null || !userDetails.getPassword().equals(credentials))
		{
			throw new BadCredentialsException("Enter a username and password");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		RestToken rt =  new RestToken(userDetails, credentials, authorities);
		return rt;
	}

	@Override
	public boolean supports(Class<?> aClass)
	{
		return RestToken.class.equals(aClass);
	}
}
