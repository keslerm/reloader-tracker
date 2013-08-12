package com.dasbiersec.reloader.service;

import com.dasbiersec.reloader.auth.AccountDetails;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.domain.Component;
import com.dasbiersec.reloader.repos.ComponentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ComponentService
{
	private Logger log = Logger.getLogger(getClass());

	@Autowired
	private ComponentRepository componentRepository;

	public Iterable<Component> getAllComponents()
	{
		Iterable<Component> components = componentRepository.findAll();
		return components;
	}

	public Iterable<Component> getComponentByType(ComponentType type)
	{
		return componentRepository.findComponentByType(type);
	}

	public Component getComponentById(Integer id)
	{
		Component component = componentRepository.findOne(id);
		return component;
	}

	public Component saveComponent(Component component)
	{
        componentRepository.save(component);
        return component;
	}

	public void deleteComponentById(Integer id)
	{
		componentRepository.delete(id);
	}

	public Iterable<Component> findComponentByType(ComponentType type)
	{
		Iterable<Component> components = componentRepository.findComponentByType(type);
		return components;
	}

    private Integer getCurrentUser()
    {
        AccountDetails accountDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountDetails.getId();
    }
}
