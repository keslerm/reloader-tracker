package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.UserDetails;
import com.dasbiersec.reloader.domain.User;
import com.dasbiersec.reloader.dto.user.RegisterDTO;
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
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
	{
		User username = userRepository.findUserByUsername(s);

		if (username == null)
			throw new UsernameNotFoundException("Enter a username and password");

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_API"));


		UserDetails user = new UserDetails(username.getUsername(), username.getPassword(), true, true, true, true, authorities);
        user.setId(username.getId());

		return user;
	}

	public void registerUser(RegisterDTO dto)
	{
		User account = new User();
		account.setUsername(dto.username);
		account.setPassword(dto.password);

		userRepository.save(account);
	}
}

