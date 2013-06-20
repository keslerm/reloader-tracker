package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.controller.editor.ComponentEditor;
import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.service.ReloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/")
@SessionAttributes(types = {Batch.class, Component.class})
public class WebComponentController
{
	@Autowired
	private ReloaderService reloaderService;

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Component.class, new ComponentEditor());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/components")
	public String components(ModelMap modelMap)
	{
		Iterable<Component> components = reloaderService.getAllComponents();
		modelMap.addAttribute("components", components);
		return "components";
	}

	@RequestMapping(method = RequestMethod.GET, value = "components/{id}/edit")
	public String editComponent(ModelMap map, @PathVariable(value = "id") Integer id)
	{
		Component component = reloaderService.getComponentById(id);
		map.addAttribute("component", component);

		return "componentform";
	}

	@RequestMapping(method = RequestMethod.GET, value = "components/{id}/details")
	public String componentDetails(ModelMap map, @PathVariable(value = "id") Integer id)
	{
		Component component = reloaderService.getComponentById(id);
		map.addAttribute("component", component);

		return "componentdetails";
	}

	@RequestMapping(method = RequestMethod.POST, value = "components/save")
	public String saveComponent(@ModelAttribute(value = "component") Component component, BindingResult result, SessionStatus status, ModelMap map)
	{
		reloaderService.saveComponent(component);
		map.addAttribute("component", component);
		return "componentform";
	}

	@ModelAttribute("componentTypes")
	public List<ComponentType> componentList()
	{
		return Arrays.asList(ComponentType.values());
	}


}
