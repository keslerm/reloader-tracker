package com.dasbiersec.reloader.auth;

import com.dasbiersec.reloader.model.User;
import com.dasbiersec.reloader.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		RestToken restToken = (RestToken) authentication;

		String key = restToken.getKey();
		String credentials = restToken.getCredentials();

		User user = userService.findUser(key, credentials);

		if (user == null)
		{
			throw new BadCredentialsException("Enter a username and password");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		RestToken rt =  new RestToken(key, credentials, authorities);
		rt.setUser(user);

		return rt;
	}

	@Override
	public boolean supports(Class<?> aClass)
	{
		return RestToken.class.equals(aClass);
	}
}
