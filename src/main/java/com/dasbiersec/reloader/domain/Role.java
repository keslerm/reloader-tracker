package com.dasbiersec.reloader.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_roles")
public class Role extends AbstractEntity
{
	private String name;

	public Role()
	{
	}

	public Role(String name)
	{
		this.name = name;
	}

	public String getRoleName()
	{
		return name;
	}

	public void setRoleName(String roleName)
	{
		this.name = roleName;
	}
}
