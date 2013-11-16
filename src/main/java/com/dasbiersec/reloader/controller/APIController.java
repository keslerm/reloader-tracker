package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.domain.*;
import com.dasbiersec.reloader.dto.component.ComponentDTO;
import com.dasbiersec.reloader.dto.log.LogDTO;
import com.dasbiersec.reloader.dto.recipe.RecipeDTO;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.mapper.LogMapper;
import com.dasbiersec.reloader.mapper.RecipeMapper;
import com.dasbiersec.reloader.service.BatchService;
import com.dasbiersec.reloader.service.ComponentService;
import com.dasbiersec.reloader.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

	/****** Recipes ******/
	@RequestMapping(value = "recipes", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
	public ResponseEntity<Iterable<RecipeDTO>> getRecipes()
	{
		return new ResponseEntity<Iterable<RecipeDTO>>(RecipeMapper.domainToDTO(recipeService.getAllRecipes()), HttpStatus.OK);
	}

	@RequestMapping(value = "recipes/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<RecipeDTO> getRecipe(@PathVariable Integer id)
	{
		RecipeDTO recipe = RecipeMapper.domainToDTO(recipeService.getRecipe(id));

		if (recipe == null)
			return new ResponseEntity<RecipeDTO>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<RecipeDTO>(recipe, HttpStatus.OK);
	}

    @RequestMapping(value = "recipes", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<Void> createRecipe(@RequestBody RecipeDTO recipe)
    {
	    recipeService.createRecipe(recipe);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "recipes/{id}", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<Void> updateRecipe(@PathVariable("id") Integer id, @RequestBody RecipeDTO recipe)
    {
        Recipe existing = recipeService.getRecipe(id);
        recipeService.saveRecipe(existing, recipe);

	    return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "recipes/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<Void> deleteRecipe(@PathVariable("id") Integer id)
    {
        Recipe recipe = recipeService.getRecipe(id);
        recipeService.deleteRecipe(recipe);

	    return new ResponseEntity<Void>(HttpStatus.OK);
    }




	/****** Batches ******/
    @RequestMapping(value = "recipes/{id}/batches", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Batch>> getBatches(@PathVariable Integer id)
    {
        List<Batch> batches = batchService.getBatches(id);
        return new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
    }

    @RequestMapping(value = "recipes/{recipeId}/batches", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createBatch(@PathVariable Integer recipeId, @RequestBody Batch batch)
    {
        batchService.createBatch(recipeId, batch);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "recipes/*/batches/{batchId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> updateBatch(@PathVariable Integer batchId, @RequestBody Batch batch)
    {
        batchService.saveBatch(batchId, batch);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



	/****** Logs ******/
	@RequestMapping(value = "recipes/{id}/logs", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<LogDTO>> getLogs(@PathVariable Integer id)
	{
        Recipe recipe = recipeService.getRecipe(id);

        List<LogDTO> logs = LogMapper.domainToDTO(recipe.getLogs());

		return new ResponseEntity<Iterable<LogDTO>>(logs, HttpStatus.OK);
	}

	@RequestMapping(value = "recipes/{recipeId}/logs", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createLog(@PathVariable Integer recipeId, @RequestBody LogDTO log)
	{
        Recipe recipe = recipeService.getRecipe(recipeId);

		recipeService.createLog(recipe, log);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "recipes/{recipeId}/logs/{logId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Void> updateLog(@PathVariable Integer recipeId, @PathVariable Integer logId, @RequestBody LogDTO log)
	{
        Recipe recipe = recipeService.getRecipe(recipeId);
		recipeService.saveLog(recipe, logId, log);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

    @RequestMapping(value = "recipes/{recipeId}/logs/{logId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LogDTO> getLog(@PathVariable Integer recipeId, @PathVariable Integer logId)
    {
        Recipe recipe = recipeService.getRecipe(recipeId);
        Log log = recipe.getLog(logId);

        return new ResponseEntity<LogDTO>(LogMapper.domainToDTO(log), HttpStatus.OK);
    }



	/****** Components ******/
	@RequestMapping(value = "components", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<ComponentDTO>> getComponents()
	{
		Iterable<ComponentDTO> components = componentService.getAllComponents();
		return new ResponseEntity<Iterable<ComponentDTO>>(components, HttpStatus.OK);
	}

	@RequestMapping(value = "components/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ComponentDTO> getComponent(@PathVariable Integer id)
	{
		return new ResponseEntity<ComponentDTO>(componentService.getComponentById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "components", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createComponent(@RequestBody ComponentDTO componentDTO)
	{
		componentService.createComponent(componentDTO);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

    @RequestMapping(value = "components/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> saveComponent(@PathVariable Integer id, @RequestBody ComponentDTO componentDTO)
    {
	    componentService.updateComponent(id, componentDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

	@RequestMapping(value = "components/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> deleteComponent(@PathVariable Integer id)
	{
		componentService.deleteComponentById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "components/type/{type}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Iterable<ComponentDTO>> searchForComponents(@PathVariable ComponentType type)
	{
		return new ResponseEntity<Iterable<ComponentDTO>>(componentService.getComponentByType(type), HttpStatus.OK);
	}
}
