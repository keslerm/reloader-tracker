package com.dasbiersec.reloader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Note extends AbstractEntity
{
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	@JsonIgnore
	private Recipe recipe;

    private String note;

	public Recipe getRecipe()
	{
		return recipe;
	}

	public void setRecipe(Recipe recipe)
	{
		this.recipe = recipe;
	}

	public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }
}
