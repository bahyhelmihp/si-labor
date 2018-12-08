package com.apap.silabor.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.silabor.model.JenisPemeriksaanModel;
import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.repository.PemeriksaanDb;

@Service
@Transactional
public class PemeriksaanServiceImpl implements PemeriksaanService {
	@Autowired
	PemeriksaanDb pemeriksaanDb;
	
	private JenisPemeriksaanService jenisPemeriksaanService;
	private JadwalJagaService jadwalJagaService;
	
	
	@Override
	public PemeriksaanModel addPemeriksaan(PemeriksaanModel pemeriksaan) {
		// TODO Auto-generated method stub
		return pemeriksaanDb.save(pemeriksaan);
	}

	@Override
	public List<PemeriksaanModel> getListPemeriksaan() {
		// TODO Auto-generated method stub
		return pemeriksaanDb.findAll();
	}

	@Override
	public PemeriksaanModel getPemeriksaanById(Long id) {
		// TODO Auto-generated method stub
		return pemeriksaanDb.findById(id).get();
	}

	private boolean isPemeriksaanCekDarahExist(long idPasien, Date tanggalPemeriksaan, long jenisPemeriksaan ) {
		boolean exist = false;
		for (PemeriksaanModel pemeriksaan : pemeriksaanDb.findAll()) {
			long targetIdPasien = pemeriksaan.getIdPasien(); 
			Date targetTanggalPemeriksaan = pemeriksaan.getTanggalPemeriksaan();
			long targetJenisPemeriksaan = pemeriksaan.getJenisPemeriksaan().getId();
			if(	targetIdPasien == idPasien && 
				targetTanggalPemeriksaan.equals(tanggalPemeriksaan) &&
				targetJenisPemeriksaan==jenisPemeriksaan) {
				return true;
			}
		}
		
		return exist;
	}
	
	@Override
	public List<PemeriksaanModel> makeListRequestPemeriksaan(List<Long> listId) {
		// TODO Auto-generated method stub              
		for(Long id : listId) {
			if(this.isPemeriksaanCekDarahExist(id, Date.valueOf(LocalDate.now()), 1)) {
				PemeriksaanModel pemeriksaanBaru = new PemeriksaanModel();
				pemeriksaanBaru.setTanggalPengajuan(Date.valueOf("2018-09-09"));
				pemeriksaanBaru.setTanggalPemeriksaan(Date.valueOf("2018-09-09"));
				pemeriksaanBaru.setStatus(1);
				pemeriksaanBaru.setIdPasien(id);
				pemeriksaanBaru.setJenisPemeriksaan(jenisPemeriksaanService.getJenisPemeriksaanById((long)1));
				pemeriksaanBaru.setJadwalJaga(jadwalJagaService.getJadwalById(1));
				pemeriksaanDb.save(pemeriksaanBaru);
			}
			
		}
		return null;
	}

	@Override
	public boolean isExist(long idPasien, long jenisPemeriksaan) {
		// TODO Auto-generated method stub
		
//		JenisPemeriksaanModel jenisPemeriksaanTarget = jenisPemeriksaanService.getJenisPemeriksaanById(jenisPemeriksaan);
//		System.out.println("masuk pakeokk");
//		if(pemeriksaanDb.findByIdPasienAndJenisPemeriksaan(idPasien, jenisPemeriksaanTarget)!= null) {
//			return true;
//		}
		for(PemeriksaanModel pemeriksaan : pemeriksaanDb.findAll()) {
			if(pemeriksaan.getIdPasien() == idPasien && pemeriksaan.getJenisPemeriksaan().getId() == jenisPemeriksaan && pemeriksaan.getStatus() == 0) {
				return true;
			}
		}
		return false;
	}



}
