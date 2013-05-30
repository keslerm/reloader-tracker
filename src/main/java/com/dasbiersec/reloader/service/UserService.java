package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.RestToken;
import com.dasbiersec.reloader.auth.SecurityContextHelper;
import com.dasbiersec.reloader.model.User;
import com.dasbiersec.reloader.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{

	@Autowired
	private SecurityContextHelper securityContextHelper;

	@Autowired
	private UserRepository userRepository;

	public RestToken getCurrentUser()
	{
		return securityContextHelper.getAuthenticationToken();
	}

	public User findUser(String username, String password)
	{
		return userRepository.findUserByUsernameAndPassword(username, password);
	}

}

