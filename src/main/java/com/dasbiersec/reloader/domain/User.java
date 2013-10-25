package com.dasbiersec.reloader.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends AbstractEntity
{

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<Role> role;

	public List<Role> getRole()
	{
		return role;
	}

	public void addRole(Role role)
	{
		if (this.role == null)
			this.role = new ArrayList<Role>();

		this.role.add(role);
	}

	public void setRole(List<Role> role)
	{
		this.role = role;
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
