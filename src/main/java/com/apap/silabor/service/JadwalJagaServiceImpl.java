package com.apap.silabor.service;

import java.sql.Date;
import java.util.List;

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
	public List<JadwalJagaModel> getJadwalByDate(Date tanggal) {
		return jadwalJagaDb.findByTanggal(tanggal);

	}

	@Override
	public JadwalJagaModel updateJadwal(JadwalJagaModel jadwalJaga) {
		return jadwalJagaDb.save(jadwalJaga);
	}

	@Override
	public JadwalJagaModel getJadwalById(long id) {
		return jadwalJagaDb.findById(id);
	}

}
