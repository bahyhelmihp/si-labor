package com.apap.silabor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.apap.silabor.model.JadwalJagaModel;
import com.apap.silabor.model.JenisPemeriksaanModel;
import com.apap.silabor.repository.JenisPemeriksaanDb;

public class JenisPemeriksaanServiceImpl implements JenisPemeriksaanService {
	@Autowired
	JenisPemeriksaanDb jenisPemeriksaanDb;
	
	@Override
	public JenisPemeriksaanModel addJenisPemeriksaan(JenisPemeriksaanModel jenisPemeriksaan) {
		// TODO Auto-generated method stub
		return jenisPemeriksaanDb.save(jenisPemeriksaan);
	}

	@Override
	public List<JenisPemeriksaanModel> getListJenisPemeriksaan() {
		// TODO Auto-generated method stub
		return jenisPemeriksaanDb.findAll();
	}

	@Override
	public JenisPemeriksaanModel getJenisPemeriksaanById(long id) {
		// TODO Auto-generated method stub
		return jenisPemeriksaanDb.getOne(id);
	}


}
