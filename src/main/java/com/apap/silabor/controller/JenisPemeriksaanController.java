package com.apap.silabor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.silabor.model.JadwalJagaModel;
import com.apap.silabor.model.JenisPemeriksaanModel;
import com.apap.silabor.service.JenisPemeriksaanService;

@RestController
public class JenisPemeriksaanController {

//	@Autowired
//	RestTemplate restTemplate;

//	@Bean
//	public RestTemplate restJenisPemeriksaan() {
//		return new RestTemplate();
//	}
	
	
	@Autowired 
	JenisPemeriksaanService jenisPemeriksaanService;
	
//	@ResponseBody
//	@GetMapping(value = "lab/jadwal-jaga/getJadwalJaga")
//	public List<JenisPemeriksaanModel> getJadwalJagaPemeriksaan(Model model) {
//		return jenisPemeriksaanService.getListJenisPemeriksaan();
//	}
//	
}
