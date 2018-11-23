package com.apap.silabor.controller;

import com.apap.silabor.model.KebutuhanReagenModel;
import com.apap.silabor.service.KebutuhanReagenService;
import com.apap.silabor.model.SupplyModel;
import com.apap.silabor.service.SupplyService;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		KebutuhanReagenModel reagen = new KebutuhanReagenModel();
		
		//input tanggal otomatis
		LocalDate date = LocalDate.now();
		Date tanggal =  Date.valueOf(date);
		model.addAttribute("tanggal", tanggal);
		
		//id masih sementara
		SupplyModel supply = supplyService.getSupplyById(1);
		reagen.setSupply(supply);
		
		model.addAttribute("reagen", reagen);
		return "reagen-add";
	}
	
	@RequestMapping(value = "/lab/kebutuhan/tambah", method = RequestMethod.POST)
	private String addKebutuhanSubmit(@ModelAttribute KebutuhanReagenModel reagen) {
		kebutuhanReagenService.addReagen(reagen);
		return "success";
	}
	
	//FITUR 4 : Melihat perencanaan kebutuhan reagen
	@RequestMapping(value = "/lab/kebutuhan", method = RequestMethod.GET)
	private String viewAll(Model model) {
		List<KebutuhanReagenModel> reagen = kebutuhanReagenService.getListReagen();
		
		model.addAttribute("datareagen", reagen);
		return "reagen-viewall";
	}
	
	//FITUR 6 : Mengubah data perencanaan kebutuhan reagen
	@RequestMapping(value="/lab/kebutuhan/ubah/{id}", method = RequestMethod.GET)
	private String updateReagen(@PathVariable(value="id") long id, Model model){
		KebutuhanReagenModel reagen = kebutuhanReagenService.getReagenById(id);
		model.addAttribute("datareagen", reagen);
		return "reagen-update";
	}
	
	@RequestMapping(value="lab/kebutuhan/ubah/{id}", method = RequestMethod.POST)
	private String updateReagenSubmit(@PathVariable(value="id") long id, KebutuhanReagenModel reagen, Model model) {
				
		LocalDate date = LocalDate.now();
		Date tanggal =  Date.valueOf(date);
		reagen.setTanggal_update(tanggal);
		
		SupplyModel supply = supplyService.getSupplyById(id);
		reagen.setSupply(supply);
		
		kebutuhanReagenService.updateReagen(reagen);
		model.addAttribute("datareagen", reagen);
		return "success";
	}
}
