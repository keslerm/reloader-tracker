package com.dasbiersec.reloader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "hibernate_sequence")
    @Column(name = "id", insertable = true, updatable = false)
	@JsonIgnore
	private Integer id;

	@Column(name = "create_date", insertable = true, updatable = false, nullable = false)
	@JsonIgnore
	private Date createDate;

	@Column(name = "update_date")
	@JsonIgnore
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

	@JsonProperty("createDate")
	public Date getCreateDate()
	{
		return createDate;
	}

	@JsonProperty("updateDate")
	public Date getUpdateDate()
	{
		return updateDate;
	}

	@JsonProperty("id")
	public Integer getId()
	{
		return id;
	}

	@JsonIgnore
    public void setId(Integer id)
    {
        this.id = id;
    }

	@JsonIgnore
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

	@JsonIgnore
    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }
}
