package com.apap.silabor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.silabor.model.UserRoleModel;
import com.apap.silabor.service.UserRoleService;

@Controller
public class UserRoleController {
	@Autowired
	private UserRoleService userService;

	@RequestMapping(value = "/register/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user, Model model) {
		System.out.println("Berhasil");
		userService.addUser(user);
		return "login";
	}
}