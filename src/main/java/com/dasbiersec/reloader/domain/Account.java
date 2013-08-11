package com.dasbiersec.reloader.domain;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class Account extends AbstractEntity
{

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "is_api")
	private boolean isApiEnabled;

	public boolean isApiEnabled()
	{
		return isApiEnabled;
	}

	public void setApiEnabled(boolean apiEnabled)
	{
		isApiEnabled = apiEnabled;
	}

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
