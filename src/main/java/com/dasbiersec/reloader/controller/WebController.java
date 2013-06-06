package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.beans.BatchBean;
import com.dasbiersec.reloader.model.Batch;
import com.dasbiersec.reloader.service.ReloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class WebController
{

    @Autowired
    private ReloaderService service;

}
