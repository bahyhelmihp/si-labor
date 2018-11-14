package com.apap.silabor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.silabor.model.SupplyModel;
import com.apap.silabor.service.SupplyService;

@Controller
public class SupplyController {
	@Autowired
	private SupplyService supplyService;
	
	//FITUR 14 Membuat data persediaan lab
	@RequestMapping(value = "/lab/stok/tambah", method = RequestMethod.GET)
	private String addLabSupply(Model model) {
		model.addAttribute("supply", new SupplyModel());
		model.addAttribute("title", "Tambah Persediaan Lab");
		return "supply-add";
	}
	
	@RequestMapping(value= "/lab/stok/tambah", method = RequestMethod.POST)
	private String addLabSupplySubmit(SupplyModel supply, Model model) {
		SupplyModel add = supplyService.addSupply(supply);
		model.addAttribute("supply", add);
		model.addAttribute("title", "Persediaan Lab");
		return "success";
	}
	
	//FITUR 15 Melihat data persediaan lab
	@RequestMapping(value="/lab/stok", method = RequestMethod.GET)
	private String viewAllLabSupply(Model model) {
		List<SupplyModel> supply = supplyService.getListSupply();
		//if(supply.isEmpty()) {
		//	return "supply-empty";
		//}
		model.addAttribute("datasupply", supply);
		model.addAttribute("title", "Daftar Persediaan Lab");	
		return "supply-viewall";
	}
	
	//FITUR16 Mengubah data persediaan lab
	//Initial Page: /lab/stok
	//Form Request: POST, /lab / stok/ubah/{id}
	//Response Page: /lab/stok
	//Role: Admin Laboratorium
	//Poin: 5
	//hanyaJumlah yang bisa diubah
	@RequestMapping(value="lab/stok/ubah/{id}", method = RequestMethod.GET)
	private String updateLabSupply(@PathVariable(value="id") long id, Model model) {
		SupplyModel supply = supplyService.getSupplyById(id);
		model.addAttribute("datasupply", supply);
		model.addAttribute("title","Ubah Data Reagen");
		return "supply-update";
	}
	
	@RequestMapping(value="lab/stok/ubah/{id}", method = RequestMethod.POST)
	private String updateLabSupplySubmit(@PathVariable(value="id") long id, SupplyModel supply, Model model) {
		supply.setId(id);
		supplyService.updateSupply(supply);
		model.addAttribute("datasupply", supply);
		model.addAttribute("title","Ubah Persediaan Reagen");
		return "success";
	}
	
}
