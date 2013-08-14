package com.dasbiersec.reloader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private List<Batch> batchList;

    public List<Batch> getBatchList()
    {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList)
    {
        this.batchList = batchList;
    }

    @JsonIgnore
    public Cost getCost()
    {
        Cost dto = new Cost();

        BigDecimal brassCost = getBrass().getCost().divide((getBrass().getAmount()), 4, RoundingMode.HALF_UP);
        BigDecimal primerCost = getPrimer().getCost().divide(getPrimer().getAmount(), 4, RoundingMode.HALF_UP);
        BigDecimal bulletCost = getBullet().getCost().divide(getBullet().getAmount(), 4, RoundingMode.HALF_UP);
        BigDecimal powderCost = getPowder().getCost().divide(getPowder().getAmount(), 4, RoundingMode.HALF_UP).multiply(getPowderCharge());

        BigDecimal total = brassCost.add(primerCost).add(bulletCost).add(powderCost);

        dto.setBrass(brassCost.setScale(2, RoundingMode.HALF_UP));
        dto.setBullet(bulletCost.setScale(2, RoundingMode.HALF_UP));
        dto.setPowder(powderCost.setScale(2, RoundingMode.HALF_UP));
        dto.setPrimer(primerCost.setScale(2, RoundingMode.HALF_UP));
        dto.setTotal(total.setScale(2, RoundingMode.HALF_UP));

        return dto;
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
