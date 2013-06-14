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

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(ModelMap map)
	{
		return "login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login/error")
	public String loginError(ModelMap map)
	{
		map.addAttribute("loginError", true);
		return "login";
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

    @ModelAttribute("allBullets")
    public Iterable<Component> populateBullets()
    {
        return reloaderService.findComponentByType(ComponentType.Bullet);
    }

    @ModelAttribute("allBrass")
    public Iterable<Component> populateBrass()
    {
	    return reloaderService.findComponentByType(ComponentType.Brass);
    }

    @ModelAttribute("allPowders")
    public Iterable<Component> populatePowder()
    {
	    return reloaderService.findComponentByType(ComponentType.Powder);
    }

    @ModelAttribute("allPrimers")
    public Iterable<Component> populatePrimer()
    {
	    return reloaderService.findComponentByType(ComponentType.Primer);
    }

}
