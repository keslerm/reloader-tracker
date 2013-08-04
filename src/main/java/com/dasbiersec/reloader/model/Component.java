package com.dasbiersec.reloader.model;

import com.dasbiersec.reloader.enums.ComponentType;
import org.springframework.data.rest.repository.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="component")
@RestResource(path = "component")
public class Component extends AbstractEntity implements Serializable
{

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private ComponentType type;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "total_cost")
	private BigDecimal cost;

	@Transient
	public BigDecimal remaining;

	@Column(name = "user_id")
	private Integer userId;

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public BigDecimal getRemaining()
	{
		return remaining;
	}

	public void setRemaining(BigDecimal remaining)
	{
		this.remaining = remaining;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ComponentType getType()
	{
		return type;
	}

	public void setType(ComponentType type)
	{
		this.type = type;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getCost()
	{
		return cost;
	}

	public void setCost(BigDecimal cost)
	{
		this.cost = cost;
	}
}
