package com.dasbiersec.reloader.mapper;

import com.dasbiersec.reloader.domain.Component;
import com.dasbiersec.reloader.dto.component.*;
import com.dasbiersec.reloader.enums.ComponentType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

	public static void copyDTOtoDomain(ComponentDTO dto, Component component)
	{
		// Maybe not the best way of doing this but quickest way I can think of
		// to get this going.
		if (dto instanceof BrassDTO)
		{
			component.setLength(((BrassDTO) dto).caseLength);
			component.setType(ComponentType.Brass);
		}
		else if (dto instanceof BulletDTO)
		{
			component.setShape(((BulletDTO) dto).shape);
			component.setWeight(((BulletDTO) dto).weight);
			component.setDiameter(((BulletDTO) dto).diameter);
			component.setCoefficient(((BulletDTO) dto).coefficient);
			component.setType(ComponentType.Bullet);
		}
		else if (dto instanceof PowderDTO)
		{
			component.setShape(((PowderDTO) dto).shape);
			component.setType(ComponentType.Powder);
		}
		else if (dto instanceof PrimerDTO)
		{
			component.setType(ComponentType.Primer);
		}

		component.setAmount(dto.amount);
		component.setCost(dto.cost);
		component.setName(dto.name);
		component.setManufacturer(dto.manufacturer);
	}

    public static List<ComponentDTO> domainToDTO(Iterable<Component> components)
    {
        List<ComponentDTO> dtos = new ArrayList<ComponentDTO>();

        for (Component component : components)
        {
            dtos.add(domainToDTO(component));
        }

        return dtos;
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
