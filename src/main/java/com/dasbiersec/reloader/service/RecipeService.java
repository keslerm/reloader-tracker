package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.AccountDetails;
import com.dasbiersec.reloader.domain.Cost;
import com.dasbiersec.reloader.domain.Recipe;
import com.dasbiersec.reloader.dto.RecipeDTO;
import com.dasbiersec.reloader.repos.RecipeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RecipeService
{
    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private RecipeRepository recipeRepository;

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
    public Recipe saveRecipe(Integer recipeId, RecipeDTO recipeDTO)
    {
        Recipe existing = recipeRepository.findOne(recipeId);

        // INCOMPLETE -----
        return existing;

    }

    public Recipe saveRecipe(Integer recipeId, Recipe recipe)
    {
        Recipe existing = recipeRepository.findOne(recipeId);

        existing.setBrass(recipe.getBrass());
        existing.setBullet(recipe.getBullet());
        existing.setPrimer(recipe.getPrimer());
        existing.setCaliber(recipe.getCaliber());
        existing.setCoal(recipe.getCoal());
        existing.setDescription(recipe.getDescription());
        existing.setPowder(recipe.getPowder());
        existing.setPowderCharge(recipe.getPowderCharge());

        recipeRepository.save(existing);

        return recipeRepository.findOne(recipeId);
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

    private Integer getCurrentUser()
    {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountDetails.getId();
    }
}
