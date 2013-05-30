package com.dasbiersec.reloader.auth;

import com.dasbiersec.reloader.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class RestToken extends UsernamePasswordAuthenticationToken
{
	private User user;

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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}

