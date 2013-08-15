package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.domain.Batch;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.domain.Cost;
import com.dasbiersec.reloader.domain.Recipe;
import com.dasbiersec.reloader.domain.Component;
import com.dasbiersec.reloader.service.BatchService;
import com.dasbiersec.reloader.service.ComponentService;
import com.dasbiersec.reloader.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "api/")
public class APIController
{
	@Autowired
	protected ComponentService componentService;

    @Autowired
    protected RecipeService recipeService;

    @Autowired
    protected BatchService batchService;

	@RequestMapping(value = "recipes", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody
	Iterable<Recipe> getRecipes()
	{
		return recipeService.getAllRecipes();
	}

	@RequestMapping(value = "recipe/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Recipe getRecipe(@PathVariable Integer id)
	{
		return recipeService.getRecipeById(id);
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

    @RequestMapping(value = "recipe/{id}/cost", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Cost> getCost(@PathVariable Integer id)
    {
        Cost cost = recipeService.getCost(id);
        return new ResponseEntity<Cost>(cost, HttpStatus.OK);
    }

    @RequestMapping(value = "recipe/{id}/batches", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Batch>> getBatches(@PathVariable Integer id)
    {
        List<Batch> batches = batchService.getBatches(id);
        return new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
    }

    @RequestMapping(value = "recipe/{recipeId}/batch", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Batch> createBatch(@PathVariable Integer recipeId, @RequestBody Batch batch)
    {
        Batch in = batchService.createBatch(recipeId, batch);
        return new ResponseEntity<Batch>(in, HttpStatus.CREATED);
    }

    @RequestMapping(value = "recipe/*/batch/{batchId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Batch> updateBatch(@PathVariable Integer batchId, @RequestBody Batch batch)
    {
        Batch in = batchService.saveBatch(batchId, batch);
        return new ResponseEntity<Batch>(in, HttpStatus.OK);
    }

	@RequestMapping(value = "components", method = RequestMethod.GET)
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
	public @ResponseBody Component createComponent(@RequestBody Component componentDTO)
	{
		return componentService.saveComponent(componentDTO);
	}

    @RequestMapping(value = "component/{id}", method = RequestMethod.PUT)
    public @ResponseBody Component saveComponent(@PathVariable Integer id, @RequestBody Component componentDTO)
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
