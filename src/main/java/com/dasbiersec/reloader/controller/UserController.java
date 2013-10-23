package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.dto.user.RegisterDTO;
import com.dasbiersec.reloader.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "user/")
public class UserController
{
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public void register(@RequestBody RegisterDTO dto)
	{
		accountService.registerUser(dto);
	}
}
