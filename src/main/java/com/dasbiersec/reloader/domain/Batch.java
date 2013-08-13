package com.dasbiersec.reloader.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "batch")
public class Batch extends AbstractEntity
{
    @Column(name = "recipe_id")
    private Integer recipeId;
    private int count;
    private int remaining;

    public Integer getRecipeId()
    {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId)
    {
        this.recipeId = recipeId;
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
