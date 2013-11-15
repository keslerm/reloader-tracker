package com.dasbiersec.reloader.helper;

import com.dasbiersec.reloader.auth.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper
{
	public static UserDetails getCurrentUser()
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails;
	}

	public static int getCurrentUserId()
	{
		return getCurrentUser().getId();
	}

	public static boolean isUserInRole(String roleName)
	{
		UserDetails details = getCurrentUser();

		return details.getAuthorities().contains(new SimpleGrantedAuthority(roleName));
	}
}
