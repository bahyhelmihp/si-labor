package com.apap.silabor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.rest.LabResultDetail;
import com.apap.silabor.rest.Setting;
import com.apap.silabor.service.PemeriksaanService;

@RestController
@RequestMapping("/lab/pemeriksaan")
public class PemeriksaanController {
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
    public RestTemplate rest() {
    	return new RestTemplate();
    }
	
	@Autowired
	private PemeriksaanService pemeriksaanService;
	
	//FITUR 7 Menampilkan permintaan pemeriksaan lab
	@GetMapping(value = "/permintaan")
	public String viewAllPemeriksaan(Model model) {
		List<PemeriksaanModel> listPemeriksaan = pemeriksaanService.getListPemeriksaan();
		model.addAttribute("pemeriksaanList", listPemeriksaan);
		model.addAttribute("title", "Daftar Pemeriksaan Lab");
		return "pemeriksaan-viewall";
	}
	
	//FITUR 10
	@PostMapping(value = "/permintaan/tambah")
	public LabResultDetail addPemeriksaan(@RequestBody PemeriksaanModel pemeriksaan) {
		if (pemeriksaan.getId() == 1) {
			LabResultDetail detail = restTemplate.postForObject(Setting.addLabResultUrl, pemeriksaan, LabResultDetail.class);
			return detail;
		}
		return null;
	}

	
}
