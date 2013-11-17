package com.dasbiersec.reloader.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users_roles")
public class Role extends AbstractEntity
{
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getName()
	{
		return name;
	}

	public User getUser()
	{
		return user;
	}

	public Role()
	{
	}

	public Role(String name)
	{
		this.name = name;
	}
}
