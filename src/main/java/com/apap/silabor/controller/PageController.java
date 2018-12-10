package com.apap.silabor.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority: authentication.getAuthorities()) {
			model.addAttribute("role", authority.getAuthority());
		}
		return "home";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/register")
	public String register(Model model) {
		return "register";
	}
	
	@RequestMapping("/htmlTest")
	public String test(Model model) {
		return "test";
	}

}