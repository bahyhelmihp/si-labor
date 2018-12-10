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
import com.apap.silabor.rest.Setting;
import com.apap.silabor.rest.StaffResponse;
import com.apap.silabor.rest.StaffTest;
import com.apap.silabor.service.JadwalJagaService;

/**
 * Controller Jadwal Jaga
 * 
 * @author Nathanael Lemuella
 *
 */
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

	/**
	 * Fitur add Jadwal Jaga part 1 : Get form
	 * 
	 * @param model Model
	 * @return HTML page jadwalJaga-add
	 */
	@GetMapping(value = "lab/jadwal-jaga/tambah")
	private String addJadwalJaga(Model model) {
		// Authorization
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals("Admin")) {
				// Consume API
				StaffResponse response = restTemplate.getForObject(Setting.getAllStaffLabUrl, StaffResponse.class);
				List<StaffTest> listStaff = response.getResult();
				// Collection Jadwal
				JadwalCollection jadwalColl = new JadwalCollection();
				jadwalColl.addJadwal(new JadwalJagaModel());
				// Tambah attribute ke dalam model
				model.addAttribute("formJadwal", jadwalColl);
				model.addAttribute("listStaff", listStaff);
				model.addAttribute("title", "Tambah Jadwal Jaga");
				return "jadwalJaga-add";
			} else // Jika bukan admin
				return "not-admin";
		}
		return "";
	}

	/**
	 * Add Jadwal Jaga Part 2 : Add Jadwal Lain (Fitur tambah banyak jadwal)
	 * 
	 * @param model      Model
	 * @param formJadwal Formulir Jadwal awal
	 * @return HTML page jadwalJaga-add
	 */
	@PostMapping(value = "lab/jadwal-jaga/tambah", params = { "addEntry" })
	private String addEntryJadwal(Model model, @ModelAttribute JadwalCollection formJadwal) {
		// Consume API
		StaffResponse response = restTemplate.getForObject(Setting.getAllStaffLabUrl, StaffResponse.class);
		List<StaffTest> listStaff = response.getResult();

		// Add baris baru dalam jadwal di form
		formJadwal.addJadwal(new JadwalJagaModel());
		// Tamba
		model.addAttribute("listStaff", listStaff);
		model.addAttribute("formJadwal", formJadwal);
		return "jadwalJaga-add";
	}

	/**
	 * Add Jadwal Jaga Part 3 : Delete Jadwal Lain (Fitur tambah banyak jadwal)
	 * 
	 * @param model       Model
	 * @param formJadwal  Formulir Jadwal awal
	 * @param deleteIndex Index to delete
	 * @return HTML page jadwalJaga-add
	 */
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

	/**
	 * Add Jadwal Jaga Part 4 : Post Jadwal
	 * 
	 * @param formJadwal Form jadwal yang sudah diisi
	 * @param model      Model
	 * @return HTML page jadwalJaga-sukses
	 */
	@PostMapping(value = "lab/jadwal-jaga/tambah", params = { "submitJadwal" })
	private String tambahJadwalSubmit(@ModelAttribute JadwalCollection formJadwal, Model model) {

		for (JadwalJagaModel jj : formJadwal.getJadwalColl()) {
			jadwalJagaService.addJadwal(jj);
		}
		return "jadwalJaga-sukses";
	}

	/**
	 * Melihat jadwal berdasarkan tanggal
	 * 
	 * @param tanggal Tanggal pilihan
	 * @param model   Model
	 * @return HTML page jadwalJaga-view
	 */
	@GetMapping(value = "lab/jadwal-jaga/lihat")
	private String getJadwalJaga(@RequestParam("tanggal") Date tanggal, Model model) {
		List<JadwalJagaModel> archive = jadwalJagaService.getJadwalByDate(tanggal);
		model.addAttribute("jadwalJaga", archive);
		model.addAttribute("tanggalJadwal", tanggal);
		model.addAttribute("title", "Lihat Jadwal Jaga");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals("Admin")) {

				return "jadwalJaga-view";
			} else {
				return "jadwalJaga-view2";
			}
		}
		return "";
	}

	/**
	 * Fitur Update Jadwal : Get Form Update Jadwal
	 * 
	 * @param id    ID jadwal jaga
	 * @param model Model
	 * @return HTML page jadwalJaga-update
	 */
	@GetMapping(value = "lab/jadwal-jaga/ubah/{id}")
	private String updateJadwalJaga(@PathVariable(value = "id") long id, Model model) {
		// Authorization
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals("Admin")) {
				// Consume API
				StaffResponse response = restTemplate.getForObject(Setting.getAllStaffLabUrl, StaffResponse.class);
				List<StaffTest> listStaff = response.getResult();
				JadwalJagaModel archive = jadwalJagaService.getJadwalById(id);
				// Add Model
				model.addAttribute("listStaff", listStaff);
				model.addAttribute("jadwalJaga", archive);
				model.addAttribute("title", "Ubah Jadwal Jaga");
				return "jadwalJaga-update";
			} else
				return "not-admin";
		}
		return "";
	}

	/**
	 * Fitur Update Jadwal : Post form update jadwal
	 * 
	 * @param id         ID jadwal
	 * @param jadwalJaga JadwalJaga Model
	 * @param model      Model
	 * @return HTML page jadwalJaga-view
	 */
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

	/**
	 * Menu awal fitur jadwal jaga
	 * 
	 * @param model Model
	 * @return HTML page jadwalJaga-Home
	 */
	@GetMapping(value = "/lab/jadwal-jaga")
	private String viewAllJadwalJaga(Model model) {
		List<JadwalJagaModel> jadwalJaga = jadwalJagaService.getAllJadwalJaga();
		model.addAttribute("jadwalJaga", jadwalJaga);
		model.addAttribute("title", "Jadwal Jaga Lab");
		// Authorization
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			if (authority.getAuthority().equals("Admin")) {
				return "jadwalJaga-home";
			} else {
				return "jadwalJaga-home2";
			}
		}
		return "";
	}

	/**
	 * Post jadwal ke IGD
	 * 
	 * @return HTML page jadwalJaga-home
	 */
	@PostMapping(value = "api/lab/jadwal-jaga/kirim")
	public String kirimJadwalJaga() {
		List<JadwalJagaModel> archive = jadwalJagaService.getAllJadwalJaga();
		restTemplate.postForObject(Setting.postToUgdUrl, archive, String.class);
		return "redirect:/lab/jadwal-jaga";
	}

	/**
	 * Get All Jadwal Jaga
	 * 
	 * @param model Model
	 * @return All jadwal Jaga
	 */
	@ResponseBody
	@GetMapping(value = "lab/jadwal-jaga/getJadwalJaga")
	public List<JadwalJagaModel> getJadwalJagaPemeriksaan(Model model) {
		return jadwalJagaService.getAllJadwalJaga();
	}

}