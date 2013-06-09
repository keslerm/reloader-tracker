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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class WebController
{
	@Autowired
	private ReloaderService reloaderService;

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Component.class, new ComponentEditor());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(ModelMap map)
	{
		return "index";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/batches")
	public String batches(ModelMap modelMap)
	{
		Iterable<Batch> batches = reloaderService.getAllBatches();
		modelMap.addAttribute("batches", batches);
		return "batches";
	}

    @RequestMapping(method = RequestMethod.GET, value = "/components")
    public String components(ModelMap modelMap)
    {
        Iterable<Component> components = reloaderService.getAllComponents();
        modelMap.addAttribute("components", components);
        return "components";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/batches/add")
    public String addBatch(ModelMap map)
    {
        Batch batch = new Batch();
        map.addAttribute("batch", batch);

        return "batchform";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/batches/{id}/edit")
    public String editBatch(ModelMap map, @PathVariable(value = "id") Integer id)
    {
        Batch batch = reloaderService.getBatchById(id);
        map.addAttribute("batch", batch);

        return "batchform";
    }

	@RequestMapping(method = RequestMethod.POST, value = "/batches/{id}/edit")
	public String saveBatch(@ModelAttribute(value = "batch") Batch batch, BindingResult result, SessionStatus status, ModelMap map)
	{
		reloaderService.saveBatch(batch);

		map.addAttribute("batch", batch);

		return "batchform";
	}

    @ModelAttribute("bullet")
    public List<Component> populateBullets()
    {
        Iterable<Component> components = reloaderService.getAllComponents();
        return getComponentByType(components, ComponentType.Bullet);
    }

    @ModelAttribute("brass")
    public List<Component> populateBrass()
    {
        Iterable<Component> components = reloaderService.getAllComponents();
        return getComponentByType(components, ComponentType.Brass);
    }


    @ModelAttribute("powder")
    public List<Component> populatePowder()
    {
        Iterable<Component> components = reloaderService.getAllComponents();
        return getComponentByType(components, ComponentType.Powder);
    }

    @ModelAttribute("primer")
    public List<Component> populatePrimer()
    {
        Iterable<Component> components = reloaderService.getAllComponents();
        return getComponentByType(components, ComponentType.Primer);
    }

    private List<Component> getComponentByType(Iterable<Component> components, ComponentType type)
    {
	    List<Component> comps = new ArrayList<Component>();

        for (Component component : components)
        {
            if (component.getType() == type)
                comps.add(component);
        }

        return comps;
    }
}
