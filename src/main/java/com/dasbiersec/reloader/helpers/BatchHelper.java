package com.dasbiersec.reloader.helpers;

import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.model.CostPerRound;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BatchHelper
{
	public void setCostPerRound(Batch batch)
	{
		CostPerRound costPerRound = new CostPerRound();

		BigDecimal brassCost = batch.getBrass().getCost().divide((batch.getBrass().getAmount()), 4, RoundingMode.HALF_UP);
		BigDecimal primerCost = batch.getPrimer().getCost().divide(batch.getPrimer().getAmount(), 4, RoundingMode.HALF_UP);
		BigDecimal bulletCost = batch.getBullet().getCost().divide(batch.getBullet().getAmount(), 4, RoundingMode.HALF_UP);
		BigDecimal powderCost = batch.getPowder().getCost().divide(batch.getPowder().getAmount(), 4, RoundingMode.HALF_UP).multiply(batch.getPowderCharge());

		BigDecimal total = brassCost.add(primerCost).add(bulletCost).add(powderCost);

		costPerRound.setCostPerBrass(brassCost);
		costPerRound.setCostPerBullet(bulletCost);
		costPerRound.setCostOfPowder(powderCost);
		costPerRound.setCostPerPrimer(primerCost);
		costPerRound.setCostPerRound(total);

		batch.setCostPerRound(costPerRound);
	}
}