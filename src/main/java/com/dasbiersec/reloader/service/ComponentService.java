package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.UserDetails;
import com.dasbiersec.reloader.dto.component.ComponentDTO;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.domain.Component;
import com.dasbiersec.reloader.mapper.ComponentMapper;
import com.dasbiersec.reloader.repos.ComponentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComponentService
{
	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private ComponentRepository componentRepository;

	public Iterable<ComponentDTO> getAllComponents()
	{
		Iterable<Component> components = componentRepository.findAllByUserId(getCurrentUser());

		List<ComponentDTO> list = new ArrayList<ComponentDTO>();

		for (Component comp : components)
		{
			list.add(ComponentMapper.domainToDTO(comp));
		}

		return list;
	}

	public Iterable<ComponentDTO> getComponentByType(ComponentType type)
	{
		Iterable<Component> components =  componentRepository.findComponentByUserIdAndType(getCurrentUser(), type);

		List<ComponentDTO> list = new ArrayList<ComponentDTO>();

		for (Component comp : components)
		{
			list.add(ComponentMapper.domainToDTO(comp));
		}

		return list;
	}

	public ComponentDTO getComponentById(Integer id)
	{
		return ComponentMapper.domainToDTO(componentRepository.findOneByUserIdAndId(getCurrentUser(), id));
	}

	public ComponentDTO createComponent(ComponentDTO dto)
	{
		Component component = new Component();
		component.setUserId(getCurrentUser());
		ComponentMapper.copyDTOtoDomain(dto, component);
        return ComponentMapper.domainToDTO(componentRepository.save(component));
	}

	public ComponentDTO updateComponent(Integer id, ComponentDTO dto)
	{
		Component component = componentRepository.findOne(id);
		ComponentMapper.copyDTOtoDomain(dto, component);
		return ComponentMapper.domainToDTO(componentRepository.save(component));
	}

	public void deleteComponentById(Integer id)
	{
		componentRepository.delete(id);
	}

    private Integer getCurrentUser()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getId();
    }
}
