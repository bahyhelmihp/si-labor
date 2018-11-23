package com.apap.silabor.service;

import java.sql.Date;
import java.util.List;

import com.apap.silabor.model.JadwalJagaModel;

public interface JadwalJagaService {
	JadwalJagaModel addJadwal(JadwalJagaModel jadwalJaga);

	List<JadwalJagaModel> getJadwalByDate(Date tanggal);

	List<JadwalJagaModel> getAllJadwaJaga();

	JadwalJagaModel getJadwalById(long id);

	JadwalJagaModel updateJadwal(JadwalJagaModel jadwalJaga);

}
