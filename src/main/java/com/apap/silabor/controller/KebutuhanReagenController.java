package com.apap.silabor.controller;

import com.apap.silabor.model.KebutuhanReagenModel;
import com.apap.silabor.service.KebutuhanReagenService;
import com.apap.silabor.model.SupplyModel;
import com.apap.silabor.service.SupplyService;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restReagen() {
		return new RestTemplate();
	}
	
	//FITUR 3 : Membuat perencanaan kebutuhan reagen
	@RequestMapping(value = "/lab/kebutuhan/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		KebutuhanReagenModel reagen = new KebutuhanReagenModel();
		
		//input tanggal otomatis
		LocalDate date = LocalDate.now();
		Date tanggal =  Date.valueOf(date);
		model.addAttribute("tanggal", tanggal);
		
		//mengambil list supply dengan jenis reagen
		List<SupplyModel> listSupply = supplyService.getListSupply();
		List result = new ArrayList<>();
				
		for(int i = 0; i<listSupply.size(); i++) {
			if(listSupply.get(i).getJenis().equalsIgnoreCase("reagen")) {
				result.add(listSupply.get(i));
			}
		}
		model.addAttribute("listsupply", result);
		
		model.addAttribute("reagen", reagen);
		return "reagen-add";
	}
	
	@RequestMapping(value = "/lab/kebutuhan/tambah", method = RequestMethod.POST)
	private String addKebutuhanSubmit(@ModelAttribute KebutuhanReagenModel reagen) {
		SupplyModel supply = reagen.getSupply();
		reagen.setNama(supply.getNama());
		kebutuhanReagenService.addReagen(reagen);
		return "success";
	}
	
	
	//FITUR 4 : Melihat perencanaan kebutuhan reagen
	@GetMapping(value="/lab/kebutuhan")
	private String viewAllReagen(Model model) {
		List<KebutuhanReagenModel> reagen = kebutuhanReagenService.getListReagen();
		
		//cek tabel
		boolean value = true;
		if(reagen.size()==0) {
			value = false;
		} else value = true;
		
		model.addAttribute("value", value);
		model.addAttribute("datareagen", reagen);
		return "reagen-viewall";
	}
	
	//FITUR 5 : Web Service untuk mengembalikan data perencanaan kebutuhan reagen
	@ResponseBody
	@RequestMapping(value = "/lab/kebutuhan/perencanaan", method = RequestMethod.GET)
	private List<KebutuhanReagenModel> viewAll() {
		//List<KebutuhanReagenModel> reagen = ;
		return kebutuhanReagenService.getListReagen();
	}
	
	//FITUR 6 : Mengubah data perencanaan kebutuhan reagen
	@RequestMapping(value="/lab/kebutuhan/ubah/{id}", method = RequestMethod.GET)
	private String updateReagen(@PathVariable(value="id") long id, Model model){
		KebutuhanReagenModel reagen = kebutuhanReagenService.getReagenById(id);
		
		//mengubah nama pada title sesuai reagen
		String nama = reagen.getNama();
		model.addAttribute("nama", nama);
		
		model.addAttribute("datareagen", reagen);
		return "reagen-update";
	}
	
	@RequestMapping(value="lab/kebutuhan/ubah/{id}", method = RequestMethod.POST)
	private String updateReagenSubmit(@PathVariable(value="id") long id, KebutuhanReagenModel reagen, Model model) {
		//input tanggal ketika diubah	
		LocalDate date = LocalDate.now();
		Date tanggal =  Date.valueOf(date);
		reagen.setTanggal_update(tanggal);
		
		//integrasi dengan supply bersangkutan
		SupplyModel supply = supplyService.getSupplyById(id);
		reagen.setSupply(supply);
		
		kebutuhanReagenService.updateReagen(reagen);
		model.addAttribute("datareagen", reagen);
		
		
		return "success";
	}
}
