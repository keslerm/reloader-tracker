package com.dasbiersec.reloader.domain;

import javax.persistence.Column;

public class Note extends AbstractEntity
{
    @Column(name = "recipe_id")
    private Integer recipeId;

    private String note;

    public Integer getRecipeId()
    {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId)
    {
        this.recipeId = recipeId;
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
