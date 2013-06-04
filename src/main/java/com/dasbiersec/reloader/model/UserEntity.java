package com.dasbiersec.reloader.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity
{

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Batch> batch;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Component> component;

    public List<Batch> getBatch() {
        return batch;
    }

    public void setBatch(List<Batch> batch) {
        this.batch = batch;
    }

    public List<Component> getComponent() {
        return component;
    }

    public void setComponent(List<Component> component) {
        this.component = component;
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
