package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.AccountDetails;
import com.dasbiersec.reloader.entity.Account;
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
	public AccountDetails loadUserByUsername(String s) throws UsernameNotFoundException
	{
		Account account = userRepository.findUserByUsername(s);

		if (account == null)
			throw new UsernameNotFoundException("Enter a username and password");

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (account != null && account.isApiEnabled())
			authorities.add(new SimpleGrantedAuthority("ROLE_API"));

		AccountDetails user = new AccountDetails(account.getUsername(), account.getPassword(), true, true, true, true, authorities);
        user.setId(account.getId());

		return user;
	}
}

