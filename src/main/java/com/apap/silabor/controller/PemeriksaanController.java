package com.apap.silabor.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.rest.LabResponse;
import com.apap.silabor.rest.LabResult;
import com.apap.silabor.rest.PasienResponse;
import com.apap.silabor.rest.PasienTest;
import com.apap.silabor.rest.Setting;
import com.apap.silabor.service.JadwalJagaService;
import com.apap.silabor.service.PemeriksaanService;

@Controller
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

	@Autowired
	private JadwalJagaService jadwalJagaService;

	//FITUR 7 Menampilkan permintaan pemeriksaan lab
	@GetMapping(value = "/permintaan")
	public String viewAllPemeriksaan(Model model) throws IOException {
		
		// mengambil data dari {url}
		String path = "aa";
		List<Long> listOfIdPasien = new ArrayList<Long>();
		//listOfIdPasien = restTemplate.getForObject(path, List.class);
		
		listOfIdPasien.add((long) 1);
		listOfIdPasien.add((long) 2);
		
		List<PemeriksaanModel> listPemeriksaan = pemeriksaanService.getListPemeriksaan();
		//call object pasien
		String urlPasien = "";
		int sizeListPemeriksaan = listPemeriksaan.size();
		for(PemeriksaanModel pemeriksaan : listPemeriksaan) {
			urlPasien += pemeriksaan.getIdPasien();
			sizeListPemeriksaan --;
			if(sizeListPemeriksaan>0) {
				urlPasien += ",";
			}
		}
		String url = "http://si-appointment.herokuapp.com/api/getPasien?listId="+urlPasien+"&resultType=List";
		System.out.println(url);
		PasienResponse response = restTemplate.getForObject(url, PasienResponse.class)	;
		System.out.println(response.getResult().get(0).getNama());
		List<PasienTest> listPasien = new ArrayList<>();
		listPasien = response.getResult();
		System.out.println(listPasien.get(0));
//		System.out.println("<<<<<<<<>>>>>>>>");
//		System.out.println(listPasien);
//
//		System.out.println("<<<<<<<<>>>>>>>>");
//		System.out.println(listPasien.keySet());
//		
//
//		System.out.println("<<<<<<<<>>>>>>>>");
//		System.out.println(listPasien.get("5"));
//
//		System.out.println("<<<<<<<<>>>>>>>>");
//		System.out.println(listPasien.values());
		model.addAttribute("pemeriksaanList", listPemeriksaan);
		model.addAttribute("pasienList", listPasien);
		model.addAttribute("title", "Daftar Pemeriksaan Lab");
		return "pemeriksaan-viewall";
	}
	//FITUR 8
	@PostMapping(value = "/permintaan/save")
	public PemeriksaanModel addPemeriksaan(@RequestBody PemeriksaanModel pemeriksaan) {
		return pemeriksaanService.addPemeriksaan(pemeriksaan);
	}

	//FITUR 9
	@PostMapping(value = "/{id}")
	public String updateStatus(@PathVariable(value="id") long id, Model model) {
		PemeriksaanModel pemeriksaan = pemeriksaanService.getPemeriksaanById(id);
		//Menunggu -> Diproses
		if (pemeriksaan.getStatus() == 0) {
			for (JenisPemeriksaanLabSuppliesModel pemeriksaan_supply : pemeriksaan.getJenisPemeriksaan().getListJenisPemeriksaanLabSupplies()) {
				//Lab Supllies Ada
				if (pemeriksaan_supply.getId() == pemeriksaan.getId() && pemeriksaan_supply.getLabSupplies().getJumlah() != 0) {
					//Kurangi Supply
					pemeriksaan_supply.getLabSupplies().setJumlah(pemeriksaan_supply.getLabSupplies().getJumlah() - 1);
					//Set Tanggal Pemeriksaan
					Calendar today = Calendar.getInstance();
					today.set(Calendar.HOUR_OF_DAY, 0);
					pemeriksaan.setTanggalPemeriksaan((Date) today.getTime());
					//Set Jadwal Jaga
					pemeriksaan.setJadwalJaga(jadwalJagaService.getJadwalByDate(pemeriksaan.getTanggalPemeriksaan()).get(0));
					//Set Diproses
					pemeriksaan.setStatus(1);
					//Sukses
					pemeriksaanService.addPemeriksaan(pemeriksaan);
					return "sukses-diproses";
				}
			}
			return "gagal";
		}
		//Diproses -> Selesai
		else {
			PemeriksaanModel pemeriksaanDiproses = pemeriksaanService.getPemeriksaanById(id);
			model.addAttribute("pemeriksaan", pemeriksaanDiproses);
			return "update-hasil";
		}
	}
	//Sukses Update Diproses -> Selesai
	@PostMapping(value = "/permintaan/update/sukses")
	public String updatePemeriksaan(PemeriksaanModel pemeriksaan) {
		pemeriksaanService.addPemeriksaan(pemeriksaan);
		return "redirect:/lab/pemeriksaan/permintaan";
	}

	//FITUR 10
	//Sementara GetMapping Dulu
	@GetMapping(value = "/permintaan/send/{id}")
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
		System.out.println(response.getMessage());
		
		//Return 
		return "redirect:/lab/pemeriksaan/permintaan";
	}
}
