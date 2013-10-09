package com.dasbiersec.reloader.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "log")
public class LogEntity extends AbstractEntity
{
	@ManyToOne
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;

    private String note;
	private String range;
	private String firearm;
	private String groupSize;
	private String shotsInGroup;
	private String targetDistance;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<Integer> fps;

	public List<Integer> getFps()
	{
		return fps;
	}

	public void setFps(List<Integer> fps)
	{
		this.fps = fps;
	}

	public String getGroupSize()
	{
		return groupSize;
	}

	public void setGroupSize(String groupSize)
	{
		this.groupSize = groupSize;
	}

	public String getShotsInGroup()
	{
		return shotsInGroup;
	}

	public void setShotsInGroup(String shotsInGroup)
	{
		this.shotsInGroup = shotsInGroup;
	}

	public String getTargetDistance()
	{
		return targetDistance;
	}

	public void setTargetDistance(String targetDistance)
	{
		this.targetDistance = targetDistance;
	}

	public String getRange()
	{
		return range;
	}

	public void setRange(String range)
	{
		this.range = range;
	}

	public String getFirearm()
	{
		return firearm;
	}

	public void setFirearm(String firearm)
	{
		this.firearm = firearm;
	}

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
