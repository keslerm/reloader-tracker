package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.ReloaderUserDetails;
import com.dasbiersec.reloader.dto.ComponentDTO;
import com.dasbiersec.reloader.dto.CostDTO;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.helpers.ComponentHelper;
import com.dasbiersec.reloader.model.Recipe;
import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.repos.RecipeRepository;
import com.dasbiersec.reloader.repos.ComponentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReloaderService
{
	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private ComponentRepository componentRepository;

    @Autowired
    private ComponentHelper componentHelper;

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

	@PreAuthorize("#recipe.userId == principal.id")
	public void deleteRecipeById(Recipe recipe)
	{
		recipeRepository.delete(recipe);
	}

	public Iterable<Component> getAllComponents()
	{
		Iterable<Component> components = componentRepository.findAll();

		for (Component component : components)
		{
			setRemainingComponentAmount(component);
		}

		return components;
	}

	public Iterable<Component> getComponentByType(ComponentType type)
	{
		return componentRepository.findComponentByType(type);
	}

	public Component getComponentById(Integer id)
	{
		Component component = componentRepository.findOne(id);
		setRemainingComponentAmount(component);
		return component;
	}

	public ComponentDTO saveComponent(ComponentDTO componentDTO)
	{
        Component component = componentRepository.findOne(componentDTO.getId());
        componentHelper.copyPropsFromDTO(component, componentDTO);

        return componentDTO;
	}

	public void deleteComponentById(Integer id)
	{
		componentRepository.delete(id);
	}

	public Iterable<Component> findComponentByType(ComponentType type)
	{
		Iterable<Component> components = componentRepository.findComponentByType(type);

		for (Component component : components)
		{
			setRemainingComponentAmount(component);
		}

		return components;
	}

	private void setRemainingComponentAmount(Component component)
	{

		Iterable<Recipe> batches = null;

		switch (component.getType())
		{
			case Brass:
				batches = recipeRepository.findByBrass(component);
				break;

			case Primer:
				batches = recipeRepository.findByPrimer(component);
				break;

			case Bullet:
				batches = recipeRepository.findByBullet(component);
				break;

			case Powder:
				batches = recipeRepository.findByPowder(component);
				break;
		}
	}

    private Integer getCurrentUser()
    {
        ReloaderUserDetails reloaderUserDetails = (ReloaderUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return reloaderUserDetails.getId();
    }
}
