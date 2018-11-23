package com.apap.silabor.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.silabor.model.JenisPemeriksaanModel;
import com.apap.silabor.repository.JenisPemeriksaanDb;

public interface JenisPemeriksaanService {

	JenisPemeriksaanDb getJenisPemeriksaanDb();

	

	//JenisPemeriksaan getJenisPemeriksaanDb();

}
