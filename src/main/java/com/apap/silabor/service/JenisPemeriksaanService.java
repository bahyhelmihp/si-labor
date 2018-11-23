package com.apap.silabor.service;

import java.util.List;
import com.apap.silabor.model.JenisPemeriksaanModel;

public interface JenisPemeriksaanService {
	JenisPemeriksaanModel addJenisPemeriksaan(JenisPemeriksaanModel jenisPemeriksaan);

	List<JenisPemeriksaanModel> getListJenisPemeriksaan();

	JenisPemeriksaanModel getJenisPemeriksaanById(long id);

}
