package com.apap.silabor.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.model.SupplyModel;
import com.apap.silabor.rest.BaseResponse;
import com.apap.silabor.rest.KamarPasienIsi;
import com.apap.silabor.rest.KamarPasienIsiResponse;
import com.apap.silabor.rest.LabResponse;
import com.apap.silabor.rest.LabResult;
import com.apap.silabor.rest.PasienResponse;
import com.apap.silabor.rest.PasienTest;
import com.apap.silabor.rest.Setting;
import com.apap.silabor.service.JadwalJagaService;
import com.apap.silabor.service.JenisPemeriksaanService;
import com.apap.silabor.service.PemeriksaanService;
import com.apap.silabor.service.SupplyService;

@Controller
//@RequestMapping("/lab/pemeriksaan")
public class PemeriksaanController {
//	@Autowired
//	RestTemplate restTemplate;
//
//	@Bean
//	public RestTemplate restPemeriksaan() {
//		return new RestTemplate();
//	}
	
	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private SupplyService supplyService;

	@Autowired
	private PemeriksaanService pemeriksaanService;

	@Autowired
	private JenisPemeriksaanService jenisPemeriksaanService;


	@Autowired
	private JadwalJagaService jadwalJagaService;

//	//FITUR 7 testing
//	@GetMapping(value = "/testAmbilKamar")
//	public String testPemeriksaan(Model model) throws IOException {
//
//		String path = "https://ta-5-1.herokuapp.com/api/kamars?isFilled=true";
//		KamarPasienIsiResponse response = restTemplate.getForObject(path, KamarPasienIsiResponse.class)	;
//		List<KamarPasienIsi>  listKamar = new ArrayList<>();
//		List<Long> listIdPasienRawatInapBaru = new ArrayList<>();
//
//		listKamar = response.getResult();
//		for(KamarPasienIsi kamar : listKamar) {
//			if(pemeriksaanService.isExist(kamar.getId_pasien(), 1));
//		}
//		System.out.println(response.getResult());
//		return "home";
//	}

	//FITUR 7 Menampilkan permintaan pemeriksaan lab
	@GetMapping(value = "/lab/pemeriksaan/permintaan")
	public String viewAllPemeriksaan(Model model) throws IOException {
		//Menambahkan Otentikasi Admin
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority: authentication.getAuthorities()) {
			if (authority.getAuthority().equals("Admin")) {
				model.addAttribute("authenticated", authority.getAuthority());
			}
		}

		// mengambil data dari {url}
		String path = "https://ta-5-1.herokuapp.com/api/kamars?isFilled=true";
		KamarPasienIsiResponse response = restTemplate.getForObject(path, KamarPasienIsiResponse.class)	;
		List<KamarPasienIsi>  listKamar = new ArrayList<>();
		listKamar = response.getResult();
		//listOfIdPasien = restTemplate.getForObject(path, List.class);
		
