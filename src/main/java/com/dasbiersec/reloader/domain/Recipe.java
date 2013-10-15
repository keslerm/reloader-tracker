package com.dasbiersec.reloader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


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

	@Column(name = "user_id")
	private Integer userId;

    @Column(name = "caliber")
    private String caliber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private List<Batch> batches;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.PERSIST)
	private List<Log> logs;

    public List<Batch> getBatches()
    {
        return batches;
    }

    public void setBatches(List<Batch> batches)
    {
        this.batches = batches;
    }

	public List<Log> getLogs()
	{
		return logs;
	}

	public void setLogs(List<Log> logs)
	{
		this.logs = logs;
	}

    public Cost getCost()
    {
        return new Cost(this);
    }

    public String getCaliber()
    {
        return caliber;
    }

    public void setCaliber(String caliber)
    {
        this.caliber = caliber;
    }

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
