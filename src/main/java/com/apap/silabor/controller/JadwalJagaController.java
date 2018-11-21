package com.apap.silabor.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.silabor.model.JadwalJagaModel;
import com.apap.silabor.service.JadwalJagaService;

@Controller
public class JadwalJagaController {

	@Autowired
	JadwalJagaService jadwalJagaService;

	@RequestMapping(value = "lab/jadwal-jaga/tambah", method = RequestMethod.GET)
	private String addJadwalJaga(Model model) {
		model.addAttribute("jadwalJaga", new JadwalJagaModel());
		return "jadwalJaga-add";
	}

	@RequestMapping(value = "lab/jadwal-jaga/tambah", method = RequestMethod.POST)
	private String addJadwalJagaSubmit(@ModelAttribute JadwalJagaModel jadwalJaga) {
		jadwalJagaService.addJadwal(jadwalJaga);
		return "jadwalJaga-add";
	}

	@RequestMapping(value = "lab/jadwal-jaga/{tanggal}", method = RequestMethod.GET)
	private String getJadwalJaga(@PathVariable("tanggal") Date tanggal, Model model) {
		List<JadwalJagaModel> archive = jadwalJagaService.getJadwalByDate(tanggal);
		model.addAttribute("jadwalJaga", archive);
		return "jadwalJaga-view";
	}

	@RequestMapping(value = "lab/jadwal-jaga/ubah/{id}", method = RequestMethod.GET)
	private String updateJadwalJaga(@PathVariable(value = "id") long id, Model model) {
		JadwalJagaModel archive = jadwalJagaService.getJadwalById(id);
		model.addAttribute("jadwalJaga", archive);
		model.addAttribute("title", "Ubah Data Reagen");
		return "jadwalJaga-update";
	}

	@RequestMapping(value = "lab/jadwal-jaga/ubah/{id}", method = RequestMethod.POST)
	private String updateLabSupplySubmit(@PathVariable(value = "id") long id, JadwalJagaModel jadwalJaga, Model model) {
		jadwalJaga.setId(id);
		jadwalJagaService.updateJadwal(jadwalJaga);
		model.addAttribute("jadwalJaga", jadwalJaga);
		model.addAttribute("title", "Ubah Persediaan Reagen");
		return "success";
	}

}
