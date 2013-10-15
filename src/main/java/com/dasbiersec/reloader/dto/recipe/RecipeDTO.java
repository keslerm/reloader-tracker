package com.dasbiersec.reloader.dto.recipe;

import com.dasbiersec.reloader.domain.Component;

import java.math.BigDecimal;

public class RecipeDTO
{
	public Integer id;
	public String description;

	public Component bullet;
	public Component powder;
	public Component primer;
	public Component brass;

	public BigDecimal powderCharge;
	public BigDecimal coal;

	public String caliber;
	public CostDTO cost;
}
