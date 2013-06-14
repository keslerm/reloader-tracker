package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.ReloaderUserDetails;
import com.dasbiersec.reloader.model.UserEntity;
import com.dasbiersec.reloader.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public ReloaderUserDetails loadUserByUsername(String s) throws UsernameNotFoundException
	{
		UserEntity userEntity = userRepository.findUserByUsername(s);

		if (userEntity == null)
			throw new UsernameNotFoundException("Enter a username and password");

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (userEntity != null && userEntity.isApiEnabled())
			authorities.add(new SimpleGrantedAuthority("ROLE_API"));

		ReloaderUserDetails user = new ReloaderUserDetails(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, authorities);
        user.setId(userEntity.getId());

		return user;
	}
}

