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
public class WebController
{

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
		return "redirect:/batches/";
	}


}
