package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.model.CostPerRound;
import com.dasbiersec.reloader.model.Batch;

public interface ExtendedBatchRepository
{
	public CostPerRound getCostPerRound(Batch batch);
}
