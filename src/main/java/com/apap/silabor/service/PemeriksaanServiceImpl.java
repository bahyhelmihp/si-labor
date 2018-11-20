package com.apap.silabor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.silabor.model.PemeriksaanModel;
import com.apap.silabor.repository.PemeriksaanDb;

@Service
@Transactional
public class PemeriksaanServiceImpl implements PemeriksaanService {
	@Autowired
	PemeriksaanDb pemeriksaanDb;
	
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
		return pemeriksaanDb.getOne(id);
	}



}