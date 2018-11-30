package com.apap.silabor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.service.JenisPemeriksaanService;
import com.apap.silabor.service.PemeriksaanService;

@Controller
public class PageController {
	
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
	@Autowired
	private JenisPemeriksaanService jenisPemeriksaanService;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
}