package com.apap.silabor.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.silabor.model.JadwalJagaModel;
import com.apap.silabor.repository.JadwalJagaDb;

@Service
@Transactional
public class JadwalJagaServiceImpl implements JadwalJagaService {
	@Autowired
	JadwalJagaDb jadwalJagaDb;

	@Override
	public JadwalJagaModel addJadwal(JadwalJagaModel jadwalJaga) {
		return jadwalJagaDb.save(jadwalJaga);
	}

	@Override
	public JadwalJagaModel getJadwalByDate(Date tanggal) {
		// TODO Auto-generated method stub
		return jadwalJagaDb.findByTanggal(tanggal);

	}

	@Override
	public JadwalJagaModel updateJadwal(JadwalJagaModel jadwalJaga) {
		return jadwalJagaDb.save(jadwalJaga);
	}

}
