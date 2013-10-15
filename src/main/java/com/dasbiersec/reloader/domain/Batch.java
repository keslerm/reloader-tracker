package com.dasbiersec.reloader.domain;

import javax.persistence.*;

@Entity
@Table(name = "batch")
public class Batch extends AbstractEntity
{
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private int count;
	private String note;

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public Recipe getRecipe()
    {
        return recipe;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

}
