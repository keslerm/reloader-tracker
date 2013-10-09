package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.entity.Recipe;
import com.dasbiersec.reloader.entity.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/")
@SessionAttributes(types = {Recipe.class, Component.class})
public class WebController
{
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(ModelMap map)
	{
		return "index";
	}
}
