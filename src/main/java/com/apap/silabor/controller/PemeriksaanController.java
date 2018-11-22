package com.apap.silabor.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.rest.LabResponse;
import com.apap.silabor.rest.LabResult;
import com.apap.silabor.rest.PasienTest;
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

	//FITUR 8
	@PostMapping(value = "/permintaan/save")
	public PemeriksaanModel addPemeriksaan(@RequestBody PemeriksaanModel pemeriksaan) {
		return pemeriksaanService.addPemeriksaan(pemeriksaan);
	}

	//FITUR 9
	@GetMapping(value = "/{id}")
	public long updateStatus(@PathVariable(value="id") long id) {
		PemeriksaanModel pemeriksaan = pemeriksaanService.getPemeriksaanById(id);
		return pemeriksaan.getId();
	}

	//FITUR 10
	@GetMapping(value = "/permintaan/send")
	public String sendPemeriksaan() {
		
		//Object Sementara
		LabResult labResult = new LabResult();
		PasienTest pasien = new PasienTest();
		labResult.setHasil("diabetes");
		labResult.setJenis("urin");
		labResult.setPasien(pasien);
		pasien.setId(1);
		labResult.setTanggalPengajuan(Date.valueOf("2018-11-21"));
		
		//Consume API
		LabResponse response = restTemplate.postForObject(Setting.addLabResultUrl, labResult, LabResponse.class);
		
		//Return Response
		return response.getMessage();
	}

	@GetMapping(value = "/tompel")
	public ResponseEntity<String> getPasien() {
		String path = "http://si-appointment.herokuapp.com/api/6/getAllPasienRawatJalan";
		ResponseEntity<String> response = restTemplate.getForEntity(path, String.class);
		return response;
	}

}
