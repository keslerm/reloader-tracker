package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.domain.Log;
import com.dasbiersec.reloader.domain.Recipe;
import com.dasbiersec.reloader.dto.log.LogDTO;
import com.dasbiersec.reloader.dto.recipe.RecipeDTO;
import com.dasbiersec.reloader.helper.SecurityHelper;
import com.dasbiersec.reloader.mapper.LogMapper;
import com.dasbiersec.reloader.mapper.RecipeMapper;
import com.dasbiersec.reloader.repos.LogRepository;
import com.dasbiersec.reloader.repos.RecipeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
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


	// Logs
	public Iterable<LogDTO> getLogs(Integer recipeId)
	{
		Recipe recipe = getRecipe(recipeId);

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
		Recipe recipe = getRecipe(recipeId);

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
}
