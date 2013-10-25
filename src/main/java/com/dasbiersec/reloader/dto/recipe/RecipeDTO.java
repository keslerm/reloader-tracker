package com.dasbiersec.reloader.dto.recipe;

import com.dasbiersec.reloader.dto.component.BrassDTO;
import com.dasbiersec.reloader.dto.component.BulletDTO;
import com.dasbiersec.reloader.dto.component.ComponentDTO;

import java.math.BigDecimal;

public class RecipeDTO
{
	public Integer id;
	public Integer userId;
	public String description;

	public BulletDTO bullet;
	public ComponentDTO powder;
	public ComponentDTO primer;
	public BrassDTO brass;

	public BigDecimal powderCharge;
	public BigDecimal coal;

	public String caliber;
	public CostDTO cost;
	public Boolean compressed;
}
