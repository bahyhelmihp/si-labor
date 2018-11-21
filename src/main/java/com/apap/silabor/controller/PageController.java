package com.apap.silabor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.service.PemeriksaanService;

@Controller
public class PageController {
	
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/lab/pemeriksaan")
	public String viewAllPemeriksaan(Model model) {
		List<PemeriksaanModel> listPemeriksaan = pemeriksaanService.getListPemeriksaan();
			model.addAttribute("pemeriksaanList", listPemeriksaan);
			model.addAttribute("title", "Daftar Pemeriksaan Lab");
		return "pemeriksaan-viewall";
	}
}