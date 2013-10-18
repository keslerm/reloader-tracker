package com.dasbiersec.reloader.domain;

import com.dasbiersec.reloader.enums.ComponentType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="component")
public class Component extends AbstractEntity implements Serializable
{

	private String manufacturer;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	private ComponentType type;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "total_cost")
	private BigDecimal cost;

	@Column(name = "user_id")
	private Integer userId;



	// Component specifics
	private BigDecimal diameter;
	private BigDecimal weight;
	private String shape;
	private BigDecimal length;
	private BigDecimal coefficient;


	public Component()	{}

	public Component(Integer id)
	{
		this.setId(id);
	}

	public BigDecimal getCoefficient()
	{
		return coefficient;
	}

	public void setCoefficient(BigDecimal coefficient)
	{
		this.coefficient = coefficient;
	}

	public String getManufacturer()
	{
		return manufacturer;
	}

	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}

	public BigDecimal getDiameter()
	{
		return diameter;
	}

	public void setDiameter(BigDecimal diameter)
	{
		this.diameter = diameter;
	}

	public BigDecimal getWeight()
	{
		return weight;
	}

	public void setWeight(BigDecimal weight)
	{
		this.weight = weight;
	}

	public String getShape()
	{
		return shape;
	}

	public void setShape(String shape)
	{
		this.shape = shape;
	}

	public BigDecimal getLength()
	{
		return length;
	}

	public void setLength(BigDecimal length)
	{
		this.length = length;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
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
