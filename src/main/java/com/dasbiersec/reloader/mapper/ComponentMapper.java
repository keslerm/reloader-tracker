package com.dasbiersec.reloader.mapper;

import com.dasbiersec.reloader.domain.Component;
import com.dasbiersec.reloader.dto.component.*;
import com.dasbiersec.reloader.enums.ComponentType;

import java.math.BigDecimal;

public class ComponentMapper
{
	public static ComponentDTO domainToDTO(Component component)
	{
		ComponentDTO dto;

		switch (component.getType())
		{
			case Brass:
				dto = createBrassDTO(component);
				break;

			case Bullet:
				dto = createBulletDTO(component);
				break;

			case Primer:
				dto = createPrimerDTO(component);
				break;

			case Powder:
				dto = createPowderDTO(component);
				break;

			default:
				dto = new ComponentDTO();
				break;
		}

		dto.manufacturer = component.getManufacturer();
		dto.amount = component.getAmount();
		dto.cost = component.getCost();
		dto.id = component.getId();
		dto.name = component.getName();
		dto.type = component.getType();

		return dto;
	}

	public static void copyDomainToDTO(Component component, ComponentDTO dto)
	{
		dto.amount = component.getAmount();
		dto.cost = component.getCost();
		dto.id = component.getId();
		dto.name = component.getName();
		dto.type = component.getType();
	}

	public static void copyDTOtoDomain(ComponentDTO dto, Component component)
	{
		component.setAmount(dto.amount);
		component.setCost(dto.cost);
		component.setName(dto.name);
		component.setType(dto.type);
	}

	public static BulletDTO createBulletDTO(Component component)
	{
		BulletDTO dto = new BulletDTO();
		dto.shape = component.getShape();
		dto.weight = component.getWeight();
		dto.diameter = component.getDiameter();
		dto.coefficient = component.getCoefficient();

		return dto;
	}

	public static BrassDTO createBrassDTO(Component component)
	{
		BrassDTO dto = new BrassDTO();
		dto.caseLength = component.getLength();

		return dto;
	}

	public static PowderDTO createPowderDTO(Component component)
	{
		PowderDTO dto = new PowderDTO();
		dto.shape = component.getShape();

		return dto;
	}

	public static PrimerDTO createPrimerDTO(Component component)
	{
		PrimerDTO dto = new PrimerDTO();

		return dto;
	}
}
