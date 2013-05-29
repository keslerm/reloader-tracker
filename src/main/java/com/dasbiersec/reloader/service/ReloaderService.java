package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.helpers.BatchHelper;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.repos.BatchRepository;
import com.dasbiersec.reloader.repos.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReloaderService
{
	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private ComponentRepository componentRepository;

	@Autowired
	private BatchHelper batchHelper;

	public Batch getBatchById(Integer id)
	{

		Batch batch = batchRepository.findOne(id);

		batchHelper.setCostPerRound(batch);

		return batch;
	}

	public Iterable<Batch> getAllBatches()
	{
		Iterable<Batch> batches = batchRepository.findAll();

		for (Batch batch : batches)
		{
			batchHelper.setCostPerRound(batch);
		}

		return batches;
	}

	public Batch saveBatch(Batch batch)
	{
		Batch rb = batchRepository.save(batch);
		return getBatchById(rb.getId());
	}

	public void deleteBatchById(Integer id)
	{
		batchRepository.delete(id);
	}

	public Iterable<Component> getAllComponents()
	{
		Iterable<Component> components = componentRepository.findAll();

		for (Component component : components)
		{
			if (component.getType() == ComponentType.Brass)
			{

			}
		}

		return components;
	}
}
