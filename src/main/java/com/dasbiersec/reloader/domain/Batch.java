package com.dasbiersec.reloader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "batch")
public class Batch extends AbstractEntity
{
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private Recipe recipe;

    private int count;
    private int remaining;

    public Recipe getRecipe()
    {
        return recipe;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    public int getRemaining()
    {
        return remaining;
    }

    public void setRemaining(int remaining)
    {
        this.remaining = remaining;
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
