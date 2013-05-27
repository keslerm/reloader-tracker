package com.dasbiersec.reloader.repos.impl;

import com.dasbiersec.reloader.dao.CostPerRound;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.repos.BatchRepository;
import com.dasbiersec.reloader.repos.BatchRepositoryExtension;
import com.dasbiersec.reloader.repos.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BatchRepositoryImpl implements BatchRepositoryExtension
{

	@Autowired
	ComponentRepository componentRepository;

	@Override
	public CostPerRound getCostPerRound(Batch batch)
	{
		CostPerRound costPerRound = new CostPerRound();
		costPerRound.setDescription(batch.getDescription());

		BigDecimal brassCost = batch.getBrass().getCost().divide((batch.getBrass().getAmount()), 4, RoundingMode.HALF_UP);
		BigDecimal primerCost = batch.getPrimer().getCost().divide(batch.getPrimer().getAmount(), 4, RoundingMode.HALF_UP);
		BigDecimal bulletCost = batch.getBullet().getCost().divide(batch.getBullet().getAmount(), 4, RoundingMode.HALF_UP);
		BigDecimal powderCost = batch.getPowder().getCost().divide(batch.getPowder().getAmount(), 4, RoundingMode.HALF_UP).multiply(batch.getPowderCharge());

		BigDecimal total = brassCost.add(primerCost).add(bulletCost).add(powderCost);

		costPerRound.setCostPerRound(total.toPlainString());
		return costPerRound;
	}
}
