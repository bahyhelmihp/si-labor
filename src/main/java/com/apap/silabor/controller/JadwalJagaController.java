package com.apap.silabor.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.apap.silabor.model.JadwalJagaModel;
import com.apap.silabor.rest.BaseResponse;
import com.apap.silabor.rest.Setting;
import com.apap.silabor.rest.StaffResponse;
import com.apap.silabor.rest.StaffTest;
import com.apap.silabor.service.JadwalJagaService;
import com.apap.silabor.controller.JadwalCollection;

@Controller
public class JadwalJagaController {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate restJadwal() {
		return new RestTemplate();
	}

	@Autowired
	JadwalJagaService jadwalJagaService;

	// Fitur 11
	@GetMapping(value = "lab/jadwal-jaga/tambah")
	private String addJadwalJaga(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals("Admin")) {
				model.addAttribute("authenticated", authority.getAuthority());
			}
		}
		// Consume API
		StaffResponse response = restTemplate.getForObject(Setting.getAllStaffLabUrl, StaffResponse.class);
		List<StaffTest> listStaff = response.getResult();
		JadwalCollection jadwalColl = new JadwalCollection();
		jadwalColl.addJadwal(new JadwalJagaModel());

		model.addAttribute("formJadwal", jadwalColl);
		model.addAttribute("listStaff", listStaff);

		model.addAttribute("title", "Tambah Jadwal Jaga");
		return "jadwalJaga-add";
	}

	@PostMapping(value = "lab/jadwal-jaga/tambah", params = { "addEntry" })
	private String addEntryJadwal(Model model, @ModelAttribute JadwalCollection formJadwal) {
		// Consume API
		StaffResponse response = restTemplate.getForObject(Setting.getAllStaffLabUrl, StaffResponse.class);
		List<StaffTest> listStaff = response.getResult();

		formJadwal.addJadwal(new JadwalJagaModel());
		model.addAttribute("listStaff", listStaff);
		model.addAttribute("formJadwal", formJadwal);
		return "jadwalJaga-add";
	}

	@PostMapping(value = "lab/jadwal-jaga/tambah", params = { "deleteEntry" })
	private String deleteEntryJadwal(Model model, @ModelAttribute JadwalCollection formJadwal,
			HttpServletRequest deleteIndex) {
		// Consume API
		StaffResponse response = restTemplate.getForObject(Setting.getAllStaffLabUrl, StaffResponse.class);
		List<StaffTest> listStaff = response.getResult();

		if (formJadwal.getJadwalColl().size() == 1) {
			model.addAttribute("error", "Tidak bisa dihapus jika hanya ada satu entri jadwal!");
		} else {
			formJadwal.getJadwalColl().remove(
					(formJadwal.getJadwalColl().get(Integer.parseInt(deleteIndex.getParameter("deleteEntry")))));
		}
		model.addAttribute("listStaff", listStaff);
		model.addAttribute("formJadwal", formJadwal);
		return "jadwalJaga-add";

	}

	@PostMapping(value = "lab/jadwal-jaga/tambah", params = { "submitJadwal" })
	private String tambahJadwalSubmit(@ModelAttribute JadwalCollection formJadwal, Model model) {

		for (JadwalJagaModel jj : formJadwal.getJadwalColl()) {
			jadwalJagaService.addJadwal(jj);
		}
		return "jadwalJaga-sukses";
	}

	// Fitur 12
	@GetMapping(value = "lab/jadwal-jaga/lihat")
	private String getJadwalJaga(@RequestParam("tanggal") Date tanggal, Model model) {
		List<JadwalJagaModel> archive = jadwalJagaService.getJadwalByDate(tanggal);
		model.addAttribute("jadwalJaga", archive);
		model.addAttribute("tanggalJadwal", tanggal);
		model.addAttribute("title", "Lihat Jadwal Jaga");
		return "jadwalJaga-view";
	}

	// Fitur 13
	@GetMapping(value = "lab/jadwal-jaga/ubah/{id}")
	private String updateJadwalJaga(@PathVariable(value = "id") long id, Model model) {
		// Authorization
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals("Admin")) {
				model.addAttribute("authenticated", authority.getAuthority());
			}
		}
		// Consume API
		StaffResponse response = restTemplate.getForObject(Setting.getAllStaffLabUrl, StaffResponse.class);
		List<StaffTest> listStaff = response.getResult();
		JadwalJagaModel archive = jadwalJagaService.getJadwalById(id);
		// Add Model
		model.addAttribute("listStaff", listStaff);
		model.addAttribute("jadwalJaga", archive);
		model.addAttribute("title", "Ubah Jadwal Jaga");
		return "jadwalJaga-update";
	}

	@PostMapping(value = "lab/jadwal-jaga/ubah/{id}")
	private String updateLabSupplySubmit(@PathVariable(value = "id") long id, JadwalJagaModel jadwalJaga, Model model) {
		jadwalJagaService.updateJadwal(jadwalJaga);
		Date tanggalJadwal = jadwalJaga.getTanggal();
		List<JadwalJagaModel> archive = jadwalJagaService.getJadwalByDate(tanggalJadwal);
		model.addAttribute("jadwalJaga", archive);
		model.addAttribute("tanggalJadwal", tanggalJadwal);
		model.addAttribute("msgUpdate", "Jadwal Berhasil Diubah");
		model.addAttribute("title", "Ubah Jadwal Jaga");
		return "jadwalJaga-view";
	}

	// Menu Awal
	@GetMapping(value = "/lab/jadwal-jaga")
	private String viewAllJadwalJaga(Model model) {
		List<JadwalJagaModel> jadwalJaga = jadwalJagaService.getAllJadwalJaga();
		model.addAttribute("jadwalJaga", jadwalJaga);
		model.addAttribute("title", "Jadwal Jaga Lab");
		return "jadwalJaga-home";
	}
	
	//Buat IGD
	@ResponseBody
	@PostMapping(value="/tambah/{tanggal}")
	private String kirimJadwalJaga (@PathVariable(value="tanggal") Date tanggal, Model model) {
		String path = "https://ta-5-1.herokuapp.com/api/kamars?isFilled=true";
		List<JadwalJagaModel> archive = jadwalJagaService.getJadwalByDate(tanggal);
		if (archive != null) {
			BaseResponse<List<JadwalJagaModel>> response = restTemplate.postForObject(path, archive, BaseResponse.class);
	
		}
		
		return "";
		
		
	}

	// Buat Fitur Pemeriksaan
	@ResponseBody
	@GetMapping(value = "lab/jadwal-jaga/getJadwalJaga")
	public List<JadwalJagaModel> getJadwalJagaPemeriksaan(Model model) {
		return jadwalJagaService.getAllJadwalJaga();
	}

}