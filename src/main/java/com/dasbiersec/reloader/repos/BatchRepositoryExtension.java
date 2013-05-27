package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.dao.CostPerRound;
import com.dasbiersec.reloader.model.Batch;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BatchRepositoryExtension <T>
{
	public CostPerRound getCostPerRound(Batch batch);
}
