package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.dao.CostPerRound;
import com.dasbiersec.reloader.dao.ReportingDAO;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.repos.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class ReportingController
{
	@Autowired
	private BatchRepository batchRepository;

	@RequestMapping(value = "/cpr/{batch}", method = RequestMethod.GET)
	public @ResponseBody CostPerRound getCPR(@PathVariable String batch)
	{
		Batch b1 = batchRepository.findOne(Integer.parseInt(batch));

		CostPerRound cpr = batchRepository.getCostPerRound(b1);
		return cpr;
	}

}
