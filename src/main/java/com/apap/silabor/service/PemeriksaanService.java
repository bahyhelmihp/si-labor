package com.apap.silabor.service;

import java.util.List;

import com.apap.silabor.model.PemeriksaanModel;

public interface PemeriksaanService {
	PemeriksaanModel addPemeriksaan(PemeriksaanModel pemeriksaan);
	
	List<PemeriksaanModel> getListPemeriksaan();
	
	PemeriksaanModel getPemeriksaanById(Long id);
	
	List<PemeriksaanModel> makeListRequestPemeriksaan(List<Long> listId);
	
}
