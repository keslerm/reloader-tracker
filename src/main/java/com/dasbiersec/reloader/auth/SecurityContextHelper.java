package com.dasbiersec.reloader.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHelper
{
	public RestToken getAuthenticationToken()
	{
		return (RestToken) SecurityContextHolder.getContext().getAuthentication();
	}
}
