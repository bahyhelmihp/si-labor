package com.apap.silabor.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.apap.silabor.model.JadwalJagaModel;
import com.apap.silabor.rest.LabResponse;
import com.apap.silabor.rest.Setting;
import com.apap.silabor.rest.StaffTest;
import com.apap.silabor.service.JadwalJagaService;

@Controller
public class JadwalJagaController {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}

	@Autowired
	JadwalJagaService jadwalJagaService;

	// Fitur 11
	@RequestMapping(value = "lab/jadwal-jaga/tambah", method = RequestMethod.GET)
	private String addJadwalJaga(Model model) {
		// Consume API
		LabResponse response = restTemplate.getForObject(Setting.getAllStaffLabUrl, LabResponse.class);
		List<StaffTest> listOfStaff = response.getResult().getListOfStaff();
		model.addAttribute("listOfStaff", listOfStaff);
		model.addAttribute("jadwalJaga", new JadwalJagaModel());
		model.addAttribute("title", "Tambah Jadwal Jaga");
		return "jadwalJaga-add";
	}

	@RequestMapping(value = "lab/jadwal-jaga/tambah", method = RequestMethod.POST)
	private String addJadwalJagaSubmit(@ModelAttribute JadwalJagaModel jadwalJaga) {
		jadwalJagaService.addJadwal(jadwalJaga);
		return "jadwalJaga-add";
	}

	// Fitur 12
	@RequestMapping(value = "lab/jadwal-jaga/{tanggal}", method = RequestMethod.GET)
	private String getJadwalJaga(@PathVariable("tanggal") Date tanggal, Model model) {
		List<JadwalJagaModel> archive = jadwalJagaService.getJadwalByDate(tanggal);
		model.addAttribute("jadwalJaga", archive);
		model.addAttribute("title", "Lihat Jadwal Jaga");
		return "jadwalJaga-view";
	}

	// Fitur 13
	@RequestMapping(value = "lab/jadwal-jaga/ubah/{id}", method = RequestMethod.GET)
	private String updateJadwalJaga(@PathVariable(value = "id") long id, Model model) {
		JadwalJagaModel archive = jadwalJagaService.getJadwalById(id);
		model.addAttribute("jadwalJaga", archive);
		model.addAttribute("title", "Ubah Jadwal Jaga");
		return "jadwalJaga-update";
	}

	@RequestMapping(value = "lab/jadwal-jaga/ubah/{id}", method = RequestMethod.POST)
	private String updateLabSupplySubmit(@PathVariable(value = "id") long id, JadwalJagaModel jadwalJaga, Model model) {
		jadwalJaga.setId(id);
		jadwalJagaService.updateJadwal(jadwalJaga);
		model.addAttribute("jadwalJaga", jadwalJaga);
		model.addAttribute("title", "Ubah Jadwal Jaga");
		return "success";
	}
	
	@ResponseBody
	@GetMapping(value = "lab/jadwal-jaga/getJadwalJaga")
	public List<JadwalJagaModel> getJadwalJagaPemeriksaan(Model model) {
		return jadwalJagaService.getAllJadwaJaga();
	}

}
