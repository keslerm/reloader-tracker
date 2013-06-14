package com.dasbiersec.reloader.helpers;

import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.model.CostPerRound;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
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

		costPerRound.setBrass(brassCost.setScale(2, RoundingMode.HALF_UP));
		costPerRound.setBullet(bulletCost.setScale(2, RoundingMode.HALF_UP));
		costPerRound.setPowder(powderCost.setScale(2, RoundingMode.HALF_UP));
		costPerRound.setPrimer(primerCost.setScale(2, RoundingMode.HALF_UP));
		costPerRound.setTotal(total.setScale(2, RoundingMode.HALF_UP));

		batch.setCostPerRound(costPerRound);
		batch.setCostForBatch(total.multiply(new BigDecimal(batch.getCount())).setScale(2, RoundingMode.HALF_UP));
	}
}
