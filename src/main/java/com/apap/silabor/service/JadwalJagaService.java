package com.apap.silabor.service;

import java.sql.Date;

import com.apap.silabor.model.JadwalJagaModel;

public interface JadwalJagaService {
	JadwalJagaModel addJadwal(JadwalJagaModel jadwal);

	JadwalJagaModel getJadwalByDate(Date tanggal);

	JadwalJagaModel updateJadwal(JadwalJagaModel jadwal);

}
