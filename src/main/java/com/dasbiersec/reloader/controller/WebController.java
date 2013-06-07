package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.service.ReloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController
{
	@Autowired
	private ReloaderService reloaderService;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String printWelcome(ModelMap modelMap)
	{

		Iterable<Batch> batches = reloaderService.getAllBatches();
		modelMap.addAttribute("batches", batches);
		return "batches";
	}
}
