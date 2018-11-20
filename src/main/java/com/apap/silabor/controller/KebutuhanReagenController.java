package com.apap.silabor.controller;

import com.apap.silabor.model.KebutuhanReagenModel;
import com.apap.silabor.service.KebutuhanReagenService;
import com.apap.silabor.model.SupplyModel;
import com.apap.silabor.service.SupplyService;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * KebutuhanReagenModel
 */
@Controller
public class KebutuhanReagenController {
	@Autowired
	private KebutuhanReagenService kebutuhanReagenService;
	
	@Autowired
	private SupplyService supplyService;
	
	//FITUR 3 : Membuat perencanaan kebutuhan reagen
	@RequestMapping(value = "/lab/kebutuhan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		KebutuhanReagenModel kebutuhan = new KebutuhanReagenModel();
		
		//gak masuk
		LocalDate date = LocalDate.now();
		Date tanggal =  Date.valueOf(date);
		kebutuhan.setTanggal_update(tanggal);
		
		//id masih sementara
		SupplyModel supply = supplyService.getSupplyById(1);
		kebutuhan.setSupply(supply);
		
		model.addAttribute("kebutuhan", kebutuhan);
		return "kebutuhan-add";
	}
	
	@RequestMapping(value = "/lab/kebutuhan/tambah", method = RequestMethod.POST)
	private String addKebutuhanSubmit(@ModelAttribute KebutuhanReagenModel kebutuhan) {
		kebutuhanReagenService.addKebutuhan(kebutuhan);
		return "success";
	}
}
