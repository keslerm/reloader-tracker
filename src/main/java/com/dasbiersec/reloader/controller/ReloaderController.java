package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.auth.RestToken;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.service.ReloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReloaderController
{
	@Autowired
	private ReloaderService reloaderService;

	@RequestMapping(value = "batch", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Iterable<Batch> getBatches()
	{
		return reloaderService.getAllBatches();
	}

	@RequestMapping(value = "batch/{id}", method = RequestMethod.GET)
	public @ResponseBody Batch getBatch(@PathVariable Integer id)
	{
		return reloaderService.getBatchById(id);
	}

	@RequestMapping(value = "batch", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Batch saveBatch(@RequestBody Batch batch)
	{
		return reloaderService.saveBatch(batch);
	}

	@RequestMapping(value = "batch/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteBatch(@PathVariable("id") Integer id)
	{
		reloaderService.deleteBatchById(id);
	}

	@RequestMapping(value = "component", method = RequestMethod.GET)
	public @ResponseBody Iterable<Component> getComponents()
	{
		return reloaderService.getAllComponents();
	}

	@RequestMapping(value = "component/{id}", method = RequestMethod.GET)
	public @ResponseBody Component getComponentById(@PathVariable Integer id)
	{
		return reloaderService.getComponentById(id);
	}

	@RequestMapping(value = "component", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Component saveComponent(@RequestBody Component component)
	{
		return reloaderService.saveComponent(component);
	}

	@RequestMapping(value = "component/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteComponent(@PathVariable Integer id)
	{
		reloaderService.deleteComponentById(id);
	}

	@RequestMapping(value = "component/type/{type}", method = RequestMethod.GET)
	public @ResponseBody Iterable<Component> searchForComponents(@PathVariable ComponentType type)
	{
		return reloaderService.findComponentByType(type);
	}

	@RequestMapping(value = "auth", method = RequestMethod.GET)
	@ResponseBody
	public String test()
	{
		UserDetails test = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return (String) test.getUsername();
	}
}
