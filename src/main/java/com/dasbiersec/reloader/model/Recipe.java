package com.dasbiersec.reloader.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "recipe")
public class Recipe extends AbstractEntity implements Serializable
{
	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name="bullet_id")
	private Component bullet;

	@ManyToOne
	@JoinColumn(name="powder_id")
	private Component powder;

	@ManyToOne
	@JoinColumn(name="primer_id")
	private Component primer;

	@ManyToOne
	@JoinColumn(name="brass_id")
	private Component brass;

	@Column(name = "powder_charge")
	private BigDecimal powderCharge;

    @Column(name = "coal")
    private BigDecimal coal;

	@Transient
	private CostPerRound costPerRound;

	@Column(name = "user_id")
	private Integer userId;

	public BigDecimal getCoal()
    {
        return coal;
    }

    public void setCoal(BigDecimal coal)
    {
        this.coal = coal;
    }

    public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public CostPerRound getCostPerRound()
	{
		return costPerRound;
	}

	public void setCostPerRound(CostPerRound costPerRound)
	{
		this.costPerRound = costPerRound;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Component getBullet()
	{
		return bullet;
	}

	public void setBullet(Component bullet)
	{
		this.bullet = bullet;
	}

	public Component getPowder()
	{
		return powder;
	}

	public void setPowder(Component powder)
	{
		this.powder = powder;
	}

	public Component getPrimer()
	{
		return primer;
	}

	public void setPrimer(Component primer)
	{
		this.primer = primer;
	}

	public Component getBrass()
	{
		return brass;
	}

	public void setBrass(Component brass)
	{
		this.brass = brass;
	}

	public BigDecimal getPowderCharge()
	{
		return powderCharge;
	}

	public void setPowderCharge(BigDecimal powderCharge)
	{
		this.powderCharge = powderCharge;
	}
}