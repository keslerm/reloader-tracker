package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.model.CostPerRound;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.repos.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReloaderController
{
	@Autowired
	private BatchRepository batchRepository;

	@RequestMapping(value = "batch", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Iterable<Batch> getBatches()
	{
		Iterable<Batch> batches = batchRepository.findAll();

		for (Batch batch : batches)
		{
			batch.setCostPerRound(batchRepository.getCostPerRound(batch));
		}

		return batches;
	}

	@RequestMapping(value = "batch/{id}", method = RequestMethod.GET)
	public @ResponseBody Batch getBatch(@PathVariable Integer id)
	{
		Batch batch = batchRepository.findOne(id);

		batch.setCostPerRound(batchRepository.getCostPerRound(batch));

		return batch;
	}

	@RequestMapping(value = "batch", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Batch saveBatch(@RequestBody Batch batch)
	{
		Batch rb = batchRepository.save(batch);
		return rb;
	}

}
