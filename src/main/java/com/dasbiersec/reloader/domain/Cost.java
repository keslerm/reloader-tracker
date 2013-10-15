package com.dasbiersec.reloader.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cost
{
	private BigDecimal brass;
	private BigDecimal primer;
	private BigDecimal bullet;
	private BigDecimal powder;
	private BigDecimal total;

    public Cost(Recipe recipe)
    {
        brass = recipe.getBrass().getCost().divide((recipe.getBrass().getAmount()), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
        primer = recipe.getPrimer().getCost().divide(recipe.getPrimer().getAmount(), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
        bullet = recipe.getBullet().getCost().divide(recipe.getBullet().getAmount(), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
        powder = recipe.getPowder().getCost().divide(recipe.getPowder().getAmount(), 4, RoundingMode.HALF_UP).multiply(recipe.getPowderCharge()).setScale(2, RoundingMode.HALF_UP);

        total = brass.add(primer).add(bullet).add(powder);
    }

	public BigDecimal getTotal()
	{
		return total;
	}

	public BigDecimal getBrass()
	{
		return brass;
	}

	public BigDecimal getPrimer()
	{
		return primer;
	}

	public BigDecimal getBullet()
	{
		return bullet;
	}

	public BigDecimal getPowder()
	{
		return powder;
	}

}
