package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.model.Component;
import com.dasbiersec.reloader.service.ReloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController
{
	@Autowired
	private ReloaderService reloaderService;

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

        return "addbatch";
    }

    @ModelAttribute("bullet")
    public Map<String, String> populateBullets()
    {
        Iterable<Component> components = reloaderService.getAllComponents();
        return getComponentByType(components, ComponentType.Bullet);
    }

    @ModelAttribute("brass")
    public Map<String, String> populateBrass()
    {
        Iterable<Component> components = reloaderService.getAllComponents();
        return getComponentByType(components, ComponentType.Brass);
    }


    @ModelAttribute("powder")
    public Map<String, String> populatePowder()
    {
        Iterable<Component> components = reloaderService.getAllComponents();
        return getComponentByType(components, ComponentType.Powder);
    }

    @ModelAttribute("primer")
    public Map<String, String> populatePrimer()
    {
        Iterable<Component> components = reloaderService.getAllComponents();
        return getComponentByType(components, ComponentType.Primer);
    }

    private Map<String, String> getComponentByType(Iterable<Component> components, ComponentType type)
    {
        Map<String, String> comps = new HashMap<String, String>();

        for (Component component : components)
        {
            if (component.getType() == type)
                comps.put(String.valueOf(component.getId()), component.getName());
        }

        return comps;
    }
}
