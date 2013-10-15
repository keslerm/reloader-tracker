package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.AccountDetails;
import com.dasbiersec.reloader.domain.Cost;
import com.dasbiersec.reloader.domain.Log;
import com.dasbiersec.reloader.dto.log.LogDTO;
import com.dasbiersec.reloader.domain.Batch;
import com.dasbiersec.reloader.domain.Recipe;
import com.dasbiersec.reloader.dto.recipe.RecipeDTO;
import com.dasbiersec.reloader.mapper.LogMapper;
import com.dasbiersec.reloader.mapper.RecipeMapper;
import com.dasbiersec.reloader.repos.ComponentRepository;
import com.dasbiersec.reloader.repos.LogRepository;
import com.dasbiersec.reloader.repos.RecipeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService
{
    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private RecipeRepository recipeRepository;

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private ComponentRepository componentRepository;


	// Recipes
	public Iterable<RecipeDTO> getAllRecipes()
	{
		Iterable<Recipe> recipes = recipeRepository.findAllByUserId(getCurrentUser());

		List<RecipeDTO> dto = new ArrayList<RecipeDTO>();

		for (Recipe recipe : recipes)
		{
			dto.add(RecipeMapper.domainToDTO(recipe));
		}

		return dto;
	}

    public RecipeDTO getRecipe(Integer id)
    {
        Recipe recipe = recipeRepository.findByIdAndUserId(id, getCurrentUser());
	    return RecipeMapper.domainToDTO(recipe);
    }

    public RecipeDTO createRecipe(RecipeDTO dto)
    {
	    Recipe recipe = new Recipe();
	    RecipeMapper.copyDTOToDomain(dto, recipe);
        recipe.setUserId(getCurrentUser());
        Recipe saved = recipeRepository.save(recipe);
        return getRecipe(saved.getId());
    }

    public RecipeDTO saveRecipe(Integer recipeId, RecipeDTO recipe)
    {
        Recipe existing = recipeRepository.findOne(recipeId);

	    RecipeMapper.copyDTOToDomain(recipe, existing);
        recipeRepository.save(existing);

	    return getRecipe(recipeId);
    }

	public void deleteRecipeById(Integer id)
	{
		recipeRepository.delete(id);
	}


	// Logs
	public Iterable<LogDTO> getLogs(Integer recipeId)
	{
		Recipe recipe = recipeRepository.findOne(recipeId);

		List<LogDTO> logs = new ArrayList<LogDTO>();

		for (Log entity : recipe.getLogs())
		{
			LogDTO log = LogMapper.domainToDTO(entity);
			logs.add(log);
		}

		return logs;
	}

	public LogDTO createLog(Integer recipeId, LogDTO log)
	{
		Recipe recipe = recipeRepository.findOne(recipeId);

		if (recipe.getLogs() == null)
			recipe.setLogs(new ArrayList<Log>());

		Log entity = LogMapper.dtoToDomain(log);

		entity.setRecipe(recipe);
		recipe.getLogs().add(entity);

		recipeRepository.save(recipe);

		return LogMapper.domainToDTO(entity);
	}

    public LogDTO saveLog(Integer logId, LogDTO dto)
    {
        Log existing = logRepository.findOne(logId);

        if (existing == null)
            throw new EntityNotFoundException("No log with id " + logId + " found");

	    LogMapper.copyDTOToDomain(dto, existing);
	    logRepository.save(existing);

        return LogMapper.domainToDTO(logRepository.findOne(logId));
    }

    private Integer getCurrentUser()
    {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountDetails.getId();
    }
}
