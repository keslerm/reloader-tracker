package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.dto.ComponentDTO;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.dto.CostDTO;
import com.dasbiersec.reloader.model.Recipe;
import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.service.ComponentService;
import com.dasbiersec.reloader.service.RecipeService;
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
	private ComponentService componentService;

    @Autowired
    private RecipeService recipeService;

	@RequestMapping(value = "recipe", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Iterable<Recipe> getRecipes()
	{
		return recipeService.getAllRecipes();
	}

	@RequestMapping(value = "recipe/{id}", method = RequestMethod.GET)
	public @ResponseBody
    Recipe getRecipe(@PathVariable Integer id)
	{
		return recipeService.getRecipeById(id);
	}

    @RequestMapping(value = "recipe/{id}/cost", method = RequestMethod.GET)
    public @ResponseBody CostDTO getCost(@PathVariable Integer id)
    {
        CostDTO cost = recipeService.getCost(id);
        return cost;
    }

	@RequestMapping(value = "recipe", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody
    Recipe saveRecipe(@RequestBody Recipe recipe)
	{
		return recipeService.saveRecipe(recipe);
	}

	@RequestMapping(value = "recipe/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteRecipe(@PathVariable("id") Integer id) throws IOException
	{
		Recipe recipe = recipeService.getRecipeById(id);

		if (recipe == null)
			throw new IOException("Could not find recipe with ID");

		recipeService.deleteRecipeById(recipe);
	}

	@RequestMapping(value = "component", method = RequestMethod.GET)
	public @ResponseBody Iterable<Component> getComponents()
	{
		return componentService.getAllComponents();
	}

	@RequestMapping(value = "component/{id}", method = RequestMethod.GET)
	public @ResponseBody Component getComponentById(@PathVariable Integer id)
	{
		return componentService.getComponentById(id);
	}

	@RequestMapping(value = "component", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ComponentDTO createComponent(@RequestBody ComponentDTO componentDTO)
	{
		return componentService.saveComponent(componentDTO);
	}

    @RequestMapping(value = "component/{id}", method = RequestMethod.PUT)
    public @ResponseBody ComponentDTO saveComponent(@PathVariable Integer id, @RequestBody ComponentDTO componentDTO)
    {
        componentService.saveComponent(componentDTO);

        return componentDTO;
    }

	@RequestMapping(value = "component/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteComponent(@PathVariable Integer id)
	{
		componentService.deleteComponentById(id);
	}

	@RequestMapping(value = "component/type/{type}", method = RequestMethod.GET)
	public @ResponseBody Iterable<Component> searchForComponents(@PathVariable ComponentType type)
	{
		return componentService.findComponentByType(type);
	}

	@RequestMapping(value = "auth", method = RequestMethod.GET)
	@ResponseBody
	public String test()
	{
		UserDetails test = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return test.getUsername();
	}
}
