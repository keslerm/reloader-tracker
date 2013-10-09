package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.AccountDetails;
import com.dasbiersec.reloader.domain.recipe.Cost;
import com.dasbiersec.reloader.domain.log.Chronograph;
import com.dasbiersec.reloader.domain.log.Log;
import com.dasbiersec.reloader.entity.Batch;
import com.dasbiersec.reloader.entity.LogEntity;
import com.dasbiersec.reloader.entity.Recipe;
import com.dasbiersec.reloader.mapper.LogMapper;
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

    public Recipe getRecipe(Integer id)
    {
        Recipe recipe = recipeRepository.findByIdAndUserId(id, getCurrentUser());
        return recipe;
    }

    public Iterable<Recipe> getAllRecipes()
    {
        Iterable<Recipe> recipes = recipeRepository.findAllByUserId(getCurrentUser());
        return recipes;
    }

    public Cost getCost(Integer recipeId)
    {
        Recipe recipe = recipeRepository.findOne(recipeId);
        return recipe.getCost();
    }

    public Recipe createRecipe(Recipe recipe)
    {
        recipe.setUserId(getCurrentUser());
        Recipe saved = recipeRepository.save(recipe);
        return saved;
    }

    public Recipe saveRecipe(Integer recipeId, Recipe recipe)
    {
        Recipe existing = recipeRepository.findOne(recipeId);

        existing.setBullet(componentRepository.findOne(recipe.getBullet().getId()));
        existing.setPrimer(componentRepository.findOne(recipe.getPrimer().getId()));
	    existing.setPowder(componentRepository.findOne(recipe.getPowder().getId()));
	    existing.setBrass(componentRepository.findOne(recipe.getBrass().getId()));

        existing.setCaliber(recipe.getCaliber());
        existing.setCoal(recipe.getCoal());
        existing.setDescription(recipe.getDescription());
        existing.setPowderCharge(recipe.getPowderCharge());

        recipeRepository.save(existing);

        return recipeRepository.findOne(recipeId);
    }

	public Batch createBatch(Integer recipeId, Batch batch)
	{
		return batch;
	}

    public Recipe saveRecipe(Recipe recipe)
    {
        recipe.setUserId(getCurrentUser());

        Recipe rb = recipeRepository.save(recipe);

        return getRecipe(rb.getId());
    }

    public void deleteRecipeById(Recipe recipe)
    {
        recipeRepository.delete(recipe);
    }

	public Iterable<Log> getLogs(Integer recipeId)
	{
		Recipe recipe = recipeRepository.findOne(recipeId);

		List<Log> logs = new ArrayList<Log>();

		for (LogEntity entity : recipe.getLogs())
		{
			Log log = LogMapper.entityToDomain(entity);
			logs.add(log);
		}

		return logs;
	}

	public Log createLog(Integer recipeId, Log log)
	{
		Recipe recipe = recipeRepository.findOne(recipeId);
		if (recipe.getLogs() == null)
			recipe.setLogs(new ArrayList<LogEntity>());

		LogEntity entity = LogMapper.domainToEntity(log);

		recipe.getLogs().add(entity);
		entity.setRecipe(recipe);
		recipeRepository.save(recipe);

		return log;
	}

    public LogEntity saveLog(Integer noteId, LogEntity log)
    {
        LogEntity existing = logRepository.findOne(noteId);

        if (existing == null)
            throw new EntityNotFoundException("No log found");

        existing.setNote(log.getNote());
	    existing.setFirearm(log.getFirearm());
	    existing.setRange(log.getRange());
	    existing.setGroupSize(log.getGroupSize());
	    existing.setShotsInGroup(log.getShotsInGroup());
	    existing.setTargetDistance(log.getTargetDistance());

        logRepository.save(log);

        return logRepository.findOne(noteId);
    }

    private Integer getCurrentUser()
    {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountDetails.getId();
    }
}
