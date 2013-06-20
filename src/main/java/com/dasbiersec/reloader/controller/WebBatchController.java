package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.service.ReloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/")
@SessionAttributes(types = {Batch.class, Component.class})
public class WebBatchController
{
	@Autowired
	private ReloaderService reloaderService;

	@RequestMapping(method = RequestMethod.GET, value = "/batches")
	public String batches(ModelMap modelMap)
	{
		Iterable<Batch> batches = reloaderService.getAllBatches();
		modelMap.addAttribute("batches", batches);
		return "batches";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/batches/add")
	public String addBatch(ModelMap map)
	{
		Batch batch = new Batch();
		map.addAttribute("batch", batch);

		return "batchform";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/batches/{id}/details")
	public String batchDetails(ModelMap map, @PathVariable(value = "id") Integer id)
	{
		Batch batch = reloaderService.getBatchById(id);
		map.addAttribute("batch", batch);

		return "batchdetails";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/batches/{id}/edit")
	public String editBatch(ModelMap map, @PathVariable(value = "id") Integer id)
	{
		Batch batch = reloaderService.getBatchById(id);
		map.addAttribute("batch", batch);

		return "batchform";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/batches/save")
	public String saveBatch(@ModelAttribute(value = "batch") Batch batch, BindingResult result, SessionStatus status, ModelMap map)
	{
		Batch newBatch = reloaderService.saveBatch(batch);
		map.addAttribute("batch", newBatch);
		return "batchform";
	}

	@ModelAttribute("allBullets")
	public Iterable<Component> populateBullets()
	{
		return reloaderService.findComponentByType(ComponentType.Bullet);
	}

	@ModelAttribute("allBrass")
	public Iterable<Component> populateBrass()
	{
		return reloaderService.findComponentByType(ComponentType.Brass);
	}

	@ModelAttribute("allPowders")
	public Iterable<Component> populatePowder()
	{
		return reloaderService.findComponentByType(ComponentType.Powder);
	}

	@ModelAttribute("allPrimers")
	public Iterable<Component> populatePrimer()
	{
		return reloaderService.findComponentByType(ComponentType.Primer);
	}
}
