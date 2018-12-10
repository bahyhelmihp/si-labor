package com.apap.silabor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.silabor.model.SupplyModel;
import com.apap.silabor.service.JenisPemeriksaanService;
import com.apap.silabor.service.SupplyService;

@Controller
public class SupplyController {
	@Autowired
	private SupplyService supplyService;
	
	@Autowired
	private JenisPemeriksaanService jenisPemeriksaanService;
	
	//FITUR 14 Membuat data persediaan lab
	//adminonly
	@RequestMapping(value = "/lab/stok/tambah", method = RequestMethod.GET)
	private String addLabSupply(Model model) {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority: authentication.getAuthorities()) {
		   if (authority.getAuthority().equals("Admin")) {
			   model.addAttribute("suppliesByJenisPemeriksaan", ((JenisPemeriksaanService)jenisPemeriksaanService).getJenisPemeriksaanDb().findAll());
			   model.addAttribute("supply", new SupplyModel());
			   model.addAttribute("title", "Tambah Persediaan Lab");
			   return "supply-add";
		   }
		} 
		return "not-admin";
	}
	
	//adminonly
	
	@RequestMapping(value= "/lab/stok/tambah", method = RequestMethod.POST)
	private String addLabSupplySubmit(SupplyModel supply, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority: authentication.getAuthorities()) {
		   if (authority.getAuthority().equals("Admin")) {
			   SupplyModel add = supplyService.addSupply(supply);
				model.addAttribute("supply", add);
				model.addAttribute("title", "Persediaan Lab");
				return "success";
		   }
		}
		return "not-admin";
	}
	
	//FITUR 15 Melihat data persediaan lab
	@RequestMapping(value="/lab/stok", method = RequestMethod.GET)
	//if admin throw to supply-viewall
	private String viewAllLabSupply(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority: authentication.getAuthorities()) {
		   if (authority.getAuthority().equals("Admin")) {
			   List<SupplyModel> supply = supplyService.getListSupply();
			   model.addAttribute("datasupply", supply);
			   model.addAttribute("title", "Daftar Persediaan Lab");	
			   return "supply-viewall";
		   } else if (authority.getAuthority().equals("Staf")) {
			   List<SupplyModel> supply = supplyService.getListSupply();
			   model.addAttribute("datasupply", supply);
			   model.addAttribute("title", "Daftar Persediaan Lab");	
			   return "supply-viewall-staf";
		   }
		
		}
		return "500";
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority: authentication.getAuthorities()) {
		   if (authority.getAuthority().equals("Admin")) {
			   	SupplyModel supply = supplyService.getSupplyById(id);
				model.addAttribute("datasupply", supply);
				model.addAttribute("title","Ubah Data Reagen");
				return "supply-update";
		   }
		}
		return "not-admin";
	}
	
	//adminonly
	@RequestMapping(value="lab/stok/ubah/{id}", method = RequestMethod.POST)
	private String updateLabSupplySubmit(@PathVariable(value="id") long id, SupplyModel supply, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority: authentication.getAuthorities()) {
		   if (authority.getAuthority().equals("Admin")) {
			   	supply.setId(id);
				supplyService.updateSupply(supply);
				model.addAttribute("datasupply", supply);
				model.addAttribute("title","Ubah Persediaan Reagen");
				return "success";
		   }
		}
		return "not-admin";
	}
	
}
