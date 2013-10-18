package com.dasbiersec.reloader.mapper;

import com.dasbiersec.reloader.domain.Component;
import com.dasbiersec.reloader.domain.Cost;
import com.dasbiersec.reloader.domain.Recipe;
import com.dasbiersec.reloader.dto.component.BrassDTO;
import com.dasbiersec.reloader.dto.component.BulletDTO;
import com.dasbiersec.reloader.dto.recipe.CostDTO;
import com.dasbiersec.reloader.dto.recipe.RecipeDTO;

public class RecipeMapper
{
	public static RecipeDTO domainToDTO(Recipe recipe)
	{
		RecipeDTO dto = new RecipeDTO();
		dto.brass = (BrassDTO) ComponentMapper.domainToDTO(recipe.getBrass());
		dto.bullet = (BulletDTO) ComponentMapper.domainToDTO(recipe.getBullet());
		dto.id = recipe.getId();
		dto.powder = ComponentMapper.domainToDTO(recipe.getPowder());
		dto.powderCharge = recipe.getPowderCharge();
		dto.primer = ComponentMapper.domainToDTO(recipe.getPrimer());
		dto.coal = recipe.getCoal();
		dto.caliber = recipe.getCaliber();
		dto.description = recipe.getDescription();
		dto.compressed = recipe.getCompressed();

		dto.cost = domainToDTO(recipe.getCost());

		return dto;
	}

	public static void copyDTOToDomain(RecipeDTO dto, Recipe recipe)
	{
		recipe.setCaliber(dto.caliber);
		recipe.setCoal(dto.coal);
		recipe.setDescription(dto.description);
		recipe.setPowderCharge(dto.powderCharge);
		recipe.setCompressed(dto.compressed);

		// TODO: Not the best way to manage this
		recipe.setBrass(new Component(dto.brass.id));
		recipe.setBullet(new Component(dto.bullet.id));
		recipe.setPrimer(new Component(dto.primer.id));
		recipe.setPowder(new Component(dto.powder.id));
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
