package com.dasbiersec.reloader.dto.recipe;

import com.dasbiersec.reloader.dto.component.ComponentDTO;

import java.math.BigDecimal;

public class RecipeDTO
{
	public Integer id;
	public String description;

	public ComponentDTO bullet;
	public ComponentDTO powder;
	public ComponentDTO primer;
	public ComponentDTO brass;

	public BigDecimal powderCharge;
	public BigDecimal coal;

	public String caliber;
	public CostDTO cost;
	public Boolean compressed;
}
