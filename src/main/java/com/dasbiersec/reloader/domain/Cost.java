package com.dasbiersec.reloader.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cost implements Serializable
{
	private BigDecimal brass;
	private BigDecimal primer;
	private BigDecimal bullet;
	private BigDecimal powder;
	private BigDecimal total;

	public BigDecimal getTotal()
	{
		return total;
	}

	public void setTotal(BigDecimal total)
	{
		this.total = total;
	}

	public BigDecimal getBrass()
	{
		return brass;
	}

	public void setBrass(BigDecimal brass)
	{
		this.brass = brass;
	}

	public BigDecimal getPrimer()
	{
		return primer;
	}

	public void setPrimer(BigDecimal primer)
	{
		this.primer = primer;
	}

	public BigDecimal getBullet()
	{
		return bullet;
	}

	public void setBullet(BigDecimal bullet)
	{
		this.bullet = bullet;
	}

	public BigDecimal getPowder()
	{
		return powder;
	}

	public void setPowder(BigDecimal powder)
	{
		this.powder = powder;
	}
}
