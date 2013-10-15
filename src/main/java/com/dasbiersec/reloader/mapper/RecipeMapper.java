package com.dasbiersec.reloader.mapper;

import com.dasbiersec.reloader.domain.Cost;
import com.dasbiersec.reloader.domain.Recipe;
import com.dasbiersec.reloader.dto.recipe.CostDTO;
import com.dasbiersec.reloader.dto.recipe.RecipeDTO;

public class RecipeMapper
{
	public static RecipeDTO domainToDTO(Recipe recipe)
	{
		RecipeDTO dto = new RecipeDTO();
		dto.brass = recipe.getBrass();
		dto.bullet = recipe.getBullet();
		dto.id = recipe.getId();
		dto.powder = recipe.getPowder();
		dto.powderCharge = recipe.getPowderCharge();
		dto.primer = recipe.getPrimer();
		dto.coal = recipe.getCoal();
		dto.caliber = recipe.getCaliber();
		dto.description = recipe.getDescription();

		dto.cost = domainToDTO(recipe.getCost());

		return dto;
	}

	public static void copyDTOToDomain(RecipeDTO dto, Recipe recipe)
	{
		recipe.setBrass(dto.brass);
		recipe.setBullet(dto.bullet);
		recipe.setCaliber(dto.caliber);
		recipe.setCoal(dto.coal);
		recipe.setDescription(dto.description);
		recipe.setPrimer(dto.primer);
		recipe.setPowder(dto.powder);
		recipe.setPowderCharge(dto.powderCharge);
	}

	public static CostDTO domainToDTO(Cost cost)
	{
		CostDTO dto = new CostDTO();
		dto.brass = cost.getBrassCost();
		dto.bullet = cost.getBulletCost();
		dto.powder = cost.getPowderCost();
		dto.primer = cost.getPrimerCost();
		dto.total = cost.getTotalCost();
		return dto;
	}
}
