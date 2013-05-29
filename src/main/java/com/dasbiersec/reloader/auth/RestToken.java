package com.dasbiersec.reloader.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class RestToken extends UsernamePasswordAuthenticationToken
{
	public RestToken(String principal, Object credentials)
	{
		super(principal, credentials);
	}

	public RestToken(String principal, String credentials, Collection<? extends GrantedAuthority> authorities)
	{
		super(principal, credentials, authorities);
	}

	public String getKey()
	{
		return (String) super.getPrincipal();
	}

	public String getCredentials()
	{
		return (String) super.getCredentials();
	}
}
