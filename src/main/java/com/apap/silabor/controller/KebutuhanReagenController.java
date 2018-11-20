package com.apap.silabor.controller;

import com.apap.silabor.model.KebutuhanReagenModel;
import com.apap.silabor.service.KebutuhanReagenService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * KebutuhanReagenModel
 */
@Controller
public class KebutuhanReagenController {
	@Autowired
	private KebutuhanReagenService kebutuhanReagenService;
	
	@RequestMapping(value = "/lab/kebutuhan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("kebutuhan", new KebutuhanReagenModel());
		return "kebutuhan-add";
	}
	
	@RequestMapping(value = "/lab/kebutuhan/tambah", method = RequestMethod.POST)
	private String addKebutuhanSubmit(@ModelAttribute KebutuhanReagenModel kebutuhan) {
		kebutuhanReagenService.addKebutuhan(kebutuhan);
		return "success";
	}
}
