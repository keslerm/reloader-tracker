package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.AccountDetails;
import com.dasbiersec.reloader.dto.CostDTO;
import com.dasbiersec.reloader.model.Recipe;
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

    public Recipe getRecipeById(Integer id)
    {

        Recipe recipe = recipeRepository.findByIdAndUserId(id, getCurrentUser());

        return recipe;
    }

    public Iterable<Recipe> getAllRecipes()
    {
        Iterable<Recipe> recipes = recipeRepository.findAllByUserId(getCurrentUser());
        return recipes;
    }

    public CostDTO getCost(Integer recipeId)
    {
        Recipe recipe = recipeRepository.findOne(recipeId);
        return recipe.getCost();
    }

    public Recipe saveRecipe(Recipe recipe)
    {
        // retrieve existing
        Recipe existing = recipeRepository.findByIdAndUserId(recipe.getId(), getCurrentUser());

        if (existing != null)
        {
            recipe.setCreateDate(existing.getCreateDate());
        }

        recipe.setUserId(getCurrentUser());

        Recipe rb = recipeRepository.save(recipe);

        return getRecipeById(rb.getId());
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
