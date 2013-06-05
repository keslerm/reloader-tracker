package com.dasbiersec.reloader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController
{

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test (ModelMap modelMap)
    {
        modelMap.addAttribute("message", "Test!");
        return "hello";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String bonus(ModelMap map)
    {
        map.addAttribute("message", "Test 2!");
        return "hello";
    }
}
