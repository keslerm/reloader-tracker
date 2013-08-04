package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.model.Recipe;
import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.service.ReloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping(value = "api/")
public class APIController
{
	@Autowired
	private ReloaderService reloaderService;

	@RequestMapping(value = "recipe", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Iterable<Recipe> getRecipes()
	{
		return reloaderService.getAllRecipes();
	}

	@RequestMapping(value = "recipe/{id}", method = RequestMethod.GET)
	public @ResponseBody
    Recipe getRecipe(@PathVariable Integer id)
	{
		return reloaderService.getRecipeById(id);
	}

	@RequestMapping(value = "recipe", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody
    Recipe saveRecipe(@RequestBody Recipe recipe)
	{
		return reloaderService.saveRecipe(recipe);
	}

	@RequestMapping(value = "recipe/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteRecipe(@PathVariable("id") Integer id) throws IOException
	{
		Recipe recipe = reloaderService.getRecipeById(id);

		if (recipe == null)
			throw new IOException("Could not find recipe with ID");

		reloaderService.deleteRecipeById(recipe);
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
		return test.getUsername();
	}
}
