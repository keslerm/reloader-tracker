package com.dasbiersec.reloader.helpers;

import com.dasbiersec.reloader.model.Recipe;
import com.dasbiersec.reloader.model.CostPerRound;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class RecipeHelper
{
	public static CostPerRound getCostPerRound(Recipe recipe)
	{
		CostPerRound costPerRound = new CostPerRound();

		BigDecimal brassCost = recipe.getBrass().getCost().divide((recipe.getBrass().getAmount()), 4, RoundingMode.HALF_UP);
		BigDecimal primerCost = recipe.getPrimer().getCost().divide(recipe.getPrimer().getAmount(), 4, RoundingMode.HALF_UP);
		BigDecimal bulletCost = recipe.getBullet().getCost().divide(recipe.getBullet().getAmount(), 4, RoundingMode.HALF_UP);
		BigDecimal powderCost = recipe.getPowder().getCost().divide(recipe.getPowder().getAmount(), 4, RoundingMode.HALF_UP).multiply(recipe.getPowderCharge());

		BigDecimal total = brassCost.add(primerCost).add(bulletCost).add(powderCost);

		costPerRound.setBrass(brassCost.setScale(2, RoundingMode.HALF_UP));
		costPerRound.setBullet(bulletCost.setScale(2, RoundingMode.HALF_UP));
		costPerRound.setPowder(powderCost.setScale(2, RoundingMode.HALF_UP));
		costPerRound.setPrimer(primerCost.setScale(2, RoundingMode.HALF_UP));
		costPerRound.setTotal(total.setScale(2, RoundingMode.HALF_UP));

        return costPerRound;
	}
}
