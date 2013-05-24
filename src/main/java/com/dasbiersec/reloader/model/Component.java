package com.dasbiersec.reloader.model;

import com.dasbiersec.reloader.enums.SupplyType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="components")
public class Component implements Serializable
{
	@Id
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private SupplyType type;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "total_cost")
	private BigDecimal cost;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public SupplyType getType()
	{
		return type;
	}

	public void setType(SupplyType type)
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
