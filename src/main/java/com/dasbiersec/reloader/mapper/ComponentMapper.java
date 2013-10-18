package com.dasbiersec.reloader.mapper;

import com.dasbiersec.reloader.domain.Component;
import com.dasbiersec.reloader.dto.component.ComponentDTO;

public class ComponentMapper
{
	public static ComponentDTO domainToDTO(Component component)
	{
		ComponentDTO dto = new ComponentDTO();
		dto.amount = component.getAmount();
		dto.cost = component.getCost();
		dto.id = component.getId();
		dto.name = component.getName();
		dto.type = component.getType();

		return dto;
	}

	public static void copyDTOtoDomain(ComponentDTO dto, Component component)
	{
		component.setAmount(dto.amount);
		component.setCost(dto.cost);
		component.setName(dto.name);
		component.setType(dto.type);
	}
}
