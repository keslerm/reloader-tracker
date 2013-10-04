package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.AccountDetails;
import com.dasbiersec.reloader.domain.Cost;
import com.dasbiersec.reloader.domain.Note;
import com.dasbiersec.reloader.domain.Recipe;
import com.dasbiersec.reloader.repos.ComponentRepository;
import com.dasbiersec.reloader.repos.NoteRepository;
import com.dasbiersec.reloader.repos.RecipeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service
public class RecipeService
{
    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private RecipeRepository recipeRepository;

	@Autowired
	private NoteRepository noteRepository;

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

	public Iterable<Note> getNotes(Integer recipeId)
	{
		Recipe recipe = recipeRepository.findOne(recipeId);
		return recipe.getNotes();
	}

	public Note createNote(Integer recipeId, Note note)
	{
		Recipe recipe = recipeRepository.findOne(recipeId);
		if (recipe.getNotes() == null)
			recipe.setNotes(new ArrayList<Note>());

		recipe.getNotes().add(note);
		note.setRecipe(recipe);
		recipeRepository.save(recipe);

		return note;
	}

    public Note saveNote(Integer noteId, Note note)
    {
        Note existing = noteRepository.findOne(noteId);

        if (existing == null)
            throw new EntityNotFoundException("No note found");

        existing.setNote(note.getNote());
        noteRepository.save(note);

        return noteRepository.findOne(noteId);
    }

    private Integer getCurrentUser()
    {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountDetails.getId();
    }
}
