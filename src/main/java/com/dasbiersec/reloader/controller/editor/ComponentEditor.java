package com.dasbiersec.reloader.controller.editor;

import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.repos.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class ComponentEditor extends PropertyEditorSupport
{
	@Autowired
	private ComponentRepository componentRepository;

	@Override
	public void setAsText(String text)
	{
		Component component = componentRepository.findOne(Integer.parseInt(text));
		setValue(component);
	}

}
