package com.dasbiersec.reloader.dto.recipe;

import com.dasbiersec.reloader.domain.Recipe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CostDTO implements Serializable
{
	private BigDecimal brass;
	private BigDecimal primer;
	private BigDecimal bullet;
	private BigDecimal powder;
	private BigDecimal total;

    public CostDTO(Recipe recipe)
    {
        BigDecimal brassCost = recipe.getBrass().getCost().divide((recipe.getBrass().getAmount()), 4, RoundingMode.HALF_UP);
        BigDecimal primerCost = recipe.getPrimer().getCost().divide(recipe.getPrimer().getAmount(), 4, RoundingMode.HALF_UP);
        BigDecimal bulletCost = recipe.getBullet().getCost().divide(recipe.getBullet().getAmount(), 4, RoundingMode.HALF_UP);
        BigDecimal powderCost = recipe.getPowder().getCost().divide(recipe.getPowder().getAmount(), 4, RoundingMode.HALF_UP).multiply(recipe.getPowderCharge());

        BigDecimal total = brassCost.add(primerCost).add(bulletCost).add(powderCost);

        setBrass(brassCost.setScale(2, RoundingMode.HALF_UP));
        setBullet(bulletCost.setScale(2, RoundingMode.HALF_UP));
        setPowder(powderCost.setScale(2, RoundingMode.HALF_UP));
        setPrimer(primerCost.setScale(2, RoundingMode.HALF_UP));
        setTotal(total.setScale(2, RoundingMode.HALF_UP));
    }

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
