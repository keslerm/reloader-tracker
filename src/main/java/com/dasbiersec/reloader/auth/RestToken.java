package com.dasbiersec.reloader.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class RestToken extends UsernamePasswordAuthenticationToken
{

	public RestToken(Object principal, Object credentials)
	{
		super(principal, credentials);
	}

	public RestToken(Object userDetails, String credentials, Collection<? extends GrantedAuthority> authorities)
	{
		super(userDetails, credentials, authorities);
	}

	public Object getKey()
	{
		return super.getPrincipal();
	}

	public String getCredentials()
	{
		return (String) super.getCredentials();
	}
}

