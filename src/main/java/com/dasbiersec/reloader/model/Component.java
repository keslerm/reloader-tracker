package com.dasbiersec.reloader.model;

import com.dasbiersec.reloader.enums.SupplyType;
import org.springframework.data.rest.repository.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="components")
@RestResource(path = "component")
public class Component extends AbstractEntity implements Serializable
{

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private SupplyType type;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "total_cost")
	private BigDecimal cost;

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
