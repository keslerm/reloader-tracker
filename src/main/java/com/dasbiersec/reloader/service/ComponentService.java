package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.dto.component.ComponentDTO;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.domain.Component;
import com.dasbiersec.reloader.helper.SecurityHelper;
import com.dasbiersec.reloader.mapper.ComponentMapper;
import com.dasbiersec.reloader.repos.ComponentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class ComponentService
{
	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private ComponentRepository componentRepository;


    @PostFilter("hasRole('ROLE_ADMIN') || filterObject.userId == principal.id")
	public Iterable<Component> getAllComponents()
	{
		Iterable<Component> components = componentRepository.findAll();
		return components;
	}

    @PostFilter("hasRole('ROLE_ADMIN') || filterObject.userId == principal.id")
	public Iterable<Component> getComponentByType(ComponentType type)
	{
		Iterable<Component> components =  componentRepository.findComponentByType(type);
		return components;
	}

    @PostAuthorize("hasRole('ROLE_ADMIN') || returnObject == null || returnObject.userId == principal.id")
	public Component getComponentById(Integer id)
	{
        return componentRepository.findOne(id);
	}

    @PostAuthorize("hasRole('ROLE_ADMIN') || returnObject == null || returnObject.userId == principal.id")
	public Component createComponent(ComponentDTO dto)
	{
		Component component = new Component();
		component.setUserId(SecurityHelper.getCurrentUserId());

		ComponentMapper.copyDTOtoDomain(dto, component);

        return componentRepository.save(component);
	}


    @PreAuthorize("hasRole('ROLE_ADMIN') || #existing.userId == principal.id")
	public Component updateComponent(Component existing, ComponentDTO dto)
	{
		ComponentMapper.copyDTOtoDomain(dto, existing);
		return componentRepository.save(existing);
	}

    @PreAuthorize("hasRole('ROLE_ADMIN') || #existing.userId == principal.id")
	public void deleteComponent(Component component)
	{
		componentRepository.delete(component);
	}
}
