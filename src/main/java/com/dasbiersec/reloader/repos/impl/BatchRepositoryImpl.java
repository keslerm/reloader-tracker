package com.dasbiersec.reloader.repos.impl;

import com.dasbiersec.reloader.model.CostPerRound;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.repos.ComponentRepository;
import com.dasbiersec.reloader.repos.ExtendedBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Resource
public class BatchRepositoryImpl implements ExtendedBatchRepository
{

	@Autowired
	ComponentRepository componentRepository;

	public CostPerRound getCostPerRound(Batch batch)
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

		return costPerRound;
	}
}
