package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.domain.Batch;
import com.dasbiersec.reloader.domain.Log;
import com.dasbiersec.reloader.domain.Recipe;
import com.dasbiersec.reloader.dto.batch.BatchDTO;
import com.dasbiersec.reloader.dto.log.LogDTO;
import com.dasbiersec.reloader.dto.recipe.RecipeDTO;
import com.dasbiersec.reloader.helper.SecurityHelper;
import com.dasbiersec.reloader.mapper.BatchMapper;
import com.dasbiersec.reloader.mapper.LogMapper;
import com.dasbiersec.reloader.mapper.RecipeMapper;
import com.dasbiersec.reloader.repos.RecipeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class RecipeService
{
    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private RecipeRepository recipeRepository;

	@PostFilter("hasRole('ROLE_ADMIN') || filterObject.userId == principal.id")
	public Iterable<Recipe> getAllRecipes()
	{
		Iterable<Recipe> recipes = recipeRepository.findAll();
		return recipes;
	}

	@PostAuthorize("hasRole('ROLE_ADMIN') || returnObject == null || returnObject.userId == principal.id")
    public Recipe getRecipe(Integer id)
    {
        Recipe recipe = recipeRepository.findOne(id);
        return recipe;
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') || returnObject.userId == principal.id")
    public Recipe createRecipe(RecipeDTO dto)
    {
	    Recipe recipe = new Recipe();
	    RecipeMapper.copyDTOToDomain(dto, recipe);
        recipe.setUserId(SecurityHelper.getCurrentUserId());
        Recipe saved = recipeRepository.save(recipe);
        return getRecipe(saved.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || #existing.userId == principal.id")
    public Recipe saveRecipe(Recipe existing, RecipeDTO recipe)
    {

	    RecipeMapper.copyDTOToDomain(recipe, existing);
        recipeRepository.save(existing);

	    return getRecipe(existing.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || #recipe.userId == principal.id")
	public void deleteRecipe(Recipe recipe)
	{
		recipeRepository.delete(recipe);
	}

    @PreAuthorize("hasRole('ROLE_ADMIN') || #recipe.userId == principal.id")
	public Log createLog(Recipe recipe, LogDTO log)
	{
		Log entity = LogMapper.dtoToDomain(log);
		entity.setRecipe(recipe);

        recipe.addLog(entity);

		recipeRepository.save(recipe);

		return entity;
	}

    @PreAuthorize("hasRole('ROLE_ADMIN') || #recipe.userId == principal.id")
    public Log saveLog(Recipe recipe, Integer logId, LogDTO dto)
    {
        // fetch log from recipe
        Log log = recipe.getLog(logId);

        // update entity with dto
        LogMapper.copyDTOToDomain(dto, log);

        // save recipe
        recipeRepository.save(recipe);

        return log;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || #recipe.userId == principal.id")
    public Batch createBatch(Recipe recipe, BatchDTO batch)
    {
        Batch entity = BatchMapper.dtoToDomain(batch);
        entity.setRecipe(recipe);

        recipe.addBatch(entity);

        recipeRepository.save(recipe);

        return entity;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || #recipe.userId == principal.id")
    public Batch saveBatch(Recipe recipe, Integer batchId, BatchDTO dto)
    {
        Batch batch = recipe.getBatch(batchId);

        BatchMapper.copyDTOtoDomain(dto, batch);

        recipeRepository.save(recipe);

        return batch;
    }
}
