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
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    @ResponseBody
	public Iterable<Recipe> getRecipes()
	{
		return recipeService.getAllRecipes();
	}

	@RequestMapping(value = "recipes/{id}", method = RequestMethod.GET)
    @ResponseBody
	public Recipe getRecipe(@PathVariable Integer id)
	{
		return recipeService.getRecipe(id);
	}

    @RequestMapping(value = "recipes", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Recipe saveRecipe(@RequestBody Recipe recipe)
    {
        return recipeService.createRecipe(recipe);
    }

    @RequestMapping(value = "recipes/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void deleteRecipe(@PathVariable("id") Integer id, @RequestBody Recipe recipe)
    {
        recipeService.saveRecipe(id, recipe);
    }

    @RequestMapping(value = "recipes/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteRecipe(@PathVariable("id") Integer id)
    {
        Recipe recipe = recipeService.getRecipe(id);

        if (recipe == null)
            throw new EntityNotFoundException("Could not find recipe with ID");

        recipeService.deleteRecipeById(recipe);
    }

    @RequestMapping(value = "recipes/{id}/cost", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Cost> getCost(@PathVariable Integer id)
    {
        Cost cost = recipeService.getCost(id);
        return new ResponseEntity<Cost>(cost, HttpStatus.OK);
    }

    @RequestMapping(value = "recipes/{id}/batches", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Batch>> getBatches(@PathVariable Integer id)
    {
        List<Batch> batches = batchService.getBatches(id);
        return new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
    }

    @RequestMapping(value = "recipes/{recipeId}/batches", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Batch> createBatch(@PathVariable Integer recipeId, @RequestBody Batch batch)
    {
        Batch in = batchService.createBatch(recipeId, batch);
        return new ResponseEntity<Batch>(in, HttpStatus.CREATED);
    }

    @RequestMapping(value = "recipes/*/batches/{batchId}", method = RequestMethod.PUT)
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

	@RequestMapping(value = "components/{id}", method = RequestMethod.GET)
	public @ResponseBody Component getComponentById(@PathVariable Integer id)
	{
		return componentService.getComponentById(id);
	}

	@RequestMapping(value = "components", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Component createComponent(@RequestBody Component componentDTO)
	{
		return componentService.saveComponent(componentDTO);
	}

    @RequestMapping(value = "components/{id}", method = RequestMethod.PUT)
    public @ResponseBody Component saveComponent(@PathVariable Integer id, @RequestBody Component componentDTO)
    {
        componentService.saveComponent(componentDTO);

        return componentDTO;
    }

	@RequestMapping(value = "components/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteComponent(@PathVariable Integer id)
	{
		componentService.deleteComponentById(id);
	}

	@RequestMapping(value = "components/type/{type}", method = RequestMethod.GET)
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
