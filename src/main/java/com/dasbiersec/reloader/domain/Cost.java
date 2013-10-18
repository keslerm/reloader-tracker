package com.dasbiersec.reloader.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cost
{
	private Recipe recipe;

    public Cost(Recipe recipe)
    {
	    this.recipe = recipe;
    }

	public BigDecimal getTotalCost()
	{
		if (getBrassCost() == null || getPowderCost() == null || getBulletCost() == null || getPrimerCost() == null)
			return null;

		return getBrassCost().add(getPrimerCost()).add(getBulletCost()).add(getPowderCost());
	}

	public BigDecimal getBrassCost()
	{
		if (recipe.getBrass() == null || recipe.getBrass().getAmount() == null)
			return null;

		return recipe.getBrass().getCost().divide((recipe.getBrass().getAmount()), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getPrimerCost()
	{
		if (recipe.getPrimer() == null || recipe.getPrimer().getAmount() == null)
			return null;

		return recipe.getPrimer().getCost().divide(recipe.getPrimer().getAmount(), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getBulletCost()
	{
		if (recipe.getBullet() == null || recipe.getBullet().getAmount() == null)
			return null;

		return recipe.getBullet().getCost().divide(recipe.getBullet().getAmount(), 4, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getPowderCost()
	{
		if (recipe.getPowder() == null || recipe.getPowder().getAmount() == null || recipe.getPowderCharge() == null)
			return null;

		return recipe.getPowder().getCost().divide(recipe.getPowder().getAmount(), 4, RoundingMode.HALF_UP).multiply(recipe.getPowderCharge()).setScale(2, RoundingMode.HALF_UP);
	}

}
