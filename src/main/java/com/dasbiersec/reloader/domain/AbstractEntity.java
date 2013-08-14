package com.dasbiersec.reloader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "hibernate_sequence")
	private Integer id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "update_date")
	private Date updateDate;

	@PrePersist
	public void onCreate()
	{
		createDate = updateDate = new Date();
	}

	@PreUpdate
	public void onUpdate()
	{
		updateDate = new Date();
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public Date getUpdateDate()
	{
		return updateDate;
	}

	public Integer getId()
	{
		return id;
	}

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }
}
