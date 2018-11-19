package com.apap.silabor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.silabor.model.JadwalJagaModel;
import com.apap.silabor.service.JadwalJagaService;

@Controller
public class JadwalJagaController {
	
	@Autowired
	JadwalJagaService jadwalJagaService;
	
	@RequestMapping(value = "/jadwaljaga/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("jadwalJaga", new JadwalJagaModel());
		return "add-pilot";
	}

	@RequestMapping(value = "/jadwaljaga/add", method = RequestMethod.POST)
	private String addJadwalSubmit(@ModelAttribute JadwalJagaModel jadwalJaga) {
		jadwalJagaService.addJadwal(jadwalJaga);
		return "add";
	}

}
