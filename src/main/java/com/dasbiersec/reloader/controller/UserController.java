package com.dasbiersec.reloader.controller;

import com.dasbiersec.reloader.dto.user.RegisterDTO;
import com.dasbiersec.reloader.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "users/")
public class UserController
{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public void register(@RequestBody RegisterDTO dto)
	{
		userService.registerUser(dto);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteUser(@RequestParam Integer id)
	{
		userService.deleteUser(id);
	}
}
