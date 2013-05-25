package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.dao.CostPerRound;
import com.dasbiersec.reloader.dao.ReportingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequestMapping("/reporting")
public class ReportingController
{
	@Autowired
	private ReportingDAO reportingDAO;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Collection<CostPerRound> getCPR()
	{
		return reportingDAO.getCPR();
	}

}