		if(!listKamar.isEmpty()) {
			for(KamarPasienIsi kamar : listKamar) {
				long idPasienBaru = kamar.getId_pasien();
				if(!pemeriksaanService.isExist(idPasienBaru, 1)) {
					PemeriksaanModel pemeriksaanBaru =new PemeriksaanModel(); 
					pemeriksaanBaru.setIdPasien(idPasienBaru);
					pemeriksaanBaru.setStatus(0);
					Calendar currentTime = Calendar.getInstance();
					Date sqlDate = new Date((currentTime.getTime()).getTime());
					pemeriksaanBaru.setTanggalPengajuan(sqlDate);
					pemeriksaanBaru.setJenisPemeriksaan(jenisPemeriksaanService.getJenisPemeriksaanById(1));
					pemeriksaanService.addPemeriksaan(pemeriksaanBaru);
				}
			}
		}
		

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
		String url = "http://si-appointment.herokuapp.com/api/getPasien?listId="+urlPasien+"&resultType=Map";
		PasienResponse response2 = restTemplate.getForObject(url, PasienResponse.class)	;
		Map<String,PasienTest> listPasien = new HashMap<>();
		listPasien = response2.getResult();
		model.addAttribute("pemeriksaanList", listPemeriksaan);
		model.addAttribute("pasienList", listPasien);
		model.addAttribute("title", "Daftar Pemeriksaan Lab");
		return "pemeriksaan-viewall";
	}
	//FITUR 8
	@ResponseBody
    @PostMapping(value = "/api/lab/pemeriksaan/permintaan")
    public BaseResponse<PemeriksaanModel> addLabResult(@RequestBody @Valid PemeriksaanModel pemeriksaan, BindingResult bindingResult) {
        BaseResponse<PemeriksaanModel> response = new BaseResponse<PemeriksaanModel>();
        if (bindingResult.hasErrors()) {
            response.setStatus(500);
            response.setMessage("error data");
        }else {
        	pemeriksaanService.addPemeriksaan(pemeriksaan);
            response.setStatus(200);
            response.setMessage("success");
            response.setResult(pemeriksaan);

        }
              return response;
    }

	//FITUR 9
	@PostMapping(value = "/lab/pemeriksaan/{id}")
	public String updateStatus(@PathVariable(value="id") long id, Model model) {
		PemeriksaanModel pemeriksaan = pemeriksaanService.getPemeriksaanById(id);
		List<SupplyModel> supplyChoosen = new ArrayList<>();
		//Menunggu -> Diproses
		if (pemeriksaan.getStatus() == 0) {
			int error = 0;

			//Iterasi Terpenuhi
			for (SupplyModel supply : pemeriksaan.getJenisPemeriksaan().getSupplyList()) {
				//Lab Supllies Ada
				if (supply.getJumlah() != 0) {
					supplyChoosen.add(supply);
				}
				//Lab Supplies Tidak Lengkap
				else {
					error += 1;
				}
			}
			
			//Cek Jadwal Ada
			Calendar currentTime = Calendar.getInstance();
			Date sqlDate = new Date((currentTime.getTime()).getTime());
			if (jadwalJagaService.getJadwalByDate(sqlDate).isEmpty()) {
				System.out.println("Jadwal Jaga Kosong");
				error += 1;
			}

			//Syarat Terpenuhi
			System.out.println("Error: " + error);
			if (error == 0) {
				for (SupplyModel supply : supplyChoosen) {
					supply.setJumlah(supply.getJumlah() - 1);
				}

				//Set Tanggal Pemeriksaan
				pemeriksaan.setTanggalPemeriksaan(sqlDate);
				//Set Jadwal Jaga
				pemeriksaan.setJadwalJaga(jadwalJagaService.getJadwalByDate(pemeriksaan.getTanggalPemeriksaan()).get(0));
				//Set Diproses
				pemeriksaan.setStatus(1);
				pemeriksaanService.addPemeriksaan(pemeriksaan);
				return "sukses-diproses";
			}
			//Gagal
			else {
				return "gagal";
			}
		}
		//Diproses -> Selesai
		else {
			//Get Pemeriksaan Diproses
			PemeriksaanModel pemeriksaanDiproses = pemeriksaanService.getPemeriksaanById(id);
			model.addAttribute("pemeriksaan", pemeriksaanDiproses);
			return "update-hasil";
		}
	}
	//Sukses Update Diproses -> Selesai
	@PostMapping(value = "/permintaan/update/sukses")
	public String updatePemeriksaan(PemeriksaanModel pemeriksaan) {

		//Set Selesai
		pemeriksaan.setStatus(2);
		pemeriksaanService.addPemeriksaan(pemeriksaan);
		return "sukses-selesai";
	}

	//FITUR 10
	@PostMapping(value = "/lab/pemeriksaan/kirim/{id}")
	public String kirimPemeriksaan(@PathVariable(value="id") long id) {
		
		//Get Pemeriksaan Selesai
		PemeriksaanModel pemeriksaan = pemeriksaanService.getPemeriksaanById(id);

		//Object Sementara
		LabResult labResult = new LabResult();
		PasienTest pasien = new PasienTest();
		labResult.setHasil(pemeriksaan.getHasil());
		labResult.setJenis(pemeriksaan.getJenisPemeriksaan().getNama());
		labResult.setPasien(pasien);
		pasien.setId(pemeriksaan.getIdPasien());
		labResult.setTanggalPengajuan(pemeriksaan.getTanggalPengajuan());

		//Consume API
		LabResponse response = restTemplate.postForObject(Setting.addLabResultUrl, labResult, LabResponse.class);

		//Set Selesai
		if (response.getMessage().equals("success")) {
			pemeriksaan.setStatus(3);
			pemeriksaanService.addPemeriksaan(pemeriksaan);
		}

		//Return 
		return "sukses-kirim";
	}
}
