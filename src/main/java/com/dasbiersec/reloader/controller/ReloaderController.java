package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.helpers.BatchHelper;
import com.dasbiersec.reloader.model.CostPerRound;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.repos.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Controller
public class ReloaderController
{
	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private BatchHelper batchHelper;

	@RequestMapping(value = "batch", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Iterable<Batch> getBatches()
	{
		Iterable<Batch> batches = batchRepository.findAll();

		for (Batch batch : batches)
		{
			batchHelper.setCostPerRound(batch);
		}

		return batches;
	}

	@RequestMapping(value = "batch/{id}", method = RequestMethod.GET)
	public @ResponseBody Batch getBatch(@PathVariable Integer id)
	{
		Batch batch = batchRepository.findOne(id);

		batchHelper.setCostPerRound(batch);

		return batch;
	}

	@RequestMapping(value = "batch", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Batch saveBatch(@RequestBody Batch batch)
	{
		Batch rb = batchRepository.save(batch);
		return batchRepository.findOne(rb.getId());
	}

	@RequestMapping(value = "batch/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteBatch(@PathVariable("id") Integer id)
	{
		batchRepository.delete(id);
	}

}
