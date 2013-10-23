package com.dasbiersec.reloader.domain;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends AbstractEntity
{

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String userName)
	{
		this.username = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
