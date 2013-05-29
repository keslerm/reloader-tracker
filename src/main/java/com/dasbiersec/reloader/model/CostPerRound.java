package com.dasbiersec.reloader.model;

import java.math.BigDecimal;

public class CostPerRound
{
	private BigDecimal costPerBrass;
	private BigDecimal costPerPrimer;
	private BigDecimal costPerBullet;
	private BigDecimal costOfPowder;
	private BigDecimal costPerRound;

	public BigDecimal getCostPerRound()
	{
		return costPerRound;
	}

	public void setCostPerRound(BigDecimal costPerRound)
	{
		this.costPerRound = costPerRound;
	}

	public BigDecimal getCostPerBrass()
	{
		return costPerBrass;
	}

	public void setCostPerBrass(BigDecimal costPerBrass)
	{
		this.costPerBrass = costPerBrass;
	}

	public BigDecimal getCostPerPrimer()
	{
		return costPerPrimer;
	}

	public void setCostPerPrimer(BigDecimal costPerPrimer)
	{
		this.costPerPrimer = costPerPrimer;
	}

	public BigDecimal getCostPerBullet()
	{
		return costPerBullet;
	}

	public void setCostPerBullet(BigDecimal costPerBullet)
	{
		this.costPerBullet = costPerBullet;
	}

	public BigDecimal getCostOfPowder()
	{
		return costOfPowder;
	}

	public void setCostOfPowder(BigDecimal costOfPowder)
	{
		this.costOfPowder = costOfPowder;
	}
}
